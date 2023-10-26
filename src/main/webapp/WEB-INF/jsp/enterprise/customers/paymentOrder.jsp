<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>log</title>
    </head>
    <body>
    <c:set var="fromPerson" value="${order.fromAccount.customer.person}" scope="session"/>
    <c:set var="toPerson" value="${order.toAccount.customer.person}" scope="session"/>

        <table border="1" cellspacing="0" cellpadding="12">
            <tr>
                <td colspan="2" align="center">платёжное поручение</td>
            </tr>
            <tr>
                <td>Дата и время операции</td>
                <td>
                    ${order.timeStamp.getDayOfMonth()}.${order.timeStamp.getMonthValue()}.${order.timeStamp.getYear()}

                    ${order.timeStamp.getHour()}:${order.timeStamp.getMinute()}:${order.timeStamp.getSecond()}
                </td>
            </tr>
            <tr>
                <th colspan="2"><b>отправитель</b></th>
            </tr>

            <tr>
                <td>номер счета</td>
                <td style="color: crimson">${order.fromAccount.accountNumber}</td>
            </tr>
            <tr>
                <td>владелец счета</td>
                <td>${fromPerson.lastName} ${fromPerson.firstName} ${fromPerson.middleName}</td>
            </tr>
            <tr>
                <td>личный номер</td><td>${fromPerson.citizenIdNumber}</td>
            </tr>


                <th colspan="2" ><b>получатель</b></th>
            </tr>
            <tr>
            <tr>
                <td>номер счета</td>
                <td style="color: crimson">${order.toAccount.accountNumber}</td>
            </tr>
            <tr>
                <td>владелец счета</td>
                <td>${toPerson.lastName} ${toPerson.firstName} ${toPerson.middleName}</td>
            </tr>
            <tr>
                <td>личный номер</td><td>${toPerson.citizenIdNumber}</td>
            </tr>

            <tr>
                <td>cумма в валюте платежа</td>
                <td>${order.amount}${order.fromAccount.currency.currencyAbbreviation}</td>
            </tr>

        </table>


    </body>
</html>