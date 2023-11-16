<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
                <div>
                    <br>
                    <div>
                        <table border="1" cellspacing="0" cellpadding="12">
                            <tr>
                                <th colspan="6" align="center">Результат поиска</th>
                            </tr>
                            <c:choose>
                                <c:when test="${data.size() > 0}">
                                <tr>
                                    <th>статус</th>
                                    <th>паспортные данные</th>
                                </tr>
                                <c:forEach items="${data}" var="item">
                                    <tr>
                                        <td>
                                            <c:choose>
                                                <c:when test="${item.customerId != null}">
                                                    клиент
                                                </c:when>
                                                <c:otherwise>
                                                    <form action="/newcustomer?pid=${item.id}" method="post">
                                                        <button class="btn success">оформить</button>
                                                    </form>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
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
                                    <td colspan="2">
                                        <form class="container" action="/newPersonCustomer">
                                            <button class="btn success">добавить новое физлицо в систему</button>
                                        </form>
                                    </td>
                                </tr>
                        </table>
                    </div>
                </div>


<%@ include file="footer.jsp" %>