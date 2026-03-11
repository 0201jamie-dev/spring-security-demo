<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html lang="de">
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<h1>Passwort vergessen</h1>
    <form action="/forgotPassword" method="post">
        <table>
            <c:if test="${emailSent}">
                <tr>
                    <div class="alert alert-success" role="alert" style="display: inline-block; padding: 1rem;">
                        Email was sent
                    </div>
                <tr>
            </c:if>

            <tr>
                <td><label for="emailAddress">email address of your account</label></td>
                <td><input type="text" id="emailAddress" name="emailAddress"/></td>
                <td><form:errors path="emailAddress" /></td>
            </tr>

            <tr>
                <td><button class="btn btn-primary" type="submit">Send Email</button></td>
            </tr>
</form>
</body>
</html>
