<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.context.SecurityContext" %>
<%@ page import="com.ua.shop.util.UserUtils" %>
<%@ page import="com.ua.shop.model.User" %>
<%@ page import="java.util.Locale" %>
<%@ page import="org.springframework.web.servlet.support.RequestContextUtils" %>
<%@ page import="org.springframework.web.servlet.LocaleResolver" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%
    User user = UserUtils.getUser(request);
    LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
    Locale locale = localeResolver.resolveLocale(request);
    String lang = locale.getLanguage();
    request.setAttribute("lang", lang);
%>

<c:set var="user" value="<%=user%>"/>
<c:set var="lang" value="<%=lang%>"/>

<div class="sign-in-status">
    <c:choose>
        <c:when test="${user ne null}">
            Welcome <b><c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></b>
            <a id="logout" href="<%=request.getContextPath()%>/logout"><spring:message code="logout"/></a>
        </c:when>
        <c:otherwise>
            <a id="login" href="<%=request.getContextPath()%>/login"><spring:message code="login"/></a>
            <a href="<%=request.getContextPath()%>/register.html"><spring:message code="register.register"/></a>
        </c:otherwise>
    </c:choose>
    <span style="float: right">
        <select id="locale">
            <option value="en" ${"en" eq lang ? "selected" : ""}>English</option>
            <option value="ru" ${"ru" eq lang ? "selected" : ""}>Русский</option>
        </select>
</span>
</div>

<img src="<%=request.getContextPath()%>/html/image/tron-legacy.jpg" alt="header" width="900" height="200">

<script type="text/javascript">
    $(document).ready(function () {
        $("#locale").change(function (){
            var href = window.location.href;

            var index = href.indexOf("?");
            if (index != -1) {
                href = href.substring(0, index);
            }

            window.location = href + "?lang=" + $(this).val();
        });
    });
</script>
