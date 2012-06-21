<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ajax test page</title>
    <link rel="stylesheet" type="text/css"
          href="<%=request.getContextPath()%>/html/css/ui-darkness/jquery-ui-1.8.18.custom.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery-1.7.1.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery.fileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/html/js/ajax_test.js"></script>

    <script type="text/javascript">

    </script>

</head>
<body>

<%--<img id="preview" src="#" alt="img" width="200" height="200">--%>

<%--<form action="<%=request.getContextPath()%>/ajax" id="fm" method="post" enctype="multipart/form-data">--%>

        <input type="file" name="file" id="fileUpload" multiple data-url="<%=request.getContextPath()%>/ajax">
        <input type="button" id="uploadButton" value="upload">
<%--</form>--%>

<div id="result"></div>

</body>
</html>