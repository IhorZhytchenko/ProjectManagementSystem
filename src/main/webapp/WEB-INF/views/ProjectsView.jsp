<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Projects</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<a href="${pageContext.servletContext.contextPath}/" class="w3-margin-top w3-button w3-grey w3-right">На головну</a>
<div class="w3-container w3-blue w3-margin-bottom">
    <h1>Проекти</h1>
</div>

<div class="w3-container w3-padding">
    <table class="w3-table w3-striped w3-bordered">
        <c:forEach items="${projects}" var="project">
            <tr>
                <td><c:out value="${project.name}"></c:out></td>
                <td><a class="w3-button w3-blue" href="${pageContext.servletContext.contextPath}/editProject?id=<c:out value="${project.id}"></c:out>">Редагувати</a></td>
                <td><form method="post" action="${pageContext.servletContext.contextPath}/projects?id=<c:out value="${project.id}"></c:out>">
                    <input type="submit" class="w3-button w3-red w3-large" value="Delete"/>
                </form></td>

            </tr>

        </c:forEach>

    </table>

    <a href="${pageContext.servletContext.contextPath}/createProject" class="w3-margin-top w3-button w3-blue ">Додати новий проект</a>
</div>
</body>
</html>