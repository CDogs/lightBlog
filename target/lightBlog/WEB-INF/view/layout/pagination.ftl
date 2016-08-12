<#if (page??)>
<#if page.totalPages != 0>
<div class="pagination text-center" style="display: inline-block" >
	<ul class="list-inline" style="display: inline-block">
	<#if page.totalPages != 0>
		<#if page.totalPages == page.currentPage && page.totalPages == 1>
			<li class="disabled" style="display: inline-block"><span class="btn btn-default">&laquo;上一页</span></li>
		<#else> 
			<#if page.currentPage == 1>
				<li class="disabled" style="display: inline-block"><span class="btn btn-default">&laquo;上一页</span></li>
			<#else> 
				<li style="display: inline-block"><a href="javascript:;" name="last" class="btn btn-default">&laquo;上一页</a></li>
			</#if>
		</#if>
	<#else> 
		<li class="disabled" style="display: inline-block"><span class="btn btn-default">&laquo;上一页</span></li>
	</#if>
	<#assign outPageNum = page.currentPage+4>
	<#assign firstPageNum = page.currentPage-2>
	<#if (outPageNum >page.totalPages) >
		<#assign outPageNum = page.totalPages>
	</#if>
	
	<#if (firstPageNum < 1) >
		<#assign firstPageNum = 1>
	</#if>
	
	
	<#if (firstPageNum > 1) >
		<li><a href="javascript:void(0);">1</a></li>		
	</#if>
	
	<#if (firstPageNum > 2) >	
		<li><a href="javascript:void(0);">2</a></li>		
		<#if (firstPageNum !=3) >	
		...
		</#if>
	</#if>	
	
	<#list firstPageNum..outPageNum as pageNum>
	 
	 	<#if (page.currentPage == pageNum) >
			<li class="active" style="display: inline-block"><a href="javascript:void(0);">${pageNum}</a></li>
		<#else> 
			<li style="display: inline-block"><a href="javascript:void(0);" >${pageNum}</a></li>
		</#if>	
	 </#list>
	 
	 <#if (outPageNum < page.totalPages) >
		<li class="disabled" style="display: inline-block"><span>... </span></li>
	 </#if>
	 
	<#if (page.currentPage == page.totalPages) >
	 	<li class="disabled" style="display: inline-block"><span class="btn btn-default">下一页 &raquo;</span></li>
	<#else> 
		 <li style="display: inline-block"><a class="btn btn-default" role="button" name="next" href="javascript:void(0);">下一页 &raquo; </a></li>
	</#if>
	</ul>
	</div>
</#if>
</#if>









