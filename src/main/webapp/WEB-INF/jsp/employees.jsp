<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
    <head>
        <title>Employees</title>
    </head>

    <body>
        Employee list

        <br>
        <ul>
        <c:forEach items = "${employees}" var = "employee">
            <li>
                <a href="employee"></a>
                ${employee.person.citizenIdNumber},
                        ${employee.person.lastName} ${employee.person.firstName} ${employee.person.middleName},  ${employee.person.dateOfBirth} <br>
            </li>
        </c:forEach>
        </ul>
    </body>
</html>
