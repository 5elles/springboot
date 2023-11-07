<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>customers</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>


    <body>
        <div>
            <div class="container">
                <h3>Поиск лица в системе</h3>
                <form id="add_form" class="form" name="searchThePerson" action="/persons" method="get">
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
        </div>
        <br>
        <div>
            <div>
                <table border="1" cellspacing="0" cellpadding="12">
                    <tr>
                        <td colspan="6" align="center">Список клиентов банка</td>
                    </tr>
                    <tr>
                        <th>паспортные данные</th>
                        <th>договор</th>
                    </tr>
                    <c:forEach items="${customers}" var="item">
                        <tr>
                            <td>
                                <a href="/customer?id=${item.id}">${item.lastName} ${item.firstName} ${item.middleName}</a>, ${item.dateOfBirth.getDayOfMonth()}.${item.dateOfBirth.getMonthValue()}.${item.dateOfBirth.getYear()} г.р. <br>
                                ID ${item.citizenIdNumber}, паспорт ${item.passportNumber}
                            </td>

                            <td align="center">
                                    ${item.agreementNumber} от ${item.agreementDate.getDayOfMonth()}.${item.agreementDate.getMonthValue()}.${item.agreementDate.getYear()} г.<br>
                                    статус:
                                        <c:choose>
                                            <c:when test="${item.closureDate == null}">
                                                актуальный
                                            </c:when>
                                            <c:otherwise>
                                                <span class="highlight">архивный</span>
                                            </c:otherwise>
                                        </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

        <br>
        <form class="redirect_btn" name="redirect" action="/operationsLog" method="get">
            <button class="transparent_btn default">Журнал учета банковских операций</button>
        </form>
    </body>
</html>