<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.shop_order_details.model.*"%>


<%
	Shop_Order_DetailsVO sodVO = (Shop_Order_DetailsVO) request.getAttribute("sodVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<table>

	<tr>
		<th>訂單編號</th>
		<th>商品編號</th>
		<th>廠商會員編號</th>
		<th>購買數量</th>
		<th>單價</th>
		<th>評價滿意度</th>
		<th>評價內容</th>
		<th>評價日期</th>
	</tr>
	<c:forEach var="soVO" items="${list}">
	
		<tr>
			<td><%=sodVO.getShop_order_id()%></td>
			<td><%=sodVO.getProd_id()%></td>
			<td><%=sodVO.getStore_id()%></td>
			<td><%=sodVO.getShop_order_qty()%></td>
			<td><%=sodVO.getShop_order_unit_price()%></td> 
			<td><%=sodVO.getCommemt_satis()%></td>
			<td><%=sodVO.getCommemt_content()%></td>
			<td><%=sodVO.getCommemt_date()%></td>
		</tr>
	</c:forEach>
</table>
</body>
</html>