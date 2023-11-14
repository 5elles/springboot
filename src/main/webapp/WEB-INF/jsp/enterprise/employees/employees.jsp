<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
        <div>
            <h3>Управление персоналом</h3>
            <form action="/staffingTable" method="get" >
                <button class="btn info" id="add-button">штатное расписание</button>
            </form>

            <div class="container">
                <h3>Поиск лица в системе</h3>
                <form id="add_form" class="form" name="searchThePerson" action="/findPersons" method="get">
                    <fieldset>
                        <legend>данные для поиска</legend>
                        <div class="field">
                            <label for="lastName">фамилия</label>
                            <input type="text" name="lastName" id="lastName" required />
                        </div>

                        <div class="field">
                            <label for="firstName">имя</label>
                            <input type="text" name="firstName" id="firstName" required/>
                        </div>

                        <div class="field">
                            <label for="middleName">отчество</label>
                            <input type="text" name="middleName" id="middleName" required/>
                        </div>
                    </fieldset>
                    <button class="btn info" type="submit" name="Add">найти</button>
                </form>
            </div>

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
