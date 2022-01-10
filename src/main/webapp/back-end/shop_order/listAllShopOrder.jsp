<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.shop_order.model.*"%>

<%
	Shop_OrderBackService soSvc = new Shop_OrderBackService();
	List<Shop_OrderBackVO> list = soSvc.getAllOrder();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>

<style>
table {
	width: 800px;
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
		<c:forEach var="Shop_OrderBackVO" items="${list}">
			<tr>
				<td>${Shop_OrderBackVO.shop_order_id}</td>
				<td>${Shop_OrderBackVO.mem_id}</td>
				<td>${Shop_OrderBackVO.shop_order_date}</td>
				<td>${Shop_OrderBackVO.shop_order_shipment}</td>
				<td>${Shop_OrderBackVO.shop_order_ship_fee}</td>
				<td>${Shop_OrderBackVO.shop_order_amount}</td>
				<td>${Shop_OrderBackVO.shop_order_payment}</td>
				<td>${Shop_OrderBackVO.shop_order_memo}</td>
				<td>${Shop_OrderBackVO.shop_order_ship_date}</td>
				<td>${Shop_OrderBackVO.shop_order_track_no}</td>
				<td>${Shop_OrderBackVO.shop_order_status}</td>
<!-- 				<td> -->
<%-- 			  		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" style="margin-bottom: 0px;"> --%>
<!-- 			     	<input type="submit" value="修改"> -->
<%-- 			     	<input type="hidden" name="shop_order_id"  value="${soVO.shop_order_id}"> --%>
<!-- 			     	<input type="hidden" name="action"	value="getOne_For_Update_Order_Back"></FORM> -->
<!-- 				</td> -->
				<td>
			  		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" style="margin-bottom: 0px;">
			     	<input type="submit" value="訂單修改">
			    	 <input type="hidden" name="shop_order_id"  value="${Shop_OrderBackVO.shop_order_id}">
			     	<input type="hidden" name="action" value="getOne_For_Update_Order_Back"></FORM>
				</td>
				<td>
			  		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" style="margin-bottom: 0px;">
			     	<input type="submit" value="刪除">
			    	 <input type="hidden" name="shop_order_id"  value="${Shop_OrderBackVO.shop_order_id}">
			     	<input type="hidden" name="action" value="delete_Shop_Order_Id_Back"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>