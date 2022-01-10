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
				<h3>�Ҧ��q���� - listAllShopOrder.jsp</h3> <!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4> -->
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

	<table align="center" id="table2">


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
				<c:if test="${soVO.shop_order_shipment == 0}">
					<td><c:out value="��a�v�t" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_shipment == 1}">
					<td><c:out value="�W�Ө��f�I��" /></td>
				</c:if>
				<td>${soVO.shop_order_ship_fee}</td>
				<td>${soVO.shop_order_amount}</td>
				<c:if test="${soVO.shop_order_payment == 0}">
					<td><c:out value="�H�Υd" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_payment == 1}">
					<td><c:out value="�Ȧ���b" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_payment == 2}">
					<td><c:out value="�W�Ө��f�I��" /></td>
				</c:if>
				<td>${soVO.shop_order_memo}</td>
				<td>${soVO.shop_order_ship_date}</td>
				<td>${soVO.shop_order_track_no}</td>
				<c:if test="${soVO.shop_order_status == 0}">
					<td><c:out value="���ݥI�ڤ�" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 1}">
					<td><c:out value="�w����" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 2}">
					<td><c:out value="���ݽ�a�T�{��" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 3}">
					<td><c:out value="�ǳƥX�f��" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 4}">
					<td><c:out value="�w�X�f" /></td>
				</c:if>
				<c:if test="${soVO.shop_order_status == 5}">
					<td><c:out value="�����f�A�h�^��a" /></td>
				</c:if>

				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop_order/shop_order_details.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�q�����"> <input type="hidden"
							name="shop_order_id" value="${soVO.shop_order_id}"> <input
							type="hidden" name="action" value="getAll_Details_Of_One">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="shop_order_id" value="${soVO.shop_order_id}"> <input
							type="hidden" name="action" value="getOne_For_Update_Order_Front">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�h�f�ӽ�"> <input type="hidden"
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