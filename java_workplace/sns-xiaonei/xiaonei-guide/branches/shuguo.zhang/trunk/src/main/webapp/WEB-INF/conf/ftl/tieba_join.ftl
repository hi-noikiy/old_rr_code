<#compress>
<#include "1,0">
<li id="feed${id[0]}">
	<#include "1,4">
	<h3>
	<#list fName as n>
		<a target="_blank" href="http://kaixin.com/profile.do?id=${fID[n_index]}&ref=${vType}">${n}</a>
	</#list> 加入说吧 <a href="${url[0]}&ref=${vType}" target="_blank">${name[0]}</a>
	</h3>
	<div class="details">
		<div class="legend">
			<img src="http://s.xnimg.cn/a.gif" alt="说吧" class="iTieba" />
			<span class="duration"><@format>${time[0]}</@format></span>
		</div>
	</div>
<#include "1,1">
</li>
</#compress>