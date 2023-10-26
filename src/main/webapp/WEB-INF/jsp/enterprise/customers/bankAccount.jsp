<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>account</title>
</head>
<body>
<div>

    <table border="1" cellspacing="0" cellpadding="12">
        <tr>
            <td colspan="2" align="center"><b>реквизиты счета</b></td>
        </tr>
        <tr>
            <td>Получатель</td>
            <th>${customer.person.lastName} ${customer.person.firstName} ${customer.person.middleName}</th>
        </tr>
        <tr align="left">
            <td>Номер счета</td>
            <th style="color: crimson">${bankAccount.accountNumber}</th>
        </tr>
        <tr align="left">
            <td>Банк получателя</td>
            <th>Bank</th>
        </tr>
        <tr align="left">
            <td>БИК</td>
            <th>04452526</th>
        </tr>
        <tr align="left">
            <td>Корр.счет</td>
            <th>301832497912444329379235</th>
        </tr>
        <tr align="left">
            <td>SWIFT-код</td>
            <th>SABRRUMM</th>
        </tr>
    </table>
</div>
<div>
    <p>
        Остаток по счету: <b>${bankAccount.currentBalance}</b> ${bankAccount.currency.currencyAbbreviation}
    </p>
</div>

<div>
    <table border="1" cellspacing="0" cellpadding="12">
        <tr>
            <td colspan="5" align="center"><b>все операции по счету</b></td>
        </tr>
        <tr>
            <th>Дата и время операции</th>
            <th>Счет отправителя</th>
            <th>Счет получателя</th>
            <th>Сумма</th>
            <th>Валюта операции</th>
        </tr>
        <c:forEach items="${allBankOrders}" var="item">
            <tr>
                <td>
                        ${item.timeStamp.getDayOfMonth()}.${item.timeStamp.getMonthValue()}.${item.timeStamp.getYear()}

                        ${item.timeStamp.getHour()}:${item.timeStamp.getMinute()}:${item.timeStamp.getSecond()}
                </td>
                <td>${item.fromAccount.accountNumber}</td>
                <td>${item.toAccount.accountNumber}</td>
                <td>${item.amount}</td>
                <td>${item.fromAccount.currency.currencyAbbreviation}</td>
            </tr>
        </c:forEach>
    </table>
</div>

<br>
<div>

    <table border="1" cellspacing="0" cellpadding="12">
        <tr>
            <td colspan="6" align="center"><b>поступления на счет</b></td>
        </tr>
        <tr>
            <th>Дата и время операции</th>
            <th>Счет отправителя</th>
            <th>Счет получателя</th>
            <th>Сумма</th>
            <th>Валюта операции</th>
        </tr>
        <c:forEach items="${toAccountOrders}" var="item">
            <tr>
                <td>
                        ${item.timeStamp.getDayOfMonth()}.${item.timeStamp.getMonthValue()}.${item.timeStamp.getYear()}

                        ${item.timeStamp.getHour()}:${item.timeStamp.getMinute()}:${item.timeStamp.getSecond()}
                </td>
                <td>${item.fromAccount.accountNumber}</td>
                <td>${item.toAccount.accountNumber}</td>
                <td>${item.amount}</td>
                <td>${item.fromAccount.currency.currencyAbbreviation}</td>
            </tr>
        </c:forEach>
    </table>
</div>


<br>
<div>
    <table border="1" cellspacing="0" cellpadding="12">
        <tr>
            <td colspan="5" align="center"><b>списания со счета</b></td>
        </tr>
        <tr>
            <th>Дата и время операции</th>
            <th>Счет отправителя</th>
            <th>Счет получателя</th>
            <th>Сумма</th>
            <th>Валюта операции</th>
        </tr>
        <c:forEach items="${fromAccountOrders}" var="item">
            <tr>
                <td>
                        ${item.timeStamp.getDayOfMonth()}.${item.timeStamp.getMonthValue()}.${item.timeStamp.getYear()}

                        ${item.timeStamp.getHour()}:${item.timeStamp.getMinute()}:${item.timeStamp.getSecond()}
                </td>
                <td>${item.fromAccount.accountNumber}</td>
                <td>${item.toAccount.accountNumber}</td>
                <td>${item.amount}</td>
                <td>${item.fromAccount.currency.currencyAbbreviation}</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>

