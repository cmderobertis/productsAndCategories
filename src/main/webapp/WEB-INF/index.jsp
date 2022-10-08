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
<h1 class="text-center">Products and Categories</h1>
<a href="/products/new">New Product</a>
<a href="/categories/new">New Category</a>
<div class="row justify-content-center">
  <div class="col-6">
    <div class="card">
      <div class="card-body">
        <div class="card-title">
          <h2>Products</h2>
        </div>
        <ul>
          <c:forEach var="product" items="${products}">
            <li>
              <a href="/products/${product.id}">${product.name}</a>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
  <div class="col-6">
    <div class="card">
      <div class="card-body">
        <div class="card-title">
          <h2>Categories</h2>
        </div>
        <ul>
          <c:forEach var="category" items="${categories}">
            <li>
              <a href="/categories/${category.id}">${category.name}</a>
            </li>
          </c:forEach>
        </ul>
      </div>
    </div>
  </div>
</div>
</body>
</html>