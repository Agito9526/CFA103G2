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
</head>
<body>
	<%-- ���~��C --%>

	<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
	<table id="table-1">
		<tr>
			<td>
				<h3>���u��� - ListOneEmp.jsp</h3>
				<h4>
					<a href="<%=request.getContextPath()%>/front-end/shop_order/select.jsp"><img src="images/back1.gif" width="100"
						height="32" border="0">�^����</a>
				</h4>
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

	<table>
		<tr>
			<th>�q��s��</th>
			<th>�|���s��</th>
			<th>�h�f�z��</th>
			<th>�h�f�ӽЮɶ�</th>
		</tr>
		<c:forEach var="soVO" items="${list}">
			<td><%=soVO.getShop_order_id()%></td>
			<td><%=soVO.getMem_id()%></td>
			<td><%=soVO.getShop_return_reason()%></td>
			<td><%=soVO.getShop_return_apply_date()%></td>
		</c:forEach>
	</table>

</body>
</html>