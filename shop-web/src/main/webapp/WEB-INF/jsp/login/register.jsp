<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/html/css/register.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery.fileupload.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/html/js/jquery.validate.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/html/js/register.js"></script>
<script type="text/javascript">
    $(document).ready(function (){
        var registerForm = $("#registerForm");

        registerForm.validate({
            rules:{
                email:{
                    required:true,
                    email: true,
                    maxlength:100
                },
                password: {
                    required: true,
                    minlength:6
                },
                confirmPassword: {
                    equalTo: "#password"

                },
                firstName: {
                    required:true,
                    maxlength:15
                },
                lastName: {
                    required:true,
                    maxlength:20
                },
                dateOfBirth: {
                    required:true
                }
            },
            messages:{
                email:{
                    required:"<spring:message code="email.should.be.not.blank"/>",
                    maxlength:"<spring:message code="email.more.than"/>",
                    email: "<spring:message code="email.is.not.valid"/>"
                },
                password:{
                    required:"<spring:message code="password.is.required"/>",
                    minlength:"<spring:message code="password.should.be.at.least"/>",
                    maxlength:"<spring:message code="password.more.than"/>"
                },
                confirmPassword: {
                    equalTo: "<spring:message code="password.should.be.same"/>"
                },
                firstName: {
                    required:"<spring:message code="first.name.should.be.not.blank"/>",
                    maxlength:"<spring:message code="first.name.more.than"/>"
                },
                lastName: {
                    required:"<spring:message code="last.name.should.be.not.blank"/>",
                    maxlength:"<spring:message code="last.name.more.than"/>"
                },
                dateOfBirth: {
                    required:"<spring:message code="date.of.birth.is.required"/>"
                }
            }
        });
    });
</script>

<div>
    <img id="user_photo" src="${form.photoUrl}" alt="user_image" width="150" height="150">
    <br/><input id="photoUpload" type="file" name="photo" data-url="<%=request.getContextPath()%>/register/upload">
</div>

<form id="registerForm" action="<%=request.getContextPath()%>/register" method="post">


    <input id="photo_url_param" type="hidden" name="photoUrl" value="${form.photoUrl}">

    <table>
        <tr>
            <td><spring:message code="register.email"/>:</td>
            <td><input type="text" name="email" value="${form.email}"/></td>
            <td><form:errors path="userForm.email" cssClass="error"/></td>
        </tr>

        <tr>
            <td><spring:message code="register.password"/>:</td>
            <td><input id="password" type="password" name="password" value="${form.password}"/></td>
            <td><form:errors path="userForm.password" cssClass="error"/></td>
        </tr>

        <tr>
            <td><spring:message code="register.confirm.password"/>:</td>
            <td><input type="password" name="confirmPassword" value="${form.confirmPassword}"/></td>
            <td><form:errors path="userForm.confirmPassword" cssClass="error"/></td>
        </tr>

        <tr>
            <td><spring:message code="register.first.name"/>:</td>
            <td><input type="text" name="firstName" value="${form.firstName}"/></td>
            <td><form:errors path="userForm.firstName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><spring:message code="register.last.name"/>:</td>
            <td><input type="text" name="lastName" value="${form.lastName}"/></td>
            <td><form:errors path="userForm.lastName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><spring:message code="register.date.of.birth"/>:</td>
            <td><input type="text" name="dateOfBirth" value="${form.dateOfBirth}" id="dataOfBirth"/></td>
            <td><form:errors path="userForm.dateOfBirth" cssClass="error"/></td>
        </tr>

        <tr>
            <td><spring:message code="register.gender"/>:</td>
            <td>
                <select name="gender">
                    <option value="MALE" ${"MALE" eq form.gender ? "selected" : ""}><spring:message
                            code="register.gender.male"/></option>
                    <option value="FEMALE" ${"FEMALE" eq form.gender ? "selected" : ""}><spring:message
                            code="register.gender.female"/></option>
                </select>
            </td>
            <td><form:errors path="userForm.gender" cssClass="error"/></td>
        </tr>

        <tr>
            <td colspan="2">
                <input name="submit" type="submit" value="<spring:message code='register.register'/>"/>
            </td>
        </tr>

    </table>
</form>