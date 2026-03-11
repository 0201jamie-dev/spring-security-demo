<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html lang="de">
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<h1>Reset your password</h1>
    <form:form method="post" modelAttribute="resetPasswordForm">
        <form:input type="hidden" value="${jwt}" path="token" />
        <table>
                <c:if test="${!isValid}">
                    <tr>
                        <td>
                            <div class="alert alert-danger" role="alert" style="display: inline-block; padding: 1rem;">
                                Token is invalid
                            </div>
                        </td>
                    </tr>
                </c:if>
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
                <td><button class="btn btn-primary" type="submit">Reset</button></td>
            </tr>
        </table>
    </form:form>
</body>
</html>
