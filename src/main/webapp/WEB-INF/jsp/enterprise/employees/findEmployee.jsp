<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>

    <div>
        <br>
        <div class="container">
            <h3>Поиск лица в системе</h3>
            <form id="add_form" class="form" name="searchThePerson" action="/findPersons" method="get">
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

<%@ include file="footer.jsp" %>