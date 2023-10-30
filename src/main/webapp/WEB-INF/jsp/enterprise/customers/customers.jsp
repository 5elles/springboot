<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>customers</title>
    </head>


    <body>
        <table border="1" cellspacing="0" cellpadding="12">
            <tr>
                <td colspan="6" align="center">Список клиентов банка</td>
            </tr>
            <tr>
                <th>фио</th>
                <th>договор</th>
            </tr>
            <c:forEach items="${customers}" var="item">
                <tr>
                    <td><b><a href="/customer?id=${item.id}">${item.lastName}</a></b>
                     ${item.firstName} ${item.middleName}</td>
                    <td align="center">${item.agreementNumber} от ${item.agreementDate.getDayOfMonth()}.${item.agreementDate.getMonthValue()}.${item.agreementDate.getYear()}г.</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="/operationsLog">Журнал учета банковских операций</a>
    </body>
</html>