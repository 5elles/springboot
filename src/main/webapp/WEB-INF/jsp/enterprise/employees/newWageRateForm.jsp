<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
    <head>
        <meta charset="UTF-8">
        <title>new employee</title>
        <link rel="stylesheet" href="/css/style.css">
    </head>
    <body>
        <div>
            <div class="container">
                <h3>Оформление новой рабочей ставки</h3>
                <form id="add_form" class="form" name="newEmployee" action="/setNewWageRate?pid=${pid}" method="post" >
                    <fieldset>
                        <legend>детали</legend>
                        <div class="field">
                            <label for="startDate">дата оформления</label>
                            <input type="date" name="startDate" id="startDate" required/>
                        </div>

                        <div class="field">
                            <label for="positionName">должность</label>
                            <select name="positionName" id="positionName" required="">
                                <c:forEach items="${data}" var="item">
                                    <option id="${item.id}" value=${item.positionName}>${item.positionName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="field">
                            <label for="rate">размер ставки</label>
                            <input type="number" min="0.1" max="1" step="any" name="rate" id="rate" required />
                        </div>

                        <div class="field">
                            <input hidden="hidden" type="number" name="personId" id="personId" value="${pid}"/>
                        </div>
                    </fieldset>
                    <button class="btn success" type="submit" name="Add">сохранить</button>
                </form>
            </div>
        </div>
    </body>
</html>