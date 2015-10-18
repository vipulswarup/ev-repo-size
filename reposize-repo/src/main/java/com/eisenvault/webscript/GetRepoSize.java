package com.eisenvault.webscript;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.alfresco.service.cmr.repository.ContentService;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationConverter;
import org.apache.commons.configuration.MapConfiguration;
import org.apache.log4j.Logger;
import org.springframework.extensions.webscripts.Cache;
import org.springframework.extensions.webscripts.DeclarativeWebScript;
import org.springframework.extensions.webscripts.Status;
import org.springframework.extensions.webscripts.WebScriptRequest;

public class GetRepoSize extends DeclarativeWebScript {
private static Logger logger = Logger.getLogger(GetRepoSize.class);
	
	private Properties globalProperties;
	private ContentService contentService;
	
	public void setGlobalProperties(Properties globalProperties) {
		this.globalProperties = globalProperties;
	}
	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}
	
	@Override
	protected Map<String, Object> executeImpl(WebScriptRequest req,Status status, Cache cache) {
		if(logger.isDebugEnabled()){
			logger.debug("Entering GetRepoSize.executeImpl ...... ");
		}
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		MapConfiguration config = (MapConfiguration) ConfigurationConverter.getConfiguration(globalProperties);
		Configuration initConfig = config.interpolatedConfiguration();
		
		String contentStore = (String) initConfig.getProperty("dir.contentstore");
		String indexes = (String) initConfig.getProperty("dir.indexes");
		String indexesBackup = (String) initConfig.getProperty("dir.indexes.backup");
		
		if(logger.isDebugEnabled()){
			logger.debug("ContentStore ...... " + contentStore);
			logger.debug("Indexes ........... " + indexes);
			logger.debug("IndexesBAckup ..... " + indexesBackup);
		}
		
		try{
			long storeFreeSpace = contentService.getStoreFreeSpace();
			long storeTotalSpace = contentService.getStoreTotalSpace();
			
			if(logger.isDebugEnabled()){
				logger.debug("FreeSpace  ...... " + storeFreeSpace);
				logger.debug("TotalSpace ...... " + storeTotalSpace);
			}
			
			long contentStoreSize = getFileSize(new File(contentStore));
			
			if(logger.isDebugEnabled()){
				logger.debug("ContentStoreSize  ...... " + contentStoreSize);
			}
			
			long indexesSize = getFileSize(new File(indexes));
			long indexesBackupSize = getFileSize(new File(indexesBackup));
			
			if (logger.isDebugEnabled()) {
				logger.debug("IndexSize  ....... " + indexesSize);
				logger.debug("IndexBackupSize .. " + indexesBackupSize);
			}
			
			model.put("contentStorePath", contentStore);
			model.put("contentStoreSize", contentStoreSize);
			model.put("storeFreeSpace", storeFreeSpace);
			model.put("storeTotalSpace", storeTotalSpace);
			model.put("indexesPath", indexes);
			model.put("indexesSize", indexesSize);
			model.put("indexesBackupPath", indexesBackup);
			model.put("indexesBackupSize", indexesBackupSize);

		}catch(Exception e){
			logger.error("Exception encountered during GetRepoSize webscript processing: ", e);
			status.setCode(Status.STATUS_INTERNAL_SERVER_ERROR);
			status.setException(e);
			status.setMessage(e.getMessage());
			status.setRedirect(false);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Exiting GetRepoSize.executeImpl ...... ");
		}
		return model;
	}
	
	/**
	 * This is a recursive method. If file is found, total file size is
	 * calculated. If it is a folder, we recurse further.
	 */
	public long getFileSize(File folder) {
		if (logger.isDebugEnabled()) {
			logger.debug("Processing " + folder.getAbsolutePath());
		}
		long foldersize = 0;

		File[] filelist = folder.listFiles();
		if (filelist != null) {
			for (int i = 0; i < filelist.length; i++) {
				if (filelist[i].isDirectory()) {
					foldersize += getFileSize(filelist[i]);
				} else {
					foldersize += filelist[i].length();
				}
			}
		}

		return foldersize;
	}
}
