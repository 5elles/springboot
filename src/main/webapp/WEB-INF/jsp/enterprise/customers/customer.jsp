<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>customer</title>
    </head>
    <body>
        <div>
            <div>
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <td colspan="4" align="center">персональные данные клиента</td>
                    </tr>
                    <tr>
                        <th colspan="3"><b>${customerFullData.lastName} ${customerFullData.firstName} ${customerFullData.middleName}</b></th>
                        <td>${customerFullData.dateOfBirth.getDayOfMonth()}.${customerFullData.dateOfBirth.getMonthValue()}.${customerFullData.dateOfBirth.getYear()}</td>
                    </tr>
                    <tr>
                        <td>личный №</td>
                        <td colspan="3"><b>${customerFullData.citizenIdNumber}</b></td>

                    </tr>
                    <tr>
                        <td>паспорт </td>
                        <td colspan="3"><b>${customerFullData.passportNumber}</b></td>
                    </tr>
                    <tr></tr>
                    <tr><td colspan="4" align="center">контактные данные</td></tr>
                    <tr>
                        <td rowspan="${customerFullData.phoneNumbers.size()}">телефон</td>
                        <c:forEach items="${customerFullData.phoneNumbers}" var="item">
                        <td colspan="3">${item.phoneNumber}</td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td rowspan="${customerFullData.emails.size()}">email</td>
                        <c:forEach items="${customerFullData.emails}" var="item">
                        <td colspan="3">${item.email}</td>
                    </tr>
                    </c:forEach>
                    <tr><td colspan="4" align="center">адрес</td></tr>
                    <tr>
                        <td colspan="4">
                            <ol>
                                <c:forEach items="${customerFullData.addresses}" var="entity">
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
            </div>
        </div>
        <br>
        <div>
            <div>
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <td colspan="6" align="center">договор обслуживания клиента</td>
                    </tr>
                    <tr>
                        <th>дата заключения</th>
                        <th>номер договора</th>
                        <th>дата расторжения</th>
                    </tr>
                    <tr>
                        <td>${customerFullData.agreementDate.getDayOfMonth()}.${customerFullData.agreementDate.getMonthValue()}.${customerFullData.agreementDate.getYear()}</td>
                        <td>${customerFullData.agreementNumber}</td>
                        <td>
                            <c:choose>
                                <c:when test="${customerFullData.closureDate == null}">
                                    <c:set var="activeAccounts" scope="session" value="${0}"></c:set>
                                    <c:forEach items="${customerFullData.bankAccounts}" var="item">
                                        <c:if test="${item.closureDate == null}">
                                            <c:set var="activeAccounts" scope="session" value="${activeAccounts+1}"></c:set>
                                        </c:if>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${activeAccounts > 0}">
                                            <form action="/newcustomer?pid=${data.id}" >
                                                <button id="disabled-button" disabled>расторгнуть</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form action="/newcustomer?pid=${data.id}" >
                                                <button id="delete-button">расторгнуть</button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                                <c:otherwise>
                                    ${customerFullData.closureDate.getDayOfMonth()}.${customerFullData.closureDate.getMonthValue()}.${customerFullData.closureDate.getYear()}
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <br>
        <div>
            <div>
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <td colspan="6" align="center">банковские счета клиента</td>
                    </tr>
                    <tr>
                        <th>Дата открытия</th>
                        <th>Номер счета</th>
                        <th>Валюта счета</th>
                        <th>Баланс в валюте счета</th>
                        <th>Баланс в BYN</th>
                        <th>Дата закрытия</th>
                    </tr>
                    <c:forEach items = "${customerFullData.bankAccounts}" var = "entity">
                        <tr>
                            <td>${entity.openingDate.getDayOfMonth()}.${entity.openingDate.getMonthValue()}.${entity.openingDate.getYear()}</td>
                            <td><a href="/bankAccount?id=${entity.id}">${entity.accountNumber}</a></td>
                            <td>${entity.currencyAbbreviation}</td>
                            <td>${entity.currentBalance}</td>
                            <td>${entity.currencyRate * entity.currentBalance}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${entity.closureDate == null}">
                                        <c:choose>
                                            <c:when test="${entity.currentBalance > 0}">
                                                <form action="/closeaccount?aid=${entity.id}" >
                                                    <button disabled>закрыть</button>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <form action="/closeaccount?aid=${entity.id}" >
                                                    <button>закрыть</button>
                                                </form>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:when>
                                    <c:otherwise>
                                        ${entity.closureDate.getDayOfMonth()}.${entity.closureDate.getMonthValue()}.${entity.closureDate.getYear()}
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </body>
</html>