/**value
 * Created by honglin.jiang on 2014/10/14.
 */
//获取DOM对象
function e(str){
    return document.getElementById(str);
}
//动态添加有样式的内容
function appendHtml(obj,str,className){
    obj.innerHTML = str;
    obj.className  = className;
}
//验证表单
function checkForm(){
    var ids  = ["checkUserName()","checkPwd()","checkRepwd()","checkNikeName()","checkTel()","checkEmail()"];
    var sum = 0;
    for(var i = 0; i<ids.length ;i++){
        if ( eval(ids[i]) )sum++;
    }
    return ids.length == sum ? true : false;
}

function focusShow(inputId,msg){
    var odom = e(inputId);//输入框DOM对象
    var omsg = e(inputId+"Id");//信息提示DOM对象
    odom.style.borderColor = "red";
    appendHtml(omsg,msg,"import_prompt");

}
//验证用户名输入
function checkUserName(){
    var flag = true;
    var odom = e("userName");//输入框DOM对象
    var omsg = e("userNameId");//信息提示DOM对象
    var reg = new RegExp("^[a-zA-Z0-9][a-zA-Z0-9\-\._]{2,16}[a-zA-Z0-9]$");
    if(odom.value == ""){
        odom.style.borderColor = "red";
        appendHtml(omsg,"通行证用户名不能为空，请输入通行证用户名","error_prompt");
        flag =  false;
    }else if( odom.value.match(reg) == null  ){
        odom.style.borderColor = "red";
        appendHtml(omsg,"请输入正确的通行证用户名","error_prompt");
        flag =  false;
    }else{
        odom.style.borderColor = "green";
        appendHtml(omsg,"输入正确","ok_prompt");
        flag =  true;
    }
    return flag;
}

//验证密码
function checkPwd(){
    var flag = true;
    var odom = e("pwd");//输入框DOM对象
    var omsg = e("pwdId");//信息提示DOM对象
    if( odom.value == ""){
        odom.style.borderColor = "red";
        appendHtml(omsg,"请输入密码","error_prompt");
        flag =  false;
    }else if( odom.value.length < 6 || odom.value.length > 16 ){
        odom.style.borderColor = "red";
        appendHtml(omsg,"密码长度为6-16个字符","error_prompt");
        flag =  false;
    }else{
        odom.style.borderColor = "green";
        appendHtml(omsg,"输入正确","ok_prompt");
        flag =  true;
    }
    return flag;
}
//验证重复密码
function checkRepwd(){
    var flag = true;
    var odom = e("repwd");//输入框DOM对象
    var omsg = e("repwdId");//信息提示DOM对象
    if(odom.value == ""){
        odom.style.borderColor = "red";
        appendHtml(omsg,"确认密码不能为空","error_prompt");
        flag =  false;
    }else if(odom.value !=  e("pwd").value){
        odom.style.borderColor = "red";
        appendHtml(omsg,"两次输入的密码不一致","error_prompt");
        flag =  false;
    } else{
        odom.style.borderColor = "green";
        appendHtml(omsg,"输入正确","ok_prompt");
        flag =  true;
    }
    return flag;
}

//验证昵称
function checkNikeName(){
    var flag = true;
    var odom = e("nickName");//输入框DOM对象
    var omsg = e("nickNameId");//信息提示DOM对象
    var reg = /^([\u4e00-\u9fa5]|\w|[@!#$%&*])+$/;   // 匹配昵称
    var chinaReg = /[\u4e00-\u9fa5]/g;   //匹配中文字符
    var len = odom.value.replace(chinaReg, "ab").length;  //把中文字符转换为两个字母，以计算字符长度

    if(odom.value == "") {
        odom.style.borderColor = "red";
        appendHtml(omsg, "昵称不能为空，请输入昵称", "error_prompt");
        flag = false;
    }else  if(odom.value.match(reg)==null) {
        odom.style.borderColor = "red";
        appendHtml(omsg, "只能由汉字、字母、数字、下划线以及@!#$%&*特殊字符组成", "error_prompt");
        flag = false;
    }else if( len <4 || len>16 ){
        appendHtml(omsg, "昵称为4-16个字符", "error_prompt");
    }else{
        odom.style.borderColor = "green";
        appendHtml(omsg,"输入正确","ok_prompt");
        flag =  true;
    }
    return flag;
}

//验证手机
function checkTel(){
    var flag = true;
    var odom = e("tel");//输入框DOM对象
    var omsg = e("telId");//信息提示DOM对象
    var reg=/^(13|15|18)\d{9}$/;
    if( odom.value == "" ) {
        odom.style.borderColor = "red";
        appendHtml(omsg, "关联手机号码不能为空，请输入关联手机号码", "error_prompt");
        flag = false;

    }else if(odom.value.match(reg) == null){
        odom.style.borderColor = "red";
        appendHtml(omsg, "关联手机号码输入不正确，请重新输入", "error_prompt");
        flag = false;
    }else{
        odom.style.borderColor = "green";
        appendHtml(omsg,"输入正确","ok_prompt");
        flag =  true;
    }
    return flag;
}
//验证邮箱
function checkEmail(){
    var flag = true;
    var odom = e("email");//输入框DOM对象
    var omsg = e("emailId");//信息提示DOM对象
    var reg=/^\w+@\w+(\.[a-zA-Z]{2,3}){1,2}$/;
    if(odom.value == ""){
        odom.style.borderColor = "red";
        appendHtml(omsg,"保密邮箱不能为空，请输入保密邮箱","error_prompt");
        flag =  false;
    }else if( odom.value.match(reg) ==null ){
        odom.style.borderColor = "red";
        appendHtml(omsg,"邮件格式不正确","error_prompt");
        flag =  false;
    }else{
        odom.style.borderColor = "green";
        appendHtml(omsg,"输入正确","ok_prompt");
        flag =  true;
    }
    return flag;
}