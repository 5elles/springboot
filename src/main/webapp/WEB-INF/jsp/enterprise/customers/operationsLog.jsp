<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>log</title>
</head>
<body>
<div style="width: 100%; display: flex">
    <div style="justify-content: center">
        <table border="1" cellspacing="0" cellpadding="12">
            <tr>
                <td colspan="7" align="center">реестр банковских операций</td>
            </tr>
            <tr>
                <th>дaта и время операции</th>
                <th>счет списания</th>
                <th>сумма списания</th>
                <th>валюта списания</th>
                <th>счет зачисления</th>
                <th>валюта зачисления</th>
                <th>сумма в валюте зачисления</th>
            </tr>

            <c:forEach items="${orders}" var="item">
                <tr>
                    <td>
                        <a href="/po?id=${item.id}">
                                ${item.timeStamp.getDayOfMonth()}.${item.timeStamp.getMonthValue()}.${item.timeStamp.getYear()}

                                ${item.timeStamp.getHour()}:${item.timeStamp.getMinute()}:${item.timeStamp.getSecond()}
                        </a>
                    </td>
                    <td>${item.fromAccountNumber}</td>
                    <td>${item.amount}</td>
                    <td>${item.fromAccountCurrencyAbbreviation}</td>
                    <td>${item.toAccountNumber}</td>
                    <td>${item.toAccountCurrencyAbbreviation}</td>
                    <td>${item.amount * item.fromAccountCurrencyRate / item.toAccountCurrencyRate}</td>
                </tr>
            </c:forEach>

        </table>
    </div>
</div>

</body>
</html>