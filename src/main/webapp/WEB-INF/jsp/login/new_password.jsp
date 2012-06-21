<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form action="<%=request.getContextPath()%>/change_password" method="post">
    <input type="hidden" name="email" value="${form.email}">
    <input type="hidden" name="hash" value="${form.hash}">
    <input type="hidden" name="forgotPassword" value="${form.forgotPassword}">

    <table>
        <tr>
            <td>New password:</td>
            <td><input type="password" name="newPassword"></td>
        </tr>
        <tr>
            <td>Confirm password:</td>
            <td><input type="password" name="confirmNewPassword"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit">
            </td>
        </tr>
    </table>
</form>

