<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>


<!DOCTYPE html>
<html lang="de">
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<h1>Login Page</h1>
    <form action="/login" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

        <label for="username">Username</label>
        <input type="text" id="username" name="username"/>

        <label for="password">Password</label>
        <input type="password" id="password" name="password"/>

        <button class="btn btn-primary" type="submit">Login</button>

        <c:if test="${param.error}">
            <p class="text-danger">Cannot log in</p>
        </c:if>
</form>
</body>
</html>
