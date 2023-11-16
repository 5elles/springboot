<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

        Мы рады Вам, <b>${customer.firstName} ${customer.middleName}</b>!

        <br>
        <div class="container">
            <div>
                <h3>Текущие курсы валют:</h3>
                <table>
                    <c:forEach var="entry" items="${customer.currencies}">
                        <tr>
                            <td><c:out value="${entry.currencyAbbreviation}"    /></td>
                            <td><c:out value="${entry.currencyRate}"/> </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        </p>

        <br>

        <table border="1" cellspacing="0" cellpadding="12">
            <tr>
                <th colspan="6" >Ваши счета</th>
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
                    <td><a href="/account?aid=${entity.id}">${entity.accountNumber}</a></td>
                    <td>${entity.currencyAbbreviation}</td>
                    <td>${entity.currentBalance}</td>
                    <td>${Math.round(entity.currencyRate * entity.currentBalance *100)/100}</td>
                    <td>
                        <c:choose>
                            <c:when test="${entity.closureDate != null}">
                                ${entity.closureDate.getDayOfMonth()}.${entity.closureDate.getMonthValue()}.${entity.closureDate.getYear()}
                            </c:when>
                            <c:otherwise>
                                <c:choose>
                                    <c:when test="${entity.currentBalance > 0}">
                                        <form action="" >
                                            <button class="btn default" disabled>закрыть</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form name="closeAccount" action="/ibankCloseAccount?aid=${entity.id}&cid=${customer.id}" method="post">
                                            <button class="btn danger" type="submit">закрыть</button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </table>

<%@ include file="footer.jsp" %>

