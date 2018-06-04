<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Developer</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-green">
    <a href="${pageContext.servletContext.contextPath}/developers" class="w3-button w3-margin w3-large w3-blue">Всі розробники</a>
    <h1>Редагування розробника</h1>
</div>

<br/>

<div class="w3-container w3-green w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/editDeveloper?id=<c:out value="${developer.id}"></c:out>">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Імя:</h3>
            <input class="w3-input" type="text" name="firstName" value="<c:out value="${developer.firstName}"></c:out>">
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Прізвище:</h3>
            <input class="w3-input" type="text" name="lastName" value="<c:out value="${developer.lastName}"></c:out>">
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Вік:</h3>
            <input class="w3-input" type="text" name="age" value="<c:out value="${developer.age}"></c:out>">
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Стать:</h3>
            <select style="display: inline-block !important;" class="w3-select w3-block" name="sex">
                <option value="male">male</option>
                <option value="female">female</option>
            </select>
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Компанія:</h3>
            <select style="display: inline-block !important;" class="w3-select w3-block" name="companyId">
                <c:forEach items="${companies}" var="company">
                    <c:choose>
                        <c:when test="${developer.company.id == company.id}">
                            <option selected="" value="<c:out value="${company.id}">
                                </c:out>"><c:out value="${company.name}"  ></c:out>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="<c:out value="${company.id}">
                             </c:out>"><c:out value="${company.name}"  ></c:out>
                            </option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </select>
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Зарплата:</h3>
            <input class="w3-input" type="text" name="salary" value="<c:out value="${developer.salary}"></c:out>">
        </div>


        <div class="w3-container">
            <input type="submit" class="w3-button w3-blue w3-right w3-large" value="Зберегти"/>
        </div>
    </form>
</div>

</body>
</html>
