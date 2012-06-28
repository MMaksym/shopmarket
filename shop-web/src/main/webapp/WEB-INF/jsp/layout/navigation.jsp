<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<ul>
    <li>
        <a href="<%=request.getContextPath()%>"><spring:message code="home.page"/></a>
    </li>
    <li>
        <a href="<%=request.getContextPath()%>/category"><spring:message code="category.categories"/></a>
    </li>
    <%--<li>--%>
    <%--<a href="<%=request.getContextPath()%>/config.htm"><spring:message code="config"/></a>--%>
    <%--</li>--%>
</ul>
