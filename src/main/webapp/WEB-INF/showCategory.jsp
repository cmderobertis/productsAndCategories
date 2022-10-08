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
  <h1 class="text-center">${category.name}</h1>
  <a href="/">Home</a>
  <hr>
  <h2>Products:</h2>
  <ul>
    <c:forEach var="product" items="${category.products}">
      <li>${product.name} <a href="/categories/${category.id}/remove/${product.id}">Remove</a></li>
    </c:forEach>
  </ul>
  <hr>
  <c:if test="${otherProducts.size() != 0}">
    <h3>Add Product</h3>
    <%--@elvariable id="categoryId" type=""--%>
    <form action="/categories/${category.id}/add" method="post">
      <select class="form-control" name="productId">
        <c:forEach var="product" items="${otherProducts}">
          <!--- Each option VALUE is the id of the category --->
          <option value="${product.id}">
            <!--- This is what shows to the user as the option --->
            <c:out value="${product.name}"/>
          </option>
        </c:forEach>
      </select>
      <input type="submit" value="Add">
    </form>
  </c:if>
</body>
</html>