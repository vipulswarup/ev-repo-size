package com.eisenvault.webscript;

import java.util.Properties;

import org.alfresco.service.cmr.repository.ContentService;
import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tradeshift.test.remote.Remote;
import com.tradeshift.test.remote.RemoteTestRunner;

@RunWith(RemoteTestRunner.class)
@Remote(runnerClass=SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:alfresco/application-context.xml")
public class GetRepoSizeTest {
	static Logger log = Logger.getLogger(GetRepoSizeTest.class);
	
	@Autowired
	@Qualifier("global-properties")
	private Properties globalProperties;
	
	@Autowired
	@Qualifier("contentService")
	private ContentService contentService;
	
	
}
