<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
        <div>
            <div class="container">
                <h3>Регистрация нового клиента банка</h3>
                <form id="add_form" class="form" name="newperson" action="/createNewCustomer" method="post" >
                    <fieldset>
                        <legend>договор оказания услуг</legend>
                        <div class="field">
                            <label for="agreementNumber">номер</label>
                            <input type="text" name="agreementNumber" id="agreementNumber" required />
                        </div>

                        <div class="field">
                            <label for="agreementDate">дата оформления</label>
                            <input type="date" name="agreementDate" id="agreementDate" required/>
                        </div>
                            <input type="hidden" name="personId" id="personId" value="${pid}"/>
                    </fieldset>
                    <button class="btn success" type="submit" name="Add">сохранить</button>
                </form>
            </div>
        </div>
<%@ include file="footer.jsp" %>