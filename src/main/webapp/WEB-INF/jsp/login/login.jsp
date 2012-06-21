<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery.validate.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        var loginForm = $("#loginForm");

        loginForm.validate({
            rules: {
                login: {
                    "required":true,
                    "email":true
                },
                password: "required"
            },
            messages:{
                login: {
                    "required":"<spring:message code="email.is.required"/>",
                    "email":"<spring:message code="email.is.not.valid"/>"
                },
                password:{
                    "required":"<spring:message code="password.is.required"/>"
                }
            }
        });

//        loginForm.submit(function (form) {
//            var valid = loginForm.valid();
//            if(valid) {
//                loginForm.submit();
//            }else{
//                return false;
//            }
//        });
    });
</script>

<c:if test="${!empty param.failed && !empty SPRING_SECURITY_LAST_EXCEPTION.message}">
    <div class="error"><spring:message code="login.or.password.incorrect"/></div>
</c:if>

<form id="loginForm" action="<%=request.getContextPath()%>/signin" method="POST" onload="document.f.email.focus();">
    <table>
        <tr>
            <td><spring:message code="register.email"/>:</td>
            <td><input type="text" name="login" value=""></td>
        </tr>
        <tr>
            <td><spring:message code="register.password"/>:</td>
            <td><input type="password" name="password"/></td>
        </tr>
        <tr>
            <td><input name="submit" type="submit" value="<spring:message code='login'/>"/></td>
            <td><a href="<%=request.getContextPath()%>/forgot_password"><spring:message code="register.forgot.password"/></a></td>
        </tr>
    </table>
</form>
