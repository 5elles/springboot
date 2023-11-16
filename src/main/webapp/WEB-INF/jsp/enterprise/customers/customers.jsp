
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

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

<%@ include file="footer.jsp" %>