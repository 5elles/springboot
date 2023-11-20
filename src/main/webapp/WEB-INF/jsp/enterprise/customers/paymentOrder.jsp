<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

    <div class="tables">
        <div class="table">
            <table <%=style%>>
                <tr>
                    <th colspan="2">платёжное поручение</th>
                </tr>
                <tr>
                    <td>Дата и время операции</td>
                    <td>
                        ${order.timeStamp.getDayOfMonth()}.${order.timeStamp.getMonthValue()}.${order.timeStamp.getYear()}

                        ${order.timeStamp.getHour()}:${order.timeStamp.getMinute()}:${order.timeStamp.getSecond()}
                    </td>
                </tr>
                <tr>
                    <th colspan="2"><b>отправитель</b></th>
                </tr>

                <tr>
                    <td>номер счета</td>
                    <td style="color: crimson">${order.fromAccountNumber}</td>
                </tr>
                <tr>
                    <td>владелец счета</td>
                    <td>${order.fromAccountCustomerLastName}
                        ${order.fromAccountCustomerFirstName}
                        ${order.fromAccountCustomerMiddleName}</td>
                </tr>
                <tr>
                    <td>личный номер</td><td>${order.fromAccountCustomerCitizenIdNumber}</td>
                </tr>


                <th colspan="2" ><b>получатель</b></th>
                </tr>
                <tr>
                <tr>
                    <td>номер счета</td>
                    <td style="color: crimson">${order.toAccountNumber}</td>
                </tr>
                <tr>
                    <td>владелец счета</td>
                    <td>${order.toAccountCustomerLastName}
                        ${order.toAccountCustomerFirstName}
                        ${order.toAccountCustomerMiddleName}</td>
                </tr>
                <tr>
                    <td>личный номер</td><td>${order.toAccountCustomerCitizenIdNumber}</td>
                </tr>

                <tr>
                    <td>cумма в валюте платежа</td>
                    <td>${order.amount} ${order.fromAccountCurrencyAbbreviation}</td>
                </tr>
            </table>
        </div>
    </div>

<%@ include file="footer.jsp" %>