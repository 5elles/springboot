<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

<section>
    <div class="tables">
<%--        <div class="table">--%>
<%--                <table <%=style%>>--%>
<%--                    <tr>--%>
<%--                        <th colspan="2">текущие курсы валют</th>--%>
<%--                    </tr>--%>
<%--                    <c:forEach var="entry" items="${data.currencies}">--%>
<%--                        <tr>--%>
<%--                            <td><c:out value="${entry.currencyAbbreviation}"    /></td>--%>
<%--                            <td><c:out value="${entry.currencyRate}"/> </td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
<%--                </table>--%>
<%--        </div>--%>
        <div class="table">
            <table <%=style%>>
                <tr>
                    <th colspan="6" >ваши счета</th>
                </tr>
                <tr>
                    <th>дата открытия</th>
                    <th>номер счета</th>
                    <th>валюта счета</th>
                    <th>баланс в валюте счета</th>
                    <th>баланс в BYN</th>
                    <th>дата закрытия</th>
                </tr>
                <c:forEach items = "${data.bankAccounts}" var = "entity">
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
                                            <a href="" class="btn default">закрыть</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="/ibankCloseAccount?aid=${entity.id}&cid=${data.id}&cid=${data.id}" class="btn danger">закрыть</a>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>


<%@ include file="footer.jsp" %>

