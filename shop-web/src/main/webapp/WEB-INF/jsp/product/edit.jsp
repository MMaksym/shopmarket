<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--@elvariable id="product" type="com.ua.shop.web.form.ProductForm"--%>

<form action="<%=request.getContextPath()%>/category/${product.categoryName}/saveProduct" method="post">
    <input type="hidden" name="id" value="${product.id}"/>
    <input type="hidden" name="categoryName" value="${product.categoryName}"/>

    <table>

        <tr>
            <th><spring:message code="product.name"/>:</th>
            <td><input type="text" name="name" value="${product.name}"/></td>

        </tr>
        <tr>
            <th><spring:message code="product.description"/>:</th>
            <td><input type="text" name="description" value="${product.description}"/></td>
        </tr>
        <tr>
            <th><spring:message code="product.price"/>:</th>
            <td><input type="text" name="price" value="${product.price}"/></td>
        </tr>

        <tr>
            <th><spring:message code="product.currency"/></th>
            <td>
                <select name="currencyId">
                    <c:forEach var="currency" items="${currencies}">
                        <option value="${currency.id}" ${product.currencyId eq currency.id ? "selected" : ""}>${currency.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td><input type="submit" value="<spring:message code="save"/>"/></td>
            <td><input type="button" value="<spring:message code="back"/>"></td>
        </tr>

    </table>

</form>