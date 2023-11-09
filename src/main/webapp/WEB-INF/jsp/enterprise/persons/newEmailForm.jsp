<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
    <head>
        <meta charset="UTF-8">
        <title>new email</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <div>
            <div class="container">
                <h3>Добавить новый email</h3>
                <form id="add_form" class="form" name="newEmail" action="/saveEmail${entityId}" method="post" >
                    <fieldset>
                        <legend>электронная почта</legend>
                        <div class="field">
                            <label for="email">Email</label>
                            <input type="email" name="email" id="email" required/>
                        </div>

                        <div class="field">
                            <input hidden="hidden" name="personId" id="personId" value="${pid}"/>
                        </div>
                    </fieldset>
                    <button class="btn success" type="submit" name="Add">сохранить</button>
                </form>
            </div>
        </div>
    </body>
</html>