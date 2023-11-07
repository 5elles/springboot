<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>


<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>customer</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <div>
            <div>
                <table>
                    <tr>
                        <td colspan="4">персональные данные клиента</td>
                    </tr>
                    <tr>
                        <th colspan="3"><b>${customerFullData.lastName} ${customerFullData.firstName} ${customerFullData.middleName}</b></th>
                        <td colspan="1">${customerFullData.dateOfBirth.getDayOfMonth()}.${customerFullData.dateOfBirth.getMonthValue()}.${customerFullData.dateOfBirth.getYear()} г.р.</td>
                    </tr>
                    <tr>
                        <td>личный №</td>
                        <td colspan="1"><b>${customerFullData.citizenIdNumber}</b></td>

                    </tr>
                    <tr>
                        <td>паспорт </td>
                        <td colspan="1"><b>${customerFullData.passportNumber}</b></td>
                    </tr>
                    <tr></tr>
                    <tr><td colspan="4">контактные данные</td></tr>
                    <tr>
                        <td rowspan="${customerFullData.phoneNumbers.size()}">телефон</td>
                        <c:forEach items="${customerFullData.phoneNumbers}" var="item">
                        <td colspan="1">${item.phoneNumber}</td>
                    </tr>
                    </c:forEach>
                    <tr>
                        <td rowspan="${customerFullData.emails.size()}">email</td>
                        <c:forEach items="${customerFullData.emails}" var="item">
                        <td colspan="1">${item.email}</td>
                    </tr>
                    </c:forEach>
                    <tr><td colspan="4">адрес</td></tr>
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
                    <tr></tr>
                    <tr>
                        <td colspan="1">
                            <form action="/addEmail?cid=${customerFullData.id}" method="post" >
                                <button class="btn success" id="add-email">добавить email</button>
                            </form>
                        </td>
                        <td colspan="2">
                            <form action="/addPhoneNumber?cid=${customerFullData.id}" method="post" >
                                <button class="btn success" id="add-phone">добавить телефон</button>
                            </form>
                        </td>
                        <td colspan="1">
                            <form action="" method="post" >
                                <button class="btn success" id="add-address">добавить адрес</button>
                            </form>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <br>
        <c:if test="${customerFullData.closureDate != null}">
            <div>
                <div class="container">
                    <h3>Регистрация нового договора банковского обслуживания</h3>
                    <form id="add_form" class="form" name="newContract" action="/customer?cid=${customerFullData.id}" method="post" >
                        <fieldset>
                            <legend>реквизиты договора</legend>
                            <div class="field">
                                <label for="agreementNumber">номер договора</label>
                                <input type="text" name="agreementNumber" id="agreementNumber" required />
                            </div>
                            <div class="field">
                                <label for="agreementDate">дата подписания</label>
                                <input type="date" name="agreementDate" id="agreementDate" required/>
                            </div>
                            <label for="id"></label>
                            <input hidden="hidden" name="id" id="id" value="${customerFullData.id}">
                            <label for="citizenID"></label>
                            <input hidden="hidden" name="citizenID" id="citizenID" value="${customerFullData.citizenIdNumber}">
                        </fieldset>
                        <button class="btn success" type="submit" name="Add">сохранить</button>
                    </form>
                </div>
            </div>
        </c:if>
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
                                            <form action="" >
                                                <button id="disabled-button" disabled>расторгнуть</button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form action="/terminate?cid=${customerFullData.id}" method="post" >
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
        <br>
        <div>
            <div>
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <td colspan="6" align="center">
                            банковские счета клиента
                        </td>
                    </tr>
                    <tr>
                        <th>дата открытия</th>
                        <th>номер счета</th>
                        <th>валюта счета</th>
                        <th>баланс в валюте счета</th>
                        <th>баланс в BYN</th>
                        <th>дата закрытия</th>
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
                                                <form action="" >
                                                    <button class="btn danger" disabled>закрыть</button>
                                                </form>
                                            </c:when>
                                            <c:otherwise>
                                                <form name="closeAccount" action="/closeAccount?aid=${entity.id}&cid=${customerFullData.id}" method="post">
                                                    <button class="btn danger" type="submit">закрыть</button>
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
                    <tr>
                        <td colspan="6">
                            <c:choose>
                                <c:when test="${customerFullData.closureDate == null}">
                                    <form action="/newbankaccount?cid=${customerFullData.id}" method="post" >
                                        <button class="btn success" id="add-button">добавить счет</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <form action="" method="post" >
                                        <button class="btn success" disabled id="add-button_disabled">добавить счет</button>
                                    </form>
                                </c:otherwise>
                            </c:choose>

                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </body>
</html>