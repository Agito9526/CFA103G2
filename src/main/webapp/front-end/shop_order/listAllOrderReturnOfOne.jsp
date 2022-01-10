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
				<h3>listAllOrderReturnOfOne.jsp</h3> <!-- 		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4> -->
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
			<th>�h�f�ӽ�</th>
			<th>�h�f�ӽЮɶ�</th>
			<th>�h�f�z��</th>
			<th>�h�f�f��</th>
			<th>�h�f���ܰT��</th>
			<th>�ڵ��h�f�z��</th>
			<th>�h�f���A</th>
		</tr>
		<c:forEach var="soVO" items="${shop_order_return_of_one}">
			<tr>
				<td>${soVO.shop_order_id}</td>
				<td>${soVO.mem_id}</td>
<%-- 				<td>${soVO.shop_return_apply}</td> --%>
				<c:if test="${soVO.shop_return_apply == 0}">
					<td><c:out value="���ӽаh�f" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_apply == 1}">
					<td><c:out value="�ӽаh�f" /></td>
				</c:if>
				<td>${soVO.shop_return_apply_date}</td>
				<td>${soVO.shop_return_reason}</td>
<%-- 				<td>${soVO.shop_return_confirm}</td> --%>
				<c:if test="${soVO.shop_return_confirm == 0}">
					<td><c:out value="�ڵ�" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_confirm == 1}">
					<td><c:out value="�q�L" /></td>
				</c:if>
				<td>${soVO.shop_return_message}</td>
				<td>${soVO.shop_return_rej_reason}</td>
<%-- 				<td>${soVO.shop_return_status}</td> --%>
				<c:if test="${soVO.shop_return_status == 0}">
					<td><c:out value="�h�f�f�֤�" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_status == 1}">
					<td><c:out value="�h�f����" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_status == 2}">
					<td><c:out value="�h�f�B�z��" /></td>
				</c:if>
				<c:if test="${soVO.shop_return_status == 3}">
					<td><c:out value="�h�ڧ���" /></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</body>
</html>