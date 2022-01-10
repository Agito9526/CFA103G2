<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order_details.model.*"%>

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
	
	<table align="center">
		<tr>
			<th>訂單編號</th>
			<th>商品編號</th>
			<th>廠商會員編號</th>
			<th>購買數量</th>
			<th>單價</th>
			<th>評價滿意度</th>
			<th>評價內容</th>
			<th>評價日期</th>
			<c:forEach var="sodVO" items="${listAllOrderDetailsOfOne}">
				<tr>
					<td>${sodVO.shop_order_id}</td>
					<td>${sodVO.prod_id}</td>
					<td>${sodVO.store_id}</td>
					<td>${sodVO.shop_order_qty}</td>
					<td>${sodVO.shop_order_unit_price}</td>
					<td>${sodVO.commemt_satis}</td>
					<td>${sodVO.commemt_content}</td>
					<td>${sodVO.commemt_date}</td>
					<td>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_order_details.do" style="margin-bottom: 0px;">
							<input type="submit" value="評分">
							<input type="hidden" name="shop_order_id" value="${sodVO.shop_order_id}">
							<input type="hidden" name="prod_id" value="${sodVO.prod_id}">
							<input type="hidden" name="action" value="getOne_For_Update_Details">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</tr>
	</table>
	
</body>
</html>