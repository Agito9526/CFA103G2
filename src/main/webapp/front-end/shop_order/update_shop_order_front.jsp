<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order.model.*"%>

<%
Shop_OrderFrontVO soVO = (Shop_OrderFrontVO) request.getAttribute("soVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>Insert title here</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post"
		ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do"
		name="form1">
		<table>
			<tr>
				<td>訂單編號:<font color=red><b>*</b></font></td>
				<td><%=soVO.getShop_order_id()%></td>
			</tr>
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
				<td><%=soVO.getMem_id()%></td>
			</tr>
			<tr>
				<td>訂單日期:<font color=red><b>*</b></font></td>
				<td><%=soVO.getShop_order_date()%></td>
			</tr>
			<tr>
				<td>出貨方式:</td>
				<td>
					<select name="shop_order_shipment"">
						<option value="">請選擇</option>
						<option value="0">賣家宅配</option>
						<option value="1">超商取貨付款</option>
					</select>
				</td>
			</tr>

			<tr>
				<td>運費:<font color=red><b>*</b></font></td>
				<td><%=soVO.getShop_order_ship_fee()%></td>
			</tr>
			<tr>
				<td>總金額:<font color=red><b>*</b></font></td>
				<td><%=soVO.getShop_order_amount()%></td>
			</tr>
			<tr>
				<td>付款方式:</td>
				<td>
					<select name="shop_order_payment">
						<option value="">請選擇</option>
						<option value="0">信用卡</option>
						<option value="1">銀行轉帳</option>
						<option value="2">超商取貨付款</option>
					</select>
				</td>
<!-- 				<input type="TEXT" name="shop_order_payment" size="45" -->
<%-- 					value="<%=soVO.getShop_order_payment()%>" /> --%>
			</tr>
			<tr>
				<td>訂單備註:</td>
				<td><input type="TEXT" name="shop_order_memo" size="45"
					value="<%=soVO.getShop_order_memo()%>" /></td>
			</tr>
			<tr>
				<td>出貨日期:<font color=red><b>*</b></font></td>
				<td><%=soVO.getShop_order_ship_date()%></td>
			</tr>
			<tr>
				<td>貨運追蹤號碼:<font color=red><b>*</b></font></td>
				<td><%=soVO.getShop_order_track_no()%></td>
			</tr>
			<tr>
				<td>訂單狀態:<font color=red><b>*</b></font></td>
				<td><%=soVO.getShop_order_status()%></td>
			</tr>

			<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
			<!-- 	<tr> -->
			<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
			<!-- 		<td><select size="1" name="deptno"> -->
			<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
			<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
			<%-- 			</c:forEach> --%>
			<!-- 		</select></td> -->
			<!-- 	</tr> -->

		</table>

		<br> <input type="hidden" name="action" value="Update_Front">
		<input type="hidden" name="shop_order_id"
			value="<%=soVO.getShop_order_id()%>"> <input type="hidden"
			name="mem_id" value="<%=soVO.getMem_id()%>"> <input
			type="hidden" name="shop_order_date"
			value="<%=soVO.getShop_order_date()%>"> <input type="hidden"
			name="shop_order_ship_fee" value="<%=soVO.getShop_order_ship_fee()%>">
		<input type="hidden" name="shop_order_amount"
			value="<%=soVO.getShop_order_amount()%>"> <input
			type="hidden" name="shop_order_ship_date"
			value="<%=soVO.getShop_order_ship_date()%>"> <input
			type="hidden" name="shop_order_track_no"
			value="<%=soVO.getShop_order_track_no()%>"> <input
			type="hidden" name="shop_order_status"
			value="<%=soVO.getShop_order_status()%>"> <input
			type="submit" value="送出修改">
	</FORM>

</body>