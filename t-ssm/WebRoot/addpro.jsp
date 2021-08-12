<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglibs.jsp"%>
<html>
<head>
    <title>Title</title>
    ${jquery_js}${jquery_validate_js}${jquery_form_js}
    <script type="text/javascript">
        $(function () {
            $("#btn").click(function () {
                var option={
                    url:"${ctx}/pro/add",
                    type:"POST",
                    dataType:"json",
                    success:callback,
                    error:error
                }
                $("#myform").ajaxSubmit(option)

            })
        })
        function callback(result) {
            if(result.code==1){
                location.href="${ctx}/sale/index";
            }
            alert(result.msg);
        }
        function error() {
            alert("请联系管理员！！")
        }

    </script>

</head>
<body>

    <form action="" method="post" enctype="multipart/form-data" id="myform">
       <table border="1">
           <tr>
               <td>数量</td>
               <td><input type="text" name="quantity"></td>
           </tr>
           <tr>
               <td>商品名</td>
               <td><input type="text" name="productname"></td>
           </tr>
           <tr>
               <td>商品图1</td>
               <td><input type="file" name="files"></td>
           </tr>
           <tr>
               <td>商品图2</td>
               <td><input type="file" name="files"></td>
           </tr>
           <tr>
               <td></td>
               <td><input type="button" id="btn" value="提交" /></td>
           </tr>
       </table>
    </form>
</body>
</html>
