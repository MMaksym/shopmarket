<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/html/css/main.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/html/css/ui-lightness/jquery-ui-1.8.11.custom.css"
          type="text/css">

    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery-ui-1.8.11.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/main.js"></script>
    <title><tiles:insertAttribute name="title"/></title>
</head>

<body>
<div class="main-frame">
    <div id="header">
        <tiles:insertAttribute name="header"/>
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