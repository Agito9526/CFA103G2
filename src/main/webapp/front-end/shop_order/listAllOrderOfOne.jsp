<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order.model.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<style>
/* table { */
/* 	width: 600px; */
/* 	background-color: white; */
/* 	margin-top: 5px; */
/* 	margin-bottom: 5px; */
/* } */
table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	/* 	padding: 10px; */
	text-align: center;
	width: 100px;
}
</style>

</head>
<body>
	<table id="table-1" align="center">
		<tr>
			<td>
				<h3>所有訂單資料 - listAllShopOrder.jsp</h3> <!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
			</td>
		</tr>
	</table>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table align="center" id="table2">


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
		</tr>
		<c:forEach var="soVO" items="${listAllOrderOfOne}">
			<tr>
				<td>${soVO.shop_order_id}</td>
				<td>${soVO.mem_id}</td>
				<td>${soVO.shop_order_date}</td>
				<c:if test="${soVO.shop_order_shipment == 0}">
					<td><c:out value="賣家宅配" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_shipment == 1}">
					<td><c:out value="超商取貨付款" /></td>
				</c:if>
				<td>${soVO.shop_order_ship_fee}</td>
				<td>${soVO.shop_order_amount}</td>
				<c:if test="${soVO.shop_order_payment == 0}">
					<td><c:out value="信用卡" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_payment == 1}">
					<td><c:out value="銀行轉帳" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_payment == 2}">
					<td><c:out value="超商取貨付款" /></td>
				</c:if>
				<td>${soVO.shop_order_memo}</td>
				<td>${soVO.shop_order_ship_date}</td>
				<td>${soVO.shop_order_track_no}</td>
				<c:if test="${soVO.shop_order_status == 0}">
					<td><c:out value="等待付款中" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 1}">
					<td><c:out value="已取消" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 2}">
					<td><c:out value="等待賣家確認中" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 3}">
					<td><c:out value="準備出貨中" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 4}">
					<td><c:out value="已出貨" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 5}">
					<td><c:out value="未取貨，退回賣家" /></td>
				</c:if>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop_order/shop_order_details.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="訂單明細"> <input type="hidden"
							name="shop_order_id" value="${soVO.shop_order_id}"> <input
							type="hidden" name="action" value="getAll_Details_Of_One">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="shop_order_id" value="${soVO.shop_order_id}"> <input
							type="hidden" name="action" value="getOne_For_Update_Order_Front">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="退貨申請"> <input type="hidden"
							name="shop_order_id" value="${soVO.shop_order_id}"> <input
							type="hidden" name="action"
							value="getOne_For_Update_Order_Return_Front">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>


</body>
</html>