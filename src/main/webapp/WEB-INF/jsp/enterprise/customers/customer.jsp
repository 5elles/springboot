<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="/css/style.css">


    <section>
        <div class="tables">
            <div class="table">
                <table <%=style%>>
                    <tr>
                        <th colspan="4">персональные данные клиента</th>
                    </tr>
                    <tr>
                        <th colspan="2">${customerFullData.lastName} ${customerFullData.firstName} ${customerFullData.middleName}</th>
                        <td colspan="2">${customerFullData.dateOfBirth.getDayOfMonth()}.${customerFullData.dateOfBirth.getMonthValue()}.${customerFullData.dateOfBirth.getYear()} г.р.</td>
                    </tr>
                    <tr>
                        <td colspan="2">личный №</td>
                        <td colspan="2"><b>${customerFullData.citizenIdNumber}</b></td>
                    </tr>
                    <tr>
                        <td colspan="2">паспорт </td>
                        <td colspan="2"><b>${customerFullData.passportNumber}</b></td>
                    </tr>
                    <tr></tr>
                    <tr><th colspan="4">контактные данные</th></tr>
                    <c:choose>
                        <c:when test="${customerFullData.phoneNumbers.size() == 0}">
                            <tr>
                                <td colspan="2">телефон</td>
                                <td colspan="2"></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                            <td rowspan="${customerFullData.phoneNumbers.size()}" colspan="2">телефон</td>
                            <c:forEach items="${customerFullData.phoneNumbers}" var="item">
                                <td colspan="2">${item.phoneNumber}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                    <c:choose>
                        <c:when test="${customerFullData.emails.size() == 0}">
                            <tr>
                                <td colspan="2">email</td>
                                <td colspan="2"></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                            <td rowspan="${customerFullData.emails.size()}" colspan="2">email</td>
                            <c:forEach items="${customerFullData.emails}" var="item">
                                <td colspan="2">${item.email}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    <tr><th colspan="4">адрес</th></tr>
                    <tr>
                        <td colspan="4">
                            <ul>
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
                            </ul>
                        </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td colspan="1" class="wrapper">
                            <a href="/addEmailCustomer?cid=${customerFullData.id}" class="btn success">добавить email</a>
                        </td>
                        <td colspan="2" class="wrapper">
                            <a href="/addPhoneNumberCustomer?cid=${customerFullData.id}" class="btn success">добавить телефон</a>
                        </td>
                        <td colspan="1" class="wrapper">
                            <a href="/newAddress?cid=${customerFullData.id}" class="btn success">добавить адрес</a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="table">
                <c:if test="${customerFullData.closureDate != null}">
                    <div class="container">
                        <h3>Регистрация нового договора банковского обслуживания</h3>
                        <form id="add_form" class="form" name="newContract" action="/customer?id=${customerFullData.id}" method="post" >
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
                                <input type="hidden" name="id" id="id" value="${customerFullData.id}">
                                <input type="hidden" name="citizenID" id="citizenID" value="${customerFullData.citizenIdNumber}">
                            </fieldset>
                            <button class="btn success" type="submit" name="Add">сохранить</button>
                        </form>
                    </div>
                </c:if>
            </div>
            <div class="table">
                <table <%=style%>>
                    <tr>
                        <th colspan="6">договор обслуживания клиента</th>
                    </tr>
                    <tr>
                        <th>дата заключения</th>
                        <th>номер договора</th>
                        <th>дата расторжения</th>
                    </tr>
                    <tr>
                        <td>${customerFullData.agreementDate.getDayOfMonth()}.${customerFullData.agreementDate.getMonthValue()}.${customerFullData.agreementDate.getYear()}</td>
                        <td>${customerFullData.agreementNumber}</td>
                        <td class="wrapper">
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
                                                <a href="" class="btn default">расторгнуть</a>
                                        </c:when>
                                        <c:otherwise>
                                                <a href="/terminate?cid=${customerFullData.id}" class="btn danger">расторгнуть</a>
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

            <div class="table">
                <table <%=style%>>
                    <tr>
                        <th colspan="6">
                            банковские счета клиента
                        </th>
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
                            <td class="wrapper">
                                <c:choose>
                                    <c:when test="${entity.closureDate == null}">
                                        <c:choose>
                                            <c:when test="${entity.currentBalance > 0}">
                                                <a href="" class="btn default">закрыть</a>
                                            </c:when>
                                            <c:otherwise>
                                                    <a href="/closeAccount?aid=${entity.id}&cid=${customerFullData.id}" class="btn danger">закрыть</a>
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
                        <td colspan="6" class="wrapper">
                            <c:choose>
                                <c:when test="${customerFullData.closureDate == null}">
                                        <a href="/newbankaccount?cid=${customerFullData.id}" class="btn success">добавить счет</a>
                                </c:when>
                                <c:otherwise>
                                        <a href="" class="btn default">добавить счет</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </section>

<%@ include file="footer.jsp" %>