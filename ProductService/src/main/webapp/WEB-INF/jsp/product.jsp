<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="header">

<h2>PRODUCT CATALOG</h2>

</div>

<div id="tableDetails">

<table>

<tr>
<th>Name</th>
<th>Brand</th>
<th>Size</th>
<th>Seller</th>
<th>Price</th>
</tr>
</br>
</br>
<h2>Products Available</h2>

<c:forEach items="${productDetails}" var= "tempDetails" >

<tr>
<td> ${tempDetails.name}</td>
<td> ${tempDetails.brand}</td>
<td> ${tempDetails.size}</td>
<td> ${tempDetails.seller}</td>
<td> ${tempDetails.price}</td>
</tr>

</c:forEach>

</table>

</div>


</body>
</html>