<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>\<%@ page import="java.util.List" %>
<%@ page import="com.wipro.market.bean.MarketBillBean" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>All Bill Records</h3>

<%
    List<MarketBillBean> list =
        (List<MarketBillBean>) request.getAttribute("billList");

    if(list == null || list.isEmpty()) {
%>

<h4>No records available!</h4>

<%
    } else {
        for(MarketBillBean bean : list) {
%>

--------------------------------------<br>
Bill ID: <%= bean.getBillId() %><br>
Customer Name: <%= bean.getCustomerName() %><br>
Item Name: <%= bean.getItemName() %><br>
Purchase Date: <%= bean.getPurchaseDate() %><br>
Quantity: <%= bean.getQuantity() %><br>
Price: <%= bean.getPrice() %><br>
Total Amount: <%= bean.getTotalAmount() %><br>
Remarks: <%= bean.getRemarks() %><br>
--------------------------------------<br><br>

<%
        }
    }
%>

</body>
</html>