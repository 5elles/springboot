<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

    <div class="container">
        <h3>Регистрация лица в системе</h3>
        <form id="add_form" class="form" name="newperson" action="/newPerson${status}" method="post" >
            <fieldset>
                <legend>паспортные данные</legend>
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

                <div class="field">
                    <label for="dateOfBirth">дата рождения</label>
                    <input type="date" name="dateOfBirth" id="dateOfBirth" required/>
                </div>

                <div class="field">
                    <label for="citizenIdNumber">ID №</label>
                    <input type="text" name="citizenIdNumber" id="citizenIdNumber" required/>
                </div>

                <div class="field">
                    <label for="passportNumber">паспорт №</label>
                    <input type="text" name="passportNumber" id="passportNumber" required/>
                </div>
            </fieldset>
            <button class="success" type="submit" name="Add">save</button>
        </form>
    </div>

<%@ include file="footer.jsp" %>