<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/taglibs.jsp"%>
<html>
<head>
    <title>Title</title>
    ${validate_css }
    ${jquery_js}
    ${jquery_validate_js}
    <script type="text/javascript">
        $(function () {
            $.validator.addMethod("checkUserName",function (value,element) {
                var regName=/^[a-zA-Z][a-zA-Z0-9]{3,15}$/;
                return this.optional(element)||(regName.test(value));
            },"4-16位，以字母开头")

            $.validator.addMethod("checkPassword",function (value,element) {
                var regName=/^[a-zA-Z0-9]{3,10}$/;
                return this.optional(element)||(regName.test(value));
            },"3-10位，由字母数字组成")


            $("#myform").validate({
                rules:{
                    username:{
                        required:true,
                        checkUserName:true,
                    },
                    password:{
                        required:true,
                        checkPassword:true
                    }
                },
                messages:{
                    username:{
                        required:"用户名不能为空"
                    },
                    password:{
                        required:"密码不能为空"
                    }
                }
            })

            $("#btn").click(function () {
                if($("#myform").valid()){
                    var username=$("#username").val();
                    var userpwd=$("#password").val();
                    var data={"username":username,"password":userpwd};
                    $.post(
                    "${ctx}/user/ajaxlogin",
                    data,
                    function (result) {
                        if(result.code==1){
                            location.href="${ctx}/sale/index";
                        }
                        alert(result.msg);
                    },"json");
                }
            });
        });
    </script>
</head>
<body>

    <form action="" method="post" id="myform">
        <p>用户名：<input type="text" name="username" id="username" /></p>
        <p>密码 ：<input type="password" name="password"  id="password"/></p>
        <input type="button" id="btn" value="登录" />
    </form>
</body>
</html>
