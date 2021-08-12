
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglibs.jsp"%>
<html>
<head>
    <title>Title</title>
    ${jquery_js}${jquery_validate_js}
    <script type="text/javascript">
        $(function () {
            $("#myform").validate({
                rules:{
                    price:{
                        required:true,
                        number:true,
                    },
                    quantity:{
                        required:true,
                        digits:true
                    },
                    saledate:{
                        required:true,
                        date:true
                    }
                },
                messages:{
                    price:{
                        required:"价格不能为空",
                        number:"必须是数值"
                    },
                    quantity:{
                        required:"数量不能为空",
                        digits:"必须是整数"
                    },
                    saledate:{
                        required:"时间不能为空",
                        date:"时间格式不正确"
                    }
                }
            })

            $("#btn").click(function () {
                if($("#myform").valid()){
                    var url;
                    var id=$("#saleid").val();
                    if(id==null || id==""){
                        url="${ctx}/sale/add";
                    }else{
                        url="${ctx}/sale/update";
                    }

                    var data=$("#myform").serialize();
                    alert(data);
                    $.post(url,data,function (result) {
                        if(result.code==1){
                            location.href="${ctx}/sale/index";
                        }
                        alert(result.msg);
                    },"json")
                }
            })

        })
    </script>

</head>
<body>

    <form action="" method="post" id="myform">
        <input type="hidden" name="saleid" id="saleid" value="${sale.saleid}" />
       <table border="1">
           <tr>
               <td>价格</td>
               <td><input type="text" name="price" value="${sale.price}"></td>
           </tr>
           <tr>
               <td>数量</td>
               <td><input type="text" name="quantity" value="${sale.quantity}"></td>
           </tr>

           <tr>
               <td>日期</td>
               <c:if test="${sale.saledate==null}">
                   <td><input type="text" name="saledate"></td>
               </c:if>
               <c:if test="${sale.saledate!=null}">
                   <td><input type="text" name="saledate" readonly value="<fmt:formatDate value="${sale.saledate}" pattern="yyyy-MM-dd" />"></td>
               </c:if>

           </tr>
           <tr>
               <td>销售人</td>
               <td>${loginuser.username}</td>
           </tr>
           <tr>
               <td>商品名</td>
               <td>
                   <select name="product.productid">
                       <option value="-1">请选择</option>
                       <c:forEach items="${productlist}" var="pro">
                           <option value="${pro.productid}"  <c:if test="${sale.product.productid==pro.productid}">selected</c:if>>${pro.productname}</option>
                       </c:forEach>
                   </select>
               </td>
           </tr>
           <tr>
               <td></td>
               <td><input type="button" id="btn" value="提交" /></td>
           </tr>
       </table>
    </form>
</body>
</html>
