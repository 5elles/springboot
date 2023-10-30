<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>employee</title>
    </head>
    <body>

        <p>
            <table border="1" cellspacing="0" cellpadding="12">
                <tr>
                    <td colspan="4" align="center">досье сотрудника</td>
                </tr>
                <tr>
                    <th colspan="3"><b>${employee.lastName} ${employee.firstName} ${employee.middleName}</b></th>
                    <td>${employee.dateOfBirth.getDayOfMonth()}.${employee.dateOfBirth.getMonthValue()}.${employee.dateOfBirth.getYear()}</td>
                </tr>
                <tr>
                    <td>личный №</td>
                    <td colspan="3"><b>${employee.citizenIdNumber}</b></td>

                </tr>
                <tr>
                    <td>паспорт </td>
                    <td colspan="3"><b>${employee.passportNumber}</b></td>
                </tr>
                <tr></tr>
                <tr><td colspan="4" align="center">контактные данные</td></tr>
                <tr>
                    <td rowspan="${employee.phoneNumbers.size()}">телефон</td>
                    <c:forEach items="${employee.phoneNumbers}" var="item">
                    <td colspan="3">${item.phoneNumber}</td>
                </tr>
                </c:forEach>
                <tr>
                    <td rowspan="${employee.emails.size()}">email</td>
                    <c:forEach items="${employee.emails}" var="item">
                    <td colspan="3">${item.email}</td>
                </tr>
                </c:forEach>
                <tr><td colspan="4" align="center">адрес</td></tr>
                <tr>
                    <td colspan="4">
                        <ol>
                            <c:forEach items="${employee.addresses}" var="entity">
                                <li>
                                        ${entity.countryName},
                                        ${entity.regionName},
                                        ${entity.settlementTypeShortName} ${entity.settlementName},
                                        ${entity.streetTypeShortName} ${entity.streetName}
                                    д. ${entity.houseNumber}
                                    кв. ${entity.apartmentNumber}
                                </li>
                            </c:forEach>
                        </ol>
                    </td>
                </tr>
            </table>
        </p>


        <p>
            <table  border="1" cellspacing="0" cellpadding="12">
                <tr>
                    <td colspan="6" align="center">занимаемые должности</td>
                </tr>
                <tr>
                    <th align="center">c</th>
                    <th align="center">по</th>
                    <th align="center">должность</th>
                    <th align="center">оклад, руб</th>
                    <th align="center">ставка</th>
                    <th align="center">сумма, руб</th>
                </tr>
                <c:forEach items="${employee.wageRates}" var="entity">
                    <tr>
                        <td align="center">${entity.startDate.getDayOfMonth()}.${entity.startDate.getMonthValue()}.${entity.startDate.getYear()}</td>
                        <td align="center">${entity.finishDate.getDayOfMonth()}.${entity.finishDate.getMonthValue()}.${entity.finishDate.getYear()}</td>
                        <td align="center">${entity.positionName}</td>
                        <td align="center">${entity.positionSalary}</td>
                        <td align="center">${entity.rate}</td>
                        <td align="center">${entity.rate * entity.positionSalary}</td>
                    </tr>
                </c:forEach>
            </table>
        </p>



<%--            <b>Актуальный уровень допуска:</b>--%>
<%--            <p>--%>
<%--            <table>--%>
<%--                <tr>--%>
<%--                    <th>Должность</th>--%>
<%--                    <th>Репозиторий</th>--%>
<%--                    <th>Банковские данные</th>--%>
<%--                    <th>Персональные данные</th>--%>
<%--                    <th>Хранилища ТМЦ</th>--%>

<%--                </tr>--%>
<%--                <c:forEach items="${employee.wageRates}" var="entity">--%>
<%--                    <c:if test="${entity.finishDate == null}">--%>
<%--                        <tr>--%>
<%--                            <td>${entity.position.positionName}</td>--%>
<%--                            <td>--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${entity.position.clearanceLevel.hasSafeDepositAccess} == 1">--%>
<%--                                        да--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise> нет </c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${entity.position.clearanceLevel.hasBankingRecordsAccess} == 1">--%>
<%--                                        да--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise> нет </c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${entity.position.clearanceLevel.hasPersonnelRecordsAccess} == 1">--%>
<%--                                        да--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise> нет </c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </td>--%>
<%--                            <td>--%>
<%--                                <c:choose>--%>
<%--                                    <c:when test="${entity.position.clearanceLevel.hasStorageRoomsAccess} == 1">--%>
<%--                                        да--%>
<%--                                    </c:when>--%>
<%--                                    <c:otherwise> нет </c:otherwise>--%>
<%--                                </c:choose>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:if>--%>
<%--                </c:forEach>--%>
<%--            </table>--%>
<%--            </p>--%>
    </body>
</html>