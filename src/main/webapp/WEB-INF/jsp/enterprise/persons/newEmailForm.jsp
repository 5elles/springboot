<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="header.jsp" %>
        <div>
            <div class="container">
                <h3>Добавить новый email</h3>
                <form id="add_form" name="newEmail" action="/saveEmail${entityId}" method="post" >
                    <fieldset>
                        <legend>электронная почта</legend>
                        <div class="field">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" required/>
                        </div>
                        <input  type="hidden" name="personId" id="personId" value="${pid}"/>
                    </fieldset>
                    <button class="btn success" type="submit" name="Add">сохранить</button>
                </form>
            </div>
        </div>

<%@ include file="footer.jsp" %>