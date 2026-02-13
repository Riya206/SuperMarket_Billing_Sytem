<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.wipro.market.bean.MarketBillBean" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bill Details</title>
</head>
<body>
<h3>Bill Details</h3>

<%
    MarketBillBean bean = (MarketBillBean) request.getAttribute("bean");
    String message = (String) request.getAttribute("message");

    if(bean != null) {
        // Format the date
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String purchaseDate = sdf.format(bean.getPurchaseDate());
%>

Bill ID: <%= bean.getBillId() %><br><br>
Customer Name: <%= bean.getCustomerName() %><br><br>
Item Name: <%= bean.getItemName() %><br><br>
Purchase Date: <%= purchaseDate %><br><br>
Quantity: <%= bean.getQuantity() %><br><br>
Price: <%= bean.getPrice() %><br><br>
Total Amount: <%= bean.getTotalAmount() %><br><br>
Remarks: <%= bean.getRemarks() %><br><br>

<%
    } else {
%>

<h4><%= message %></h4>

<%
    }
%>

<a href="Menu.html">Back to Menu</a>

</body>
</html>
