<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<html>
    <head>
        <title>employees</title>
    </head>

    <body>
        <table border="1" cellspacing="0" cellpadding="12">
            <tr>
                <td colspan="4" align="center">Список сотрудников банка</td>
            </tr>
            <tr>
                <th>фио</th>
                <th>дата рождения</th>
            </tr>
            <c:forEach items="${employees}" var="item">
                <tr>
                    <td>
                        <b><a href="/employee?id=${item.id}">${item.lastName}</a></b>
                            ${item.firstName} ${item.middleName}</td>
                    <td align="center">${item.dateOfBirth.getDayOfMonth()}.
                            ${item.dateOfBirth.getMonthValue()}.
                            ${item.dateOfBirth.getYear()}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
    </body>
</html>
