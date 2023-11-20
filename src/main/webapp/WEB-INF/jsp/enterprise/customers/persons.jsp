<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
    <section class="tables">
        <div class="table">
            <table <%=style%>>
                <tr>
                    <th colspan="2">Результат поиска</th>
                </tr>
                <c:choose>
                    <c:when test="${data.size() > 0}">
                    <tr>
                        <th colspan="1">статус</th>
                        <th colspan="1">паспортные данные</th>
                    </tr>
                    <c:forEach items="${data}" var="item">
                        <tr>
                            <td colspan="1" class="wrapper">
                                <c:choose>
                                    <c:when test="${item.customerId != null}">
                                        клиент
                                    </c:when>
                                    <c:otherwise>
                                        <a href="/newcustomer?pid=${item.id}" class="btn success">оформить</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td colspan="1">
                                <c:choose>
                                    <c:when test="${item.customerId != null}">

                                        <a href="/customer?id=${item.customerId}">${item.lastName} ${item.firstName} ${item.middleName}</a>

                                    </c:when>
                                    <c:otherwise>
                                        ${item.lastName} ${item.firstName} ${item.middleName}
                                    </c:otherwise>
                                </c:choose>
                                , ${item.dateOfBirth.getDayOfMonth()}.${item.dateOfBirth.getMonthValue()}.${item.dateOfBirth.getYear()} г.р. <br>
                                ID ${item.citizenIdNumber}, паспорт ${item.passportNumber}
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td colspan="2">
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
                            <a href="/newPersonCustomer" class="btn success">добавить новое физлицо в систему</a>
                        </td>
                    </tr>
            </table>
        </div>
    </section>


<%@ include file="footer.jsp" %>