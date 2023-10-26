<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>customer</title>
    </head>
    <body>
        Здравствуйте, <b>${customer.person.firstName} ${customer.person.middleName}</b>!

        <br>
        <p>
            <b>Текущие курсы валют:</b>
            <table>
                <c:forEach var="entry" items="${currencies}">
                    <tr>
                        <td><c:out value="${entry.currencyAbbreviation}"    /></td>
                        <td><c:out value="${entry.currencyRate}"/> </td>
                    </tr>
                </c:forEach>
            </table>
        </p>

        <br>

        <table border="1" cellspacing="0" cellpadding="12">
            <tr>
                <td colspan="6" align="center">Ваши счета</td>
            </tr>
            <tr>
                <th>Дата открытия</th>
                <th>Номер счета</th>
                <th>Валюта счета</th>
                <th>Баланс в валюте счета</th>
                <th>Баланс в BYN</th>
                <th>Дата закрытия</th>
            </tr>
            <c:forEach items = "${customer.bankAccounts}" var = "entity">
                <tr>
                    <td>${entity.openingDate.getDayOfMonth()}.${entity.openingDate.getMonthValue()}.${entity.openingDate.getYear()}</td>
                    <td><a href="/account?aid=${entity.id}&cid=${customer.id}">${entity.accountNumber}</a></td>
                    <td>${entity.currency.currencyAbbreviation}</td>
                    <td>${entity.currentBalance}</td>
                    <td>${entity.currency.currencyRate * entity.currentBalance}</td>
                    <td>${entity.closureDate.getDayOfMonth()}.${entity.closureDate.getMonthValue()}.${entity.closureDate.getYear()}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

