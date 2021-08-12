<%@include file="common/taglibs.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Title</title>
    ${jquery_js}
    <script type="text/javascript">
    	$(function(){
    		$(".del").click(function(){
    			if(confirm("你确定要删除吗")){
    				var id=$(this).attr("rel");
    				$.post(
    					"${ctx}/sale/del/"+id,
    					function(data){
    						if(data.code==1){
    							location.href="${ctx}/sale/index";
    						}
    						alert(data.msg);
    					},
    					"json"
    				);
    			}
    		});
    		
    		$(".update").click(function(){
    			var id = $(this).attr("rel");
    			location.href="${ctx}/sale/toedit/"+id;
    		});
    		
    		$(".show").click(function(){
    			var id=$(this).attr("rel");
    			$.post(
    				"${ctx}/sale/show/"+id,
    				function(result){
    					if(result=="nodata"){
    						alert("没有相关数据");
    					}else if(result=="failed"){
    						alert("操作失败，请联系管理员");
    					}else{
    						$("#proname").val(result.product.productname);
    						$("#totalprice").val(result.totalprice);
    						$("#username").val(result.user.username);
    						$("#saledate").val(result.saledate);
    					}
    				},
    				"json"
    			);
    		});
    	});
    </script>
  </head>
  <body>
    <h1>欢迎${loginuser.username}登录成功！！</h1>
    <a href="${ctx}/sale/toadd">添加销售记录</a>
    <a href="${ctx}/addpro.jsp">添加商品</a>

    <form action="${ctx}/sale/index" method="post">

      <input type="hidden" name="currentPage" id="pageIndex" value="${pager.currentPage}" />

      <p>商品名:
        <select name="product.productid">
          <option value="-1">请选择</option>
          <c:forEach items="${productlist}" var="pro">
            <option value="${pro.productid}"  <c:if test="${sale.product.productid==pro.productid}">selected</c:if>>${pro.productname}</option>
          </c:forEach>
        </select>
      </p>

      <p>
        <select name="choice">
          <option value="totalprice"  <c:if test="${choice=='totalprice'}">selected</c:if>>按总价</option>
          <option value="saledate" <c:if test="${choice=='saledate'}">selected</c:if>>按日期</option>
        </select>
      </p>

      <input type="submit" value="查询" />

      <table width="600px" border="1">
        <tr>
          <td>销售编号</td>
          <td>销售总价</td>
          <td>销售日期</td>
          <td>销售人</td>
          <td>销售商品名</td>
          <td>销售商品图片</td>
          <td>编辑</td>
        </tr>

        <c:forEach items="${pager.pageRecords}" var="sale">
          <tr>
            <td><a href="javascript:void(0)" class="show" rel="${sale.saleid}">${sale.saleid}</a></td>
            <td>${sale.totalprice}</td>
            <td><fmt:formatDate value="${sale.saledate}" pattern="yyyy-MM-dd" /> </td>
            <td>${sale.user.username}</td>
            <td>${sale.product.productname}</td>
            <td><img src="${ctx}/statics/images/${sale.product.image}" width="80px" height="80px"></td>
            <td><a href="javascript:location.href='${ctx}/pro/down?filename=${sale.product.image}'">下载</a>
            <a href="javascript:void(0)" class="del" rel="${sale.saleid}">删除</a>
            <a href="javascript:void(0)" class="update" rel="${sale.saleid}">修改</a>

            </td>

          </tr>
        </c:forEach>
      </table>

      <%@include file="common/page.jsp"%>
    </form>
    
    <p>商品名，<input type="text" id="proname" /></p>
    <p>销售总价，<input type="text" id="totalprice" /></p>
    <p>销售人，<input type="text" id="username" /></p>
    <p>销售时间，<input type="text" id="saledate" /></p>
  </body>
</html>
