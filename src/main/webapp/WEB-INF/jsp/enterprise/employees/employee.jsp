<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="/css/style.css">

        <section>
            <div class="tables">
                <div class="table">
                    <table <%=style%>>
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
                        <c:choose>
                            <c:when test="${employee.phoneNumbers.size() == 0}">
                                <tr>
                                    <td colspan="2">телефон</td>
                                    <td colspan="2"></td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <tr>
                                    <td rowspan="${employee.phoneNumbers.size()}" colspan="2">телефон</td>
                                    <c:forEach items="${employee.phoneNumbers}" var="item">
                                        <td colspan="2">${item.phoneNumber}</td>
                                </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>

                       <c:choose>
                           <c:when test="${employee.emails.size() == 0}">
                               <tr>
                                   <td colspan="2">email</td>
                                   <td colspan="2"></td>
                               </tr>
                           </c:when>
                           <c:otherwise>
                                <tr>
                                    <td rowspan="${employee.emails.size()}" colspan="2">email</td>
                                    <c:forEach items="${employee.emails}" var="item">
                                    <td colspan="2">${item.email}</td>
                                </tr>
                                </c:forEach>
                           </c:otherwise>
                       </c:choose>
                        <tr><th colspan="4">адрес</th></tr>
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
                            <td colspan="1" class="wrapper">
                                <a href="/addEmailEmployee?eid=${employee.id}" class="btn success">добавить email</a>
                            </td>
                            <td colspan="2" class="wrapper">
                                <a href="/addPhoneNumberEmployee?eid=${employee.id}" class="btn success">добавить телефон</a>
                            </td>
                            <td colspan="1" class="wrapper">
                                <a href="/addNewAddress?eid=${employee.id}" class="btn success">добавить адрес</a>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="table">
                    <table  <%=style%>>
                        <tr>
                            <th colspan="6">занимаемые должности</th>
                        </tr>
                        <tr>
                            <th>c</th>
                            <th>по</th>
                            <th>должность</th>
                            <th>оклад, руб</th>
                            <th>ставка</th>
                            <th>сумма, руб</th>
                        </tr>
                        <c:forEach items="${employee.wageRates}" var="entity">
                            <tr>
                                <td>${entity.startDate.getDayOfMonth()}.${entity.startDate.getMonthValue()}.${entity.startDate.getYear()}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${entity.finishDate == null}">
                                            <a href="fire?wid=${entity.id}&eid=${employee.id}" class="btn danger">уволить</a>
                                        </c:when>
                                        <c:otherwise>
                                            ${entity.finishDate.getDayOfMonth()}.${entity.finishDate.getMonthValue()}.${entity.finishDate.getYear()}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${entity.positionName}</td>
                                <td>${entity.positionSalary}</td>
                                <td>${entity.rate}</td>
                                <td>${entity.rate * entity.positionSalary}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="6" class="wrapper">
                                <a href="${pageContext.request.contextPath}/showNewWageRateForm?pid=${employee.personId}" class="btn success">назначить на должность</a>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </section>

<%@ include file="footer.jsp" %>