<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.shop_order.model.*"%>

<%
	Shop_OrderFrontVO soVO = (Shop_OrderFrontVO) request.getAttribute("soVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
<script type="text/JavaScript">
	function show() {
		if (<%=soVO.getShop_order_shipment()%> === 0) {
            document.getElementById('shipment').innerHTML = "賣家宅配"; 
		}
		if (<%=soVO.getShop_order_shipment()%> === 1) {
            document.getElementById('shipment').innerHTML = "超商取貨付款"; 
		}
		if (<%=soVO.getShop_order_payment()%> === 0) {
            document.getElementById('payment').innerHTML = "信用卡"; 
		}
		if (<%=soVO.getShop_order_payment()%> === 1) {
            document.getElementById('payment').innerHTML = "銀行轉帳"; 
		}
		if (<%=soVO.getShop_order_payment()%> === 2) {
            document.getElementById('payment').innerHTML = "超商取貨付款"; 
		}
		if (<%=soVO.getShop_order_status()%> === 0) {
            document.getElementById('status').innerHTML = "等待付款中"; 
		}
		if (<%=soVO.getShop_order_status()%> === 1) {
            document.getElementById('status').innerHTML = "已取消"; 
		}
		if (<%=soVO.getShop_order_status()%> === 2) {
            document.getElementById('status').innerHTML = "等待賣家確認中"; 
		}
		if (<%=soVO.getShop_order_status()%> === 3) {
            document.getElementById('status').innerHTML = "準備出貨中"; 
		}
		if (<%=soVO.getShop_order_status()%> === 4) {
            document.getElementById('status').innerHTML = "已出貨"; 
		}
		if (<%=soVO.getShop_order_status()%> === 5) {
            document.getElementById('status').innerHTML = "未取貨，退回賣家"; 
		}
	}
</script>
</head>
<body onload="show()">
<%-- 錯誤表列 --%>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/shop_order/select.jsp"><img src="" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>	
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>

	<tr>
		<th>訂單編號</th>
		<th>會員編號</th>
		<th>訂單日期</th>
		<th>出貨方式</th>
		<th>運費</th>
		<th>總金額</th>
		<th>付款方式</th>
		<th>訂單備註</th>
		<th>出貨日期</th>
		<th>貨運追蹤號碼</th>
		<th>訂單狀態</th>
<!-- 		<th>退貨申請</th> -->
<!-- 		<th>拓或申請時間</th> -->
<!-- 		<th>退貨理由</th> -->
<!-- 		<th>退貨審核</th> -->
<!-- 		<th>退貨指示訊息</th> -->
<!-- 		<th>拒絕退貨理由</th> -->
<!-- 		<th>退貨狀態</th> -->
	</tr>
	<c:forEach var="soVO" items="${list}">
	
		<tr>
			<td><%=soVO.getShop_order_id()%></td>
			<td><%=soVO.getMem_id()%></td>
			<td><%=soVO.getShop_order_date()%></td>
			<td><span id="shipment"></span></td>
<%-- 			<td><%=soVO.getShop_order_shipment()%></td> --%>
			<td><%=soVO.getShop_order_ship_fee()%></td> 
			<td><%=soVO.getShop_order_amount()%></td>
			<td><span id="payment"></span></td>
<%-- 			<td><%=soVO.getShop_order_payment()%></td> --%>
			<td><%=soVO.getShop_order_memo()%></td>
			<td><%=soVO.getShop_order_ship_date()%></td>
			<td><%=soVO.getShop_order_track_no()%></td>
			<td><span  id="status"></span></td>
<%-- 			<td><%=soVO.getShop_order_status()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_apply()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_apply_date()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_reason()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_confirm()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_message()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_rej_reason()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_status()%></td> --%>
		</tr>
	</c:forEach>
</table>

</body>
</html>