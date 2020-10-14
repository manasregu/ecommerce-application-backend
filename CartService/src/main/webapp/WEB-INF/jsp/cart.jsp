<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
    <%@ page language="java" contentType="application/x-www-form-urlencoded charset=utf-8"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manasadevi R</title>
</head>
<body>

<div id="header">
<h2> CART ITEMS </h2>
</div>

<div id="cart table">
<table border="1" cellpadding="5">
  <tr>
    <th>Cart Id</th>
    <th>Product Id</th>
    <th>product Name</th>
    <th>Price</th>
  </tr>
  
  <c:forEach var= "item" items="${items}">
	<tr>
	<td> ${item.id}</td>
	<td> ${item.pid}</td>
	<td> ${item.name}</td>
	<td> ${item.price}</td>
	</tr>

</c:forEach>
</table>
</div>

<div id ="Input Section">
   
</div> 

</body>
</html>