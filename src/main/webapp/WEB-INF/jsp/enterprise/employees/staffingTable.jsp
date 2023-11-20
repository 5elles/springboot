
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="/css/style.css">

        <c:set var="totalSalary" scope="session" value="${0}"></c:set>

        <section>
            <div class="tables">
                <div class="table">
                    <table <%=style%>>
                        <tr>
                            <th colspan="5">штатное расписание</th>
                        </tr>
                        <tr>
                            <th colspan="1">должность</th>
                            <th colspan="1">оклад, BYN</th>
                            <th colspan="1">действующих<br>сотрудников</th>
                            <th colspan="1">всего<br>ставок</th>
                            <th colspan="1">затраты по<br>должности, BYN</th>
                        </tr>
                        <c:forEach items="${data}" var="item">
                            <tr>
                                <td>
                                    <a href="/employeesByPosition?posId=${item.id}">${item.positionName}</a>
                                </td>
                                <td>
                                    ${item.salary}
                                </td>
                                <td>
                                    <c:set var="activeWageRates" scope="session" value="${0}"></c:set>
                                    <c:set var="totalWageRates" scope="session" value="${0}"></c:set>
                                    <c:forEach items="${item.wageRates}" var="wageRate">
                                        <c:if test="${wageRate.finishDate == null}">
                                            <c:set var="activeWageRates" scope="session" value="${activeWageRates+1}"></c:set>
                                            <c:set var="totalWageRates" scope="session" value="${totalWageRates + wageRate.rate}"></c:set>
                                        </c:if>
                                    </c:forEach>
                                    ${activeWageRates}
                                </td>
                                <td>
                                    ${totalWageRates}
                                </td>
                                <td>
                                    ${totalWageRates * item.salary}
                                    <c:set var="totalSalary" scope="session" value="${totalSalary + totalWageRates * item.salary}"></c:set>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr class="wrapper">
                            <td colspan="5">
                                фонд оплаты труда: <b class="text-red">${totalSalary}</b> BYN / месяц
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>

<%@ include file="footer.jsp" %>
