<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order.model.*"%>

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
	<table id="table-1" align="center">
		<tr>
			<td>
				<h3>�Ҧ��q���� - listAllShopOrder.jsp(Back)</h3> <!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4> -->
			</td>
		</tr>
	</table>

	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table align="center">


		<tr>
			<th>�q��s��</th>
			<th>�|���s��</th>
			<th>�q����</th>
			<th>�X�f�覡</th>
			<th>�B�O</th>
			<th>�`���B</th>
			<th>�I�ڤ覡</th>
			<th>�q��Ƶ�</th>
			<th>�X�f���</th>
			<th>�f�B�l�ܸ��X</th>
			<th>�q�檬�A</th>
		</tr>
		<c:forEach var="soVO" items="${listAllOrderOfOne}">
			<tr>
				<td>${soVO.shop_order_id}</td>
				<td>${soVO.mem_id}</td>
				<td>${soVO.shop_order_date}</td>
				<td>${soVO.shop_order_shipment}</td>
				<td>${soVO.shop_order_ship_fee}</td>
				<td>${soVO.shop_order_amount}</td>
				<td>${soVO.shop_order_payment}</td>
				<td>${soVO.shop_order_memo}</td>
				<td>${soVO.shop_order_ship_date}</td>
				<td>${soVO.shop_order_track_no}</td>
				<td>${soVO.shop_order_status}</td>
				<td>
			  		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" style="margin-bottom: 0px;">
			     	<input type="submit" value="�q��ק�">
			    	 <input type="hidden" name="shop_order_id"  value="${soVO.shop_order_id}">
			     	<input type="hidden" name="action" value="getOne_For_Update_Order_Back"></FORM>
				</td>
				<td>
			  		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" style="margin-bottom: 0px;">
			     	<input type="submit" value="�R��">
			    	 <input type="hidden" name="shop_order_id"  value="${soVO.shop_order_id}">
			     	<input type="hidden" name="action" value="delete_Shop_Order_Id_Back"></FORM>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>