<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
        <div>
            <table border="1" cellspacing="0" cellpadding="12">
                <tr>
                    <th colspan="2">реквизиты счета</th>
                </tr>

                <tr>
                    <td>Получатель</td><th>${data.accountHolderLastName} ${data.accountHolderFirstName} ${data.accountHolderMiddleName}</th>
                </tr>

                <tr align="left">
                    <td>Номер счета</td> <th style="color: crimson">${data.accountNumber}</th>
                </tr>

                <tr align="left">
                    <td>Банк получателя</td> <th>Bank</th>
                </tr>

                <tr align="left">
                    <td>БИК</td> <th>04452526</th>
                </tr>

                <tr align="left">
                    <td>Корр.счет</td> <th>301832497912444329379235</th>
                </tr>

                <tr align="left">
                    <td>SWIFT-код</td><th>SABRRUMM</th>
                </tr>

                <tr>
                    <td>Остаток по счету</td>
                    <th><b>${data.currentBalance}</b> ${data.currencyAbbreviation}</th>
                </tr>
            </table>
        </div>

        <c:if test="${data.closureDate == null}">
            <div>
                <div class="container">
                    <h3>перевести на другой счет:</h3>
                        <form id="add_form" name="payment" action="/account?aid=${data.id}" method="post" >
                            <fieldset>
                                <legend>платежные реквизиты</legend>
                                <div class="field">
                                    <label for="amount">cумма,  ${data.currencyAbbreviation}</label>
                                    <input type="number" name="amount" id="amount" required value="0" min="0.1" step="any"/>
                                </div>

                                <div class="field">
                                    <label for="toAccountNumber">cчет получателя</label>
                                    <input type="text" name="toAccountNumber" id="toAccountNumber" required/><br/>
                                </div>
                                <input type="hidden" name="fromAccountId" id="fromAccountId" value="${data.id}"/><br/>
                            </fieldset>
                            <button type="submit" name="Add">перевести</button>
                        </form>
                </div>
            </div>
        </c:if>
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
