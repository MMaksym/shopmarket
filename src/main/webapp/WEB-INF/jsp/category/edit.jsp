<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/html/js/category/edit.js"></script>

<form action="<%=request.getContextPath()%>/category" method="post">
    <input type="hidden" name="id" value="${category.id}"/>
    <table>

        <tr>
            <th><spring:message code="category.name"/>:</th>
            <td><input type="text" name="title" value="${category.title}"/></td>
            <td><form:errors path="category.title" cssClass="error"/></td>
        </tr>
        <tr>
            <th><spring:message code="category.description"/>:</th>
            <td><input type="text" name="description" value="${category.description}"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="<spring:message code="save"/>"/></td>
            <td><input type="button" class="back" value="<spring:message code="back"/>"></td>
        </tr>

    </table>

</form>



