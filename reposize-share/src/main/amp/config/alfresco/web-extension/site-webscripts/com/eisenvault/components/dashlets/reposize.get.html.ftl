<script type="text/javascript">
//<![CDATA[
    new Alfresco.dashlet.Reposize("${args.htmlid}").setOptions({
        "componentId": "${instance.object.id}"
        <#if refreshInterval??>
        ,"refreshInterval": "${refreshInterval?string}"
        </#if>
        <#if units??>
        ,"units": "${units?string}"
        </#if>
    });
    new Alfresco.widget.DashletResizer("${args.htmlid}", "${instance.object.id}");
//]]
</script>
<div class="dashlet">
    <div class="title">${msg("label.header")}</div>
    <div class="body scrollable reposize-body">
   		<div class="toolbar">
   			<span class="align-left yui-button-align">
   				<span class="first-child">
            		<a id="${args.htmlid}-configReposize-link" class="theme-color-1" href="#">${msg("label.configure")}</a>
            	</span>
            </span>
        </div>
    	<table id="${args.htmlid}-stats-reposize-table" class="reposize">
    		<tr>
   	   			<td rowspan="6" class="reposize-icon-column">
    				<img src="${page.url.context}/res/components/dashlets/reposize.png" />
    			</td>
    			<td>&nbsp;</td>
    			<td class="header reposize-label-column"><h4 class="theme-color-2">${msg("label.source")}</h4></td>
    			<td class="reposize-margin-column">&nbsp;</td>
    			<td class="header reposize-value-column"><h4 class="theme-color-2">${msg("label.size")} (<span id="${args.htmlid}-reposize-units">${units?string!'B'}</span>)</h4></td>
   			</tr>
   			
    		<tr>
    			<td>&nbsp;</td>
    			<td>${msg("label.freeSpace")}</td>
    			<td>&nbsp;</td>
   				<td><span id="${args.htmlid}-reposize-storeFreeSpace"></span></td>
    		</tr>
    		<tr>
    			<td>&nbsp;</td>
    			<td>${msg("label.totalSpace")}</td>
    			<td>&nbsp;</td>
   				<td><span id="${args.htmlid}-reposize-storeTotalSpace"></span></td>
    		</tr>
    	</table>
    </div>
</div>
