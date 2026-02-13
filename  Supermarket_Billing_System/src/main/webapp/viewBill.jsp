<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>View Bill Record</h3>

<form action="MainServlet" method="post">

    <input type="hidden" name="operation" value="viewRecord">

    Customer Name:
    <input type="text" name="customerName" required><br><br>

    Purchase Date:
    <input type="date" name="purchaseDate" required><br><br>

    <input type="submit" value="View Bill">

</form>
</body>
</html>