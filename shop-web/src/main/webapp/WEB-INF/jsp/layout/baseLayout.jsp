<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/html/css/main.css" type="text/css">
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/html/css/ui-darkness/jquery-ui-1.8.18.custom.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/main.js"></script>
    <tiles:useAttribute id="key" name="title"/>
    <title><spring:message code="${key}"/></title>
</head>

<body>
<div class="main-frame">
    <div id="header">
        <tiles:insertAttribute name="header"/>
    </div>
    <div id="navigation">
        <tiles:insertAttribute name="navigation"/>
    </div>
    <div id="content">
        <tiles:insertAttribute name="content"/>
    </div>
    <div id="footer">
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
</body>

</html>