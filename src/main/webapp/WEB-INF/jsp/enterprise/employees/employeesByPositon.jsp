<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="/css/style.css">

    <section>
        <div class="tables">
            <div class="table">
                <table <%=style%>>
                    <tr>
                        <th colspan="4">cписок ${positionName}ов банка на текущую дату</th>
                    </tr>
                    <tr>
                        <th colspan="2">паспортные данные</th>
                        <th colspan="2">позиция в банке</th>
                    </tr>
                    <c:forEach items="${data}" var="item">
                        <tr>
                            <td colspan="2">
                                <a href="/employee?id=${item.id}">${item.lastName} ${item.firstName} ${item.middleName}</a>,
                                    ${item.dateOfBirth.getDayOfMonth()}.${item.dateOfBirth.getMonthValue()}.${item.dateOfBirth.getYear()} г.р. <br>
                                ID ${item.citizenIdNumber}, паспорт ${item.passportNumber}
                            </td>
                            <td colspan="2">
                                <c:forEach items="${item.wageRates}" var="item">
                                    <c:choose>
                                        <c:when test="${item.finishDate == null}">
                                            c ${item.startDate.getDayOfMonth()}.${item.startDate.getMonthValue()}.${item.startDate.getYear()} по н.в. -
                                        </c:when>
                                        <c:otherwise>
                                            c ${item.startDate.getDayOfMonth()}.${item.startDate.getMonthValue()}.${item.startDate.getYear()} по
                                            ${item.finishDate.getDayOfMonth()}.${item.finishDate.getMonthValue()}.${item.finishDate.getYear()} -
                                        </c:otherwise>
                                    </c:choose>
                                    ${item.positionName}<br>
                                </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </section>

<%@ include file="footer.jsp" %>
