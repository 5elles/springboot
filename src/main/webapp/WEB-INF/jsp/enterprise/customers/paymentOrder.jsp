<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>log</title>
    </head>
    <body>
        <table border="1" cellspacing="0" cellpadding="12">
            <tr>
                <td colspan="2" align="center"><b>платёжное поручение</b></td>
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
                <td style="color: crimson">${order.fromAccountNumber}</td>
            </tr>
            <tr>
                <td>владелец счета</td>
                <td>${order.fromAccountCustomerLastName}
                    ${order.fromAccountCustomerFirstName}
                    ${order.fromAccountCustomerMiddleName}</td>
            </tr>
            <tr>
                <td>личный номер</td><td>${order.fromAccountCustomerCitizenIdNumber}</td>
            </tr>


                <th colspan="2" ><b>получатель</b></th>
            </tr>
            <tr>
            <tr>
                <td>номер счета</td>
                <td style="color: crimson">${order.toAccountNumber}</td>
            </tr>
            <tr>
                <td>владелец счета</td>
                <td>${order.toAccountCustomerLastName}
                    ${order.toAccountCustomerFirstName}
                    ${order.toAccountCustomerMiddleName}</td>
            </tr>
            <tr>
                <td>личный номер</td><td>${order.toAccountCustomerCitizenIdNumber}</td>
            </tr>

            <tr>
                <td>cумма в валюте платежа</td>
                <td>${order.amount} ${order.fromAccountCurrencyAbbreviation}</td>
            </tr>
        </table>
    </body>
</html>