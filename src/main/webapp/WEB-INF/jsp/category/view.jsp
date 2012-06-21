<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/html/css/jquery.dataTables.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/html/js/category/view.js"></script>

<table id="catTable" border="1" width="100%" cellpadding="0" cellspacing="0">
    <thead>
    <tr>
        <th><spring:message code="category.name"/></th>
        <th><spring:message code="category.description"/></th>
        <th><spring:message code="actions"/></th>
    </tr>
    </thead>

    <tbody>
    <%--<c:forEach var="category" items="${categories}">--%>
        <%--<tr>--%>
            <%--<td>${category.name}</td>--%>
            <%--<td>${category.description}</td>--%>
            <%--<td>--%>
                <%--<a href="<%=request.getContextPath()%>/category/${category.id}"><spring:message code="edit"/></a>--%>
                <%--<a href="<%=request.getContextPath()%>/category/delete.html?id=${category.id}"><spring:message code="delete"/></a>--%>
                <%--<a href="<%=request.getContextPath()%>/category/${category.name}.html"><spring:message code="category.products"/></a>--%>
            <%--</td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
    </tbody>
</table>
<br/>

<a href="<%=request.getContextPath()%>/category/new_category"><spring:message code="category.add.new"/></a>



