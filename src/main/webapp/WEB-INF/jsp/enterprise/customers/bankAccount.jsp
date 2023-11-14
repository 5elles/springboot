<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

    <div>

        <table border="1" cellspacing="0" cellpadding="12">
            <tr>
                <td colspan="2" align="center"><b>реквизиты счета</b></td>
            </tr>
            <tr>
                <td>Получатель</td>
                <th>${data.accountHolderLastName} ${data.accountHolderFirstName} ${data.accountHolderMiddleName}</th>
            </tr>
            <tr align="left">
                <td>Номер счета</td>
                <th style="color: crimson">${data.accountNumber}</th>
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
            Остаток по счету: <b>${data.currentBalance}</b> ${data.currencyAbbreviation}
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
                <th>Валюта отправителя</th>
            </tr>
            <c:forEach items="${data.allPayments}" var="item">
                <tr>
                    <td>
                            ${item.timeStamp.getDayOfMonth()}.${item.timeStamp.getMonthValue()}.${item.timeStamp.getYear()}

                            ${item.timeStamp.getHour()}:${item.timeStamp.getMinute()}:${item.timeStamp.getSecond()}
                    </td>
                    <td>${item.fromAccountNumber}</td>
                    <td>${item.toAccountNumber}</td>
                    <td>${item.amount}</td>
                    <td>${item.fromAccountCurrencyAbbreviation}</td>
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
                <th>Валюта отправителя</th>
            </tr>
            <c:forEach items="${data.incomingPayments}" var="item">
                <tr>
                    <td>
                            ${item.timeStamp.getDayOfMonth()}.${item.timeStamp.getMonthValue()}.${item.timeStamp.getYear()}

                            ${item.timeStamp.getHour()}:${item.timeStamp.getMinute()}:${item.timeStamp.getSecond()}
                    </td>
                    <td>${item.fromAccountNumber}</td>
                    <td>${item.toAccountNumber}</td>
                    <td>${item.amount}</td>
                    <td>${item.fromAccountCurrencyAbbreviation}</td>
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
                <th>Валюта отправителя</th>
            </tr>
            <c:forEach items="${data.outgoingPayments}" var="item">
                <tr>
                    <td>
                            ${item.timeStamp.getDayOfMonth()}.${item.timeStamp.getMonthValue()}.${item.timeStamp.getYear()}

                            ${item.timeStamp.getHour()}:${item.timeStamp.getMinute()}:${item.timeStamp.getSecond()}
                    </td>
                    <td>${item.fromAccountNumber}</td>
                    <td>${item.toAccountNumber}</td>
                    <td>${item.amount}</td>
                    <td>${item.fromAccountCurrencyAbbreviation}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
<%@ include file="footer.jsp" %>
