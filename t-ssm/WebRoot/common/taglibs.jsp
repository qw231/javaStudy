<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%--  system values--%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="jquery_js" value="<script type='text/javascript' src='${ctx}/statics/js/jquery-1.8.3.min.js'></script>"  />
<c:set var="jquery_form_js" value="<script type='text/javascript' src='${ctx}/statics/js/jquery.form.js'></script>"  />
<c:set var="jquery_validate_js" value="<script type='text/javascript' src='${ctx}/statics/js/jquery.validate.js'></script>"  />
<c:set var="echar_js" value="<script type='text/javascript' src='${ctx}/statics/js/echarts.js'></script>"  />
<%-- css file define--%>
<c:set var="index_css" value="<link href='${ctx}/statics/css/index.css' rel='stylesheet' type='text/css' />"/>
<c:set var="validate_css" value="<link href='${ctx}/statics/css/validate.css' rel='stylesheet' type='text/css' />"/>