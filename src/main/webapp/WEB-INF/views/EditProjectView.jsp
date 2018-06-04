<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Project</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
</head>
<body>
<div class="w3-container w3-blue">
    <a href="${pageContext.servletContext.contextPath}/projects" class="w3-button w3-margin w3-large w3-yellow">Всі проекти</a>
    <h1>Додавання проекта</h1>
</div>

<br/>

<div class="w3-container w3-blue w3-padding">
    <form method="post" action="${pageContext.servletContext.contextPath}/editProject?id=<c:out value="${project.id}"></c:out>">
        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Імя:</h3>
            <input class="w3-input" type="text" name="name" value="<c:out value="${project.name}"></c:out>">
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Опис:</h3>
            <textarea class="w3-input w3-border" name="desc"  ><c:out value="${project.description}"></c:out></textarea>

        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Вартість:</h3>
            <input class="w3-input" type="text" name="cost" value="<c:out value="${project.cost}"></c:out>">
        </div>

        <div class="w3-container w3-light-grey w3-margin w3-padding">
            <h3>Компанія:</h3>
            <select style="display: inline-block !important;" class="w3-select w3-block" name="companyId">
                <c:forEach items="${companies}" var="company">
                    <c:choose>
                        <c:when test="${project.company.id == company.id}">
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
            <h3>Замовник:</h3>
            <select style="display: inline-block !important;" class="w3-select w3-block" name="customerId">
                <c:forEach items="${customers}" var="customer">
                    <c:choose>
                        <c:when test="${project.company.id == customer.id}">
                            <option selected="" value="<c:out value="${customer.id}">
                                </c:out>"><c:out value="${customer.name}"  ></c:out>
                            </option>
                        </c:when>
                        <c:otherwise>
                            <option value="<c:out value="${customer.id}">
                             </c:out>"><c:out value="${customer.name}"  ></c:out>
                            </option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>

        <div class="w3-container">
            <input type="submit" class="w3-button w3-yellow w3-right w3-large" value="Зберегти"/>
        </div>
    </form>
</div>

</body>
</html>
