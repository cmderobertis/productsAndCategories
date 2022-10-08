<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) -->
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Tacos</title>
  <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
  <script src="/webjars/jquery/jquery.min.js"></script>
  <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
  <h1 class="text-center">${product.name} (<fmt:formatNumber value="${product.price}" type="CURRENCY"/>)</h1>
  <p class="text-center">${product.description}</p>
  <a href="/">Home</a>
  <hr>
  <h2>Categories:</h2>
  <ul>
    <c:forEach var="category" items="${product.categories}">
      <li>${category.name} <a href="/products/${product.id}/remove/${category.id}">Remove</a></li>
    </c:forEach>
  </ul>
  <hr>
  <c:if test="${otherCategories.size() != 0}">
    <h3>Add Category</h3>
    <%--@elvariable id="categoryId" type=""--%>
    <form action="/products/${product.id}/add" method="post">
      <select class="form-control" name="categoryId">
        <c:forEach var="category" items="${otherCategories}">
          <!--- Each option VALUE is the id of the category --->
          <option value="${category.id}">
            <!--- This is what shows to the user as the option --->
            <c:out value="${category.name}"/>
          </option>
        </c:forEach>
      </select>
      <input type="submit" value="Add">
    </form>
  </c:if>
</body>
</html>