<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
        <div>
            <h3>Управление персоналом</h3>

            <div>
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <th colspan="4">cписок сотрудников банка</th>
                    </tr>
                    <tr>
                        <th>паспортные данные</th>
                        <th colspan="3">позиция в банке</th>
                    </tr>
                    <c:forEach items="${employees}" var="item">
                        <tr>
                            <td>
                                <a href="/employee?id=${item.id}">${item.lastName} ${item.firstName} ${item.middleName}</a>,
                                    ${item.dateOfBirth.getDayOfMonth()}.${item.dateOfBirth.getMonthValue()}.${item.dateOfBirth.getYear()} г.р. <br>
                                ID ${item.citizenIdNumber}, паспорт ${item.passportNumber}
                            </td>
                            <td>
                                <c:forEach items="${item.wageRates}" var="item">
                                    <c:choose>
                                        <c:when test="${item.finishDate == null}">
                                            c ${item.startDate.getDayOfMonth()}.${item.startDate.getMonthValue()}.${item.startDate.getYear()} по н.в. -
                                        </c:when>
                                        <c:otherwise>
                                            c ${item.startDate.getDayOfMonth()}.${item.startDate.getMonthValue()}.${item.startDate.getYear()}
                                            по ${item.finishDate.getDayOfMonth()}.${item.finishDate.getMonthValue()}.${item.finishDate.getYear()} -
                                        </c:otherwise>
                                    </c:choose>
                                    ${item.positionName}<br>
                                </c:forEach>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <br>
<%@ include file="footer.jsp" %>
