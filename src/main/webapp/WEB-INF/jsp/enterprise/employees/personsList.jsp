<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
<link rel="stylesheet" href="/css/style.css">

    <section>
        <div class="tables">
            <div class="table">
                <table <%=style%>>
                    <tr>
                        <th colspan="2">результат поиска</th>
                    </tr>
                    <c:choose>
                        <c:when test="${data.size() > 0}">
                            <tr>
                                <th>статус</th>
                                <th>паспортные данные</th>
                            </tr>
                            <c:forEach items="${data}" var="item">
                                <tr>
                                    <td class="wrapper">
                                        <c:choose>
                                            <c:when test="${item.employeeId != null}">
                                                сотрудник
                                            </c:when>
                                            <c:otherwise>
                                                <a href="/showNewWageRateForm?pid=${item.id}" class="btn success">оформить</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${item.employeeId != null}">
                                                <a href="/employee?id=${item.employeeId}">${item.lastName} ${item.firstName} ${item.middleName}</a>
                                            </c:when>
                                            <c:otherwise>
                                                ${item.lastName} ${item.firstName} ${item.middleName}
                                            </c:otherwise>
                                        </c:choose>
                                        , ${item.dateOfBirth.getDayOfMonth()}.${item.dateOfBirth.getMonthValue()}.${item.dateOfBirth.getYear()}
                                        г.р. <br>
                                        ID ${item.citizenIdNumber}, паспорт ${item.passportNumber}
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6">
                                    <div class="text-red">
                                        <p>
                                            поиск не дал результатов
                                        </p>
                                    </div>
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td colspan="2" class="wrapper">
                            <a href="/newPersonEmployee" class="btn success">добавить новое лицо в систему</a>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </section>

<%@ include file="footer.jsp" %>