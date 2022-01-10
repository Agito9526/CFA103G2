<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order.model.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
	<table id="table-1" align="center">
		<tr>
			<td>
				<h3>listAllOrderReturnOfOne.jsp</h3> <!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4> -->
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

	<table align="center">

		<tr>
			<th>訂單編號</th>
			<th>會員編號</th>
			<th>退貨申請</th>
			<th>退貨申請時間</th>
			<th>退貨理由</th>
			<th>退貨審核</th>
			<th>退貨指示訊息</th>
			<th>拒絕退貨理由</th>
			<th>退貨狀態</th>
		</tr>
		<c:forEach var="soVO" items="${shop_order_return_of_one}">
			<tr>
				<td>${soVO.shop_order_id}</td>
				<td>${soVO.mem_id}</td>
<%-- 				<td>${soVO.shop_return_apply}</td> --%>
				<c:if test="${soVO.shop_return_apply == 0}">
					<td><c:out value="未申請退貨" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_apply == 1}">
					<td><c:out value="申請退貨" /></td>
				</c:if>
				<td>${soVO.shop_return_apply_date}</td>
				<td>${soVO.shop_return_reason}</td>
<%-- 				<td>${soVO.shop_return_confirm}</td> --%>
				<c:if test="${soVO.shop_return_confirm == 0}">
					<td><c:out value="拒絕" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_confirm == 1}">
					<td><c:out value="通過" /></td>
				</c:if>
				<td>${soVO.shop_return_message}</td>
				<td>${soVO.shop_return_rej_reason}</td>
<%-- 				<td>${soVO.shop_return_status}</td> --%>
				<c:if test="${soVO.shop_return_status == 0}">
					<td><c:out value="退貨審核中" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_status == 1}">
					<td><c:out value="退貨失敗" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_status == 2}">
					<td><c:out value="退貨處理中" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_status == 3}">
					<td><c:out value="退款完成" /></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>