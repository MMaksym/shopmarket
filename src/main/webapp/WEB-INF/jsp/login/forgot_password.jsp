<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<h3 style="color: green;">${successMessage}</h3>

<form action="<%=request.getContextPath()%>/forgot_password" method="post">

    <table>
        <tr>
            <td>Your Email:</td>
            <td><input type="text" name="email"></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Send request">
            </td>
        </tr>
    </table>

</form>
