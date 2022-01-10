<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.shop_order.model.*"%>

<%
	Shop_OrderBackVO soVO = (Shop_OrderBackVO) request.getAttribute("soVO");
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
</head>
<body>
<%-- 錯誤表列 --%>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>listOneMemReturn.jsp</h3>
		 <h4><a href="select.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
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
	<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do" style="margin-bottom: 0px;">
		<input type="submit" value="退貨訂單">
		<input type="hidden" name="mem_id"  value="${soVO.mem_id}">
		<input type="hidden" name="action"  value="getAll_For_Shop_Return"></FORM>
	</td>
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
		<th>退貨申請</th>
		<th>拓或申請時間</th>
		<th>退貨理由</th>
		<th>退貨審核</th>
		<th>退貨指示訊息</th>
		<th>拒絕退貨理由</th>
		<th>退貨狀態</th>
	</tr>
	<c:forEach var="soVO" items="${list}">
	
		<tr>
			<td><%=soVO.getShop_order_id()%></td>
			<td><%=soVO.getMem_id()%></td>
			<td><%=soVO.getShop_order_date()%></td>
			<td><%=soVO.getShop_order_shipment()%></td>
			<td><%=soVO.getShop_order_ship_fee()%></td> 
			<td><%=soVO.getShop_order_amount()%></td>
			<td><%=soVO.getShop_order_payment()%></td>
			<td><%=soVO.getShop_order_memo()%></td>
			<td><%=soVO.getShop_order_ship_date()%></td>
			<td><%=soVO.getShop_order_track_no()%></td>
			<td><%=soVO.getShop_order_status()%></td>
			<td><%=soVO.getShop_return_apply()%></td>
			<td><%=soVO.getShop_return_apply_date()%></td>
			<td><%=soVO.getShop_return_reason()%></td>
			<td><%=soVO.getShop_return_confirm()%></td>
			<td><%=soVO.getShop_return_message()%></td>
			<td><%=soVO.getShop_return_rej_reason()%></td>
			<td><%=soVO.getShop_return_status()%></td>
			<td>
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" style="margin-bottom: 0px;">
			     <input type="submit" value="退貨訂單修改">
			    <input type="hidden" name="shop_order_id"  value="${soVO.shop_order_id}">
			     <input type="hidden" name="action" value="getOne_For_Update_Order_Return_Back"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>