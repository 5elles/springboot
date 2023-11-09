<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>employee</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <div>
            <div>
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <th colspan="4" align="center">досье сотрудника</th>
                    </tr>
                    <tr>
                        <th colspan="2"><b>${employee.lastName} ${employee.firstName} ${employee.middleName}</b></th>
                        <td colspan="2">${employee.dateOfBirth.getDayOfMonth()}.${employee.dateOfBirth.getMonthValue()}.${employee.dateOfBirth.getYear()} г.р.</td>
                    </tr>
                    <tr>
                        <td colspan="2">личный №</td>
                        <td colspan="2"><b>${employee.citizenIdNumber}</b></td>

                    </tr>
                    <tr>
                        <td colspan="2">паспорт </td>
                        <td colspan="2"><b>${employee.passportNumber}</b></td>
                    </tr>
                    <tr></tr>
                    <tr><th colspan="4" align="center">контактные данные</th></tr>
                    <tr>
                        <td rowspan="${employee.phoneNumbers.size()}" colspan="2">телефон</td>
                        <c:forEach items="${employee.phoneNumbers}" var="item">
                        <td colspan="2">${item.phoneNumber}</td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td rowspan="${employee.emails.size()}" colspan="2">email</td>
                        <c:forEach items="${employee.emails}" var="item">
                        <td colspan="2">${item.email}</td>
                    </tr>
                    </c:forEach>
                    <tr><th colspan="4" align="center">адрес</th></tr>
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
                    <tr></tr>
                    <tr>
                        <td colspan="1">
                            <form action="/addEmailEmployee?eid=${employee.id}" method="post" >
                                <button class="btn success" id="add-email">добавить email</button>
                            </form>
                        </td>
                        <td colspan="2">
                            <form action="/addPhoneNumberEmployee?eid=${employee.id}" method="post" >
                                <button class="btn success" id="add-phone">добавить телефон</button>
                            </form>
                        </td>
                        <td colspan="1">
                            <form action="/newAddress?cid=${customerFullData.id}" method="post" >
                                <button class="btn default" id="add-address">добавить адрес</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
            <br>
            <div>
                <table  border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <th colspan="6" align="center">занимаемые должности</th>
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
                            <td>
                                <c:choose>
                                    <c:when test="${entity.finishDate == null}">
                                        <form action="/fire?wid=${entity.id}&eid=${employee.id}" method="post" >
                                            <button class="btn danger" id="delete-button">уволить</button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        ${entity.finishDate.getDayOfMonth()}.${entity.finishDate.getMonthValue()}.${entity.finishDate.getYear()}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td align="center">${entity.positionName}</td>
                            <td align="center">${entity.positionSalary}</td>
                            <td align="center">${entity.rate}</td>
                            <td align="center">${entity.rate * entity.positionSalary}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="6">
                            <form action="${pageContext.request.contextPath}/showNewWageRateForm?pid=${employee.personId}" method="post" >
                                <button class="btn success" id="add-button">назначить на должность</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </body>
</html>