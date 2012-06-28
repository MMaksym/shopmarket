<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>${category.name}</h1>

<table border="1" width="100%">

    <tr>
        <th><spring:message code="product.name"/></th>
        <th><spring:message code="product.description"/></th>
        <th><spring:message code="product.price"/></th>
        <th><spring:message code="product.currency"/></th>
        <th><spring:message code="actions"/></th>
    </tr>

    <c:forEach var="product" items="${products}">
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
            <td>${product.currency.name}</td>
            <td>
                <a href="<%=request.getContextPath()%>/category/${category.name}/${product.name}.html"><spring:message code="edit"/></a>
                <%--<a href="<%=request.getContextPath()%>/category/delete.html?id=${category.id}"><spring:message code="delete"/></a>--%>
            </td>
        </tr>
    </c:forEach>
</table>

<a href="<%=request.getContextPath()%>/category/${category.name}/new_product"><spring:message code="product.add.new"/></a>