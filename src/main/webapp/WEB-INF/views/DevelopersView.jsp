<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Developers</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/" class="w3-margin-top w3-button w3-grey w3-right">На головну</a>
<div class="w3-container w3-green w3-margin-bottom">
    <h1>Розробники</h1>
</div>

<div class="w3-container w3-padding">
    <table class="w3-table w3-striped w3-bordered">

        <c:forEach items="${developers}" var="developer">
        <tr>

            <td><c:out value="${developer.firstName}" ></c:out>    <c:out value="${developer.lastName}" ></c:out></td>
            <td><a class="w3-button w3-green" href="${pageContext.servletContext.contextPath}/editDeveloper?id=<c:out value="${developer.id}"></c:out>">Редагувати</a></td>
            <td><form method="post" action="${pageContext.servletContext.contextPath}/developers?id=<c:out value="${developer.id}"></c:out>">
                <input type="submit" class="w3-button w3-red w3-large" value="Delete"/>
            </form></td>
        </tr>
        </c:forEach>

    </table>

    <a href="${pageContext.servletContext.contextPath}/createDeveloper" class="w3-margin-top w3-button w3-green ">Додати нового розробника</a>
</div>
</body>
</html>
