<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

            <div class="container">
                <h3>Добавление нового номера телефона</h3>
                <form id="add_form" name="newPhoneNumber" action="/savePhoneNumber${entityId}" method="post" >
                    <fieldset>
                        <legend>телефон</legend>
                        <div class="field">
                            <label for="phoneNumber">номер </label>
                            <input type="tel" name="phoneNumber" id="phoneNumber" required/>
                        </div>
                        <input type="hidden" name="personId"  id="personId" value="${pid}"/>
                    </fieldset>
                    <button class="btn success" type="submit" name="Add">сохранить</button>
                </form>
            </div>

<%@ include file="footer.jsp" %>