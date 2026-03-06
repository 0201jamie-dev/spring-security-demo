<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>

<!DOCTYPE html>
<html lang="de">
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
</head>
<body>
<h1>Registration Page</h1>
    <form:form modelAttribute="registrationForm" method="post">
        <table>
            <tr>
                <td><label for="username">Username</label></td>
                <td><form:input type="text" id="username" path="username"/></td>
                <td><form:errors path="username" /></td>
            </tr>

            <tr>
                <td><label for="realname">Realname</label></td>
                <td><form:input type="text" id="realname" path="realname"/></td>
                <td><form:errors path="realname" /></td>
            </tr>

            <tr>
                <td><label for="emailAddress">Email address</label></td>
                <td><form:input type="text" id="emailAddress" path="emailAddress"/></td>
                <td><form:errors path="emailAddress" /></td>
            </tr>

            <tr>
                <td><label for="emailAddressConfirmation">Email address confirmation</label></td>
                <td><form:input type="text" id="emailAddressConfirmation" path="emailAddressConfirmation"/></td>
                <td><c:out value="${emailAddressConstraint}" /></td>
            </tr>

            <tr>
                <td><label for="password">Password</label></td>
                <td><form:input type="password" id="password" path="password"/></td>
                <td><form:errors path="password" /></td>
            </tr>

            <tr>
                <td><label for="passwordConfirmation">Password confirmation</label></td>
                <td><form:input type="password" id="passwordConfirmation" path="passwordConfirmation" /></td>
                <td><c:out value="${passwordConstraint}" /></td>
            </tr>
            <tr>
                <td><button class="btn btn-primary" type="submit">register</button></td>
            </tr>
    </form:form>
</body>
</html>
