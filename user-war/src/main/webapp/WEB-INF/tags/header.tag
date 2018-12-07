<%@attribute name="title" type="java.lang.String" required="false"%>
<%@ include file="/WEB-INF/pages/taglibs.jspf"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no">
<title>${title}</title>
<script type="text/javascript">
	var basePath = "${basePath}";
</script>
<link rel="stylesheet" type="text/css" href="${basePath}/css/global.css" />
<script type="text/javascript" src="${basePath}/libs/zepto/zepto.min.js"></script>
<script type="text/javascript"src="${basePath}/libs/fastclick/fastclick.js"></script>
<script type="text/javascript" src="${basePath}/libs/vue/vue.js"></script>

