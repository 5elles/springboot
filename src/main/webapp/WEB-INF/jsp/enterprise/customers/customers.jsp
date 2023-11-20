
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

    <section class="tables">
        <div class="table">
            <table <%=style%>>
                <tr>
                    <th colspan="6" >Список клиентов банка</th>
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

                        <td>
                                ${item.agreementNumber} от ${item.agreementDate.getDayOfMonth()}.${item.agreementDate.getMonthValue()}.${item.agreementDate.getYear()} г.<br>
                            статус:
                            <c:choose>
                                <c:when test="${item.closureDate == null}">
                                    актуальный
                                </c:when>
                                <c:otherwise>
                                    <span class="text-red">архивный</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
<%@ include file="footer.jsp" %>