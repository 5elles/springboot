<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>customer</title>
</head>
<body>
Здравствуйте, ${customer.person.firstName} ${customer.person.middleName}!

<p>
    Текущие курсы валют:
    <c:forEach var="entry" items="${pageScope.currencyRates}">
        <tr>
            <td><c:out value="${entry.key}"/></td>
            <td><c:out value="${entry.value}"/> </td>
        </tr>
    </c:forEach>
</p>

<p>
    Ваши счета:
<ul>
    <c:forEach items = "${customer.bankAccounts}" var = "entity">
        <li>
            <a href="/ibank/account/${entity.id}">${entity.accountNumber}</a>
            , ${entity.currentBalance} ${entity.currency.currencyAbbreviation}
            <c:if test="${entity.currency.id ne 2}">
                 (${entity.currentBalance * entity.currency.currencyRate} BYN)
            </c:if>
            <br>
        </li>
    </c:forEach>
</ul>
</p>


</body>
</html>

