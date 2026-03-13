<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html lang="de">
<head>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<h1>Reset your password</h1>
    <form:form method="post" modelAttribute="resetPasswordForm">
        <form:input type="hidden" value="${jwt}" path="token" />
        <table>
            <tr>
                <c:if test="${isCaptchaInvalid}">
                    <td>
                        <div class="alert alert-warning" role="alert" style="display: inline-block; padding: 1rem;">
                            Captcha Failed
                        </div>
                    </td>
                </c:if>
                <c:if test="${!isValid}">
                    <td>
                        <div class="alert alert-danger" role="alert" style="display: inline-block; padding: 1rem;">
                            Token is invalid
                        </div>
                    </td>
                </c:if>
            </tr>
            <tr>
                <c:if test="${isValid}">
                    <td><label for="password">new password</label></td>
                    <td><form:input type="password" id="password" name="password" path="password"/></td>
                    <td><form:errors path="password" /></td>
                </c:if>
                <c:if test="${!isValid}">
                    <td><input disabled /></td>
                </c:if>
            </tr>

            <tr>
                <c:if test="${isValid}">
                    <td><label for="passwordConfirmation">confirm new password</label></td>
                    <td><form:input type="password" id="passwordConfirmation" name="passwordConfirmation" path="passwordConfirmation"/></td>
                    <td><form:errors path="passwordConfirmation" /></td>
                </c:if>
                <c:if test="${!isValid}">
                    <td><input disabled /></td>
                </c:if>
            </tr>

            <tr>
                <spring:eval expression="@environment.getProperty('google.recaptcha.site')" var="recaptchaSite" />
                <div class="g-recaptcha" data-sitekey="${recaptchaSite}"></div>
            <tr>

            <tr>
                <c:if test="${isValid}">
                    <td><button class="btn btn-primary" type="submit">Reset</button></td>
                </c:if>
                <c:if test="${!isValid}">
                    <td><button class="btn btn-primary" disabled>Reset</button></td>
                </c:if>
            </tr>
        </table>
    </form:form>
</body>
</html>
