<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<script type="text/javascript">
		function jump(p,s){
			var oForm = document.searchForm || document.forms[0];
			with(oForm){
				$("#pageIndex").val(p);
				//alert($("#pageIndex").val());
				//$("#pageSize").val(s);
				//alert($("#pageSize").val());
				submit();
			}
		}
</script>
	
	 <table>
    	<tr>
    		<td style="text-align:left;">
    			第<span style="color:red">${pager.currentPage }</span>/${pager.pageCount}页
    			共${pager.total}条
    		</td>
    		<td style="text-align:right">
    			<c:if test="${pager.currentPage==1}">
    				首页
    				上一页
    			</c:if>
    			<c:if test="${pager.currentPage!=1}">
    				<a href="javascript:jump(1,${pager.pageSize})">首页</a>
    				<a href="javascript:jump(${pager.currentPage-1},${pager.pageSize})">上一页</a>
    			</c:if>
    			<c:if test="${pager.currentPage==pager.pageCount}">
    				下一页
    				末页
    			</c:if>
    			<c:if test="${pager.currentPage!=pager.pageCount}">
    				<a href="javascript:jump(${pager.currentPage+1},${pager.pageSize})">下一页</a>
    				<a href="javascript:jump(${pager.pageCount},${pager.pageSize})">末页</a>
    			</c:if>
    			
    			转到<input type="text" id="gPage" style="width:20px" name="currentPage"></input><input type="button" value="GO" onclick="jump(document.getElementById('gPage').value,${pager.pageSize})" />
    			每页显示：<select name="currentPage" onchange="jump(1,this.value)">
    						<option value="3">3</option>
    						<option value="5">5</option>
    						<option value="10">10</option>
    					</select>
    		</td>
    	</tr>
    </table>