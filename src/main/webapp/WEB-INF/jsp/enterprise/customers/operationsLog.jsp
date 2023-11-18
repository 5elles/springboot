<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
        <div>
            <br>
            <div class="container">
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr class="table-title">
                        <th colspan="7">реестр банковских операций</th>
                    </tr>
                    <tr>
                        <th>дaта и время<br>операции</th>
                        <th>счет<br>списания</th>
                        <th>сумма<br>списания</th>
                        <th>валюта<br>списания</th>
                        <th>счет<br>зачисления</th>
                        <th>валюта<br>зачисления</th>
<%--                        <th>сумма в валюте зачисления</th>--%>
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
<%--                            <td>${item.amount * item.fromAccountCurrencyRate / item.toAccountCurrencyRate}</td>--%>
                        </tr>
                    </c:forEach>

                </table>
            </div>
        </div>
<%@ include file="footer.jsp" %>