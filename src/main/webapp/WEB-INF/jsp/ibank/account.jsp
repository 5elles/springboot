<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="/css/style.css">

    <section>
        <div class="tables">
            <div class="table">
                <table <%=style%>>
                    <tr>
                        <th colspan="2">реквизиты счета</th>
                    </tr>
                    <tr>
                        <td>Получатель</td><td>${data.lastName} ${data.firstName} ${data.middleName}</td>
                    </tr>
                    <tr>
                        <td>Номер счета</td> <td style="color: crimson">${data.accountNumber}</td>
                    </tr>
                    <tr>
                        <td>Банк получателя</td> <td>Bank</td>
                    </tr>
                    <tr>
                        <td>БИК</td> <td>04452526</td>
                    </tr>
                    <tr>
                        <td>Корр.счет</td> <td>301832497912444329379235</td>
                    </tr>
                    <tr>
                        <td>SWIFT-код</td><td>SABRRUMM</td>
                    </tr>
                    <tr>
                        <td>Остаток по счету</td>
                        <td>${data.currentBalance} ${data.currencyAbbreviation}</td>
                    </tr>
                </table>
            </div>
            <div class="table">
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
                                        <button class="btn success" type="submit" name="Add">перевести</button>
                                    </form>
                            </div>
                        </div>
                    </c:if>
            </div>
            <div class="table">
                <table <%=style%>>
                    <tr>
                        <th colspan="5">все операции по счету</th>
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
            <div class="table">
                <table <%=style%>>
                    <tr>
                        <th colspan="6">поступления на счет</th>
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
            <div class="table">
                <table <%=style%>>
                    <tr>
                        <td colspan="5"><b>списания со счета</b></td>
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
        </div>
    </section>

<%@ include file="footer.jsp" %>
