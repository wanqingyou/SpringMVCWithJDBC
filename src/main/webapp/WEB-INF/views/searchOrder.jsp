<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.List"%>

<title>Spring MVC</title>

<html>
<head>
<style type="text/css">
body {
		font-family: helvetica, arial, verdana;
		font-size: 16pt;
	}
	
	input[type="submit"] {
		background-color: black;
		color: white;
		border: none;
	}
	
	table th {
		background-color: black;
		color: white;
	}
	
	table.results tr:nth-child(even) {
		background: #EEE
	}
	
	table.results tr:nth-child(odd) {
		background: #FFF
	}
</style>
</head>
<body>
<p><a href="index.jsp">Back to Main List</a></p>
<form name="orderForm" action="orderSearch" method="post">

	<table>
		<tr>
			<td style="text-align: right;"><strong>Customer Name:</strong></td>
			<td><input type="text" name="custName" autocomplete="off"
				value="<c:out value="${custName}"/>"></td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td><input type="submit" value="Search"></td>
		</tr>
	</table>
</form>

<c:choose>
	<c:when test="${noData eq true}">
		<h2>
			Sorry, no records found.
		</h2>
	</c:when>
	<c:when test="${not empty searchResults}">
		<h2>
			<c:out value="Orders placed by customer ${custName }" />
		</h2>
		<table class="results">
			<tr>
				<th>ID</th>
				<th>Order Amount</th>
				<th>Order Date</th>
				<th>Customer</th>
			</tr>
			<c:forEach var="order" items="${searchResults}">
				<tr>
					<td><c:out value="${order.orderID}" /></td>
					<td><c:out value="${order.amount}" /></td>
					<td><c:out value="${order.orderDate}" /></td>
					<td><c:out value="${order.customer.customerName }"/></td>
				</tr>
			</c:forEach>
		</table>

	</c:when>
</c:choose>
</body>
</html>