<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload</title>
</head>
<body>


<form action="<%=request.getContextPath()%>/upload" method="post" enctype="multipart/form-data">
    <table>
        <tr>
            <td>File 1:</td>
            <td><input type="file" name="file"></td>
        </tr>

        <tr>
            <td colspan="2">
                <input type="submit" value="Submit">
            </td>
        </tr>
    </table>
</form>

<table>
    <tr>
        <th>Name</th>
        <th>Title</th>
        <th>Extension</th>
        <th>Content type</th>
        <th>Size</th>
        <th>Actions</th>
    </tr>
    
    <c:forEach var="attachment" items="${attachments}">
        <tr>
            <td>${attachment.name}</td>
            <td>${attachment.title}</td>
            <td>${attachment.extension}</td>
            <td>${attachment.contentType}</td>
            <td>${attachment.size}</td>
            <td>
                <a href="<%=request.getContextPath()%>/image/${attachment.user.id}/${attachment.name}">Download</a>,
                <a href="<%=request.getContextPath()%>/upload/${attachment.user.id}/${attachment.name}?remove">Remove</a>
            </td>
        </tr>
    </c:forEach>
    


</table>



</body>
</html>