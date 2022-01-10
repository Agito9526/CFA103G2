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
<script type="text/JavaScript">
	function show() {
		if (<%=soVO.getShop_order_shipment()%> === 0) {
            document.getElementById('shipment').innerHTML = "��a�v�t"; 
		}
		if (<%=soVO.getShop_order_shipment()%> === 1) {
            document.getElementById('shipment').innerHTML = "�W�Ө��f�I��"; 
		}
		if (<%=soVO.getShop_order_payment()%> === 0) {
            document.getElementById('payment').innerHTML = "�H�Υd"; 
		}
		if (<%=soVO.getShop_order_payment()%> === 1) {
            document.getElementById('payment').innerHTML = "�Ȧ���b"; 
		}
		if (<%=soVO.getShop_order_payment()%> === 2) {
            document.getElementById('payment').innerHTML = "�W�Ө��f�I��"; 
		}
		if (<%=soVO.getShop_order_status()%> === 0) {
            document.getElementById('status').innerHTML = "���ݥI�ڤ�"; 
		}
		if (<%=soVO.getShop_order_status()%> === 1) {
            document.getElementById('status').innerHTML = "�w����"; 
		}
		if (<%=soVO.getShop_order_status()%> === 2) {
            document.getElementById('status').innerHTML = "���ݽ�a�T�{��"; 
		}
		if (<%=soVO.getShop_order_status()%> === 3) {
            document.getElementById('status').innerHTML = "�ǳƥX�f��"; 
		}
		if (<%=soVO.getShop_order_status()%> === 4) {
            document.getElementById('status').innerHTML = "�w�X�f"; 
		}
		if (<%=soVO.getShop_order_status()%> === 5) {
            document.getElementById('status').innerHTML = "�����f�A�h�^��a"; 
		}
	}
</script>
</head>
<body onload="show()">
<%-- ���~��C --%>

<h4>�����Ƚm�߱ĥ� Script ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>���u��� - ListOneEmp.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/front-end/shop_order/select.jsp"><img src="" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>	
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table>

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
<!-- 		<th>�h�f�ӽ�</th> -->
<!-- 		<th>�ݩΥӽЮɶ�</th> -->
<!-- 		<th>�h�f�z��</th> -->
<!-- 		<th>�h�f�f��</th> -->
<!-- 		<th>�h�f���ܰT��</th> -->
<!-- 		<th>�ڵ��h�f�z��</th> -->
<!-- 		<th>�h�f���A</th> -->
	</tr>
	<c:forEach var="soVO" items="${list}">
	
		<tr>
			<td><%=soVO.getShop_order_id()%></td>
			<td><%=soVO.getMem_id()%></td>
			<td><%=soVO.getShop_order_date()%></td>
			<td><span id="shipment"></span></td>
<%-- 			<td><%=soVO.getShop_order_shipment()%></td> --%>
			<td><%=soVO.getShop_order_ship_fee()%></td> 
			<td><%=soVO.getShop_order_amount()%></td>
			<td><span id="payment"></span></td>
<%-- 			<td><%=soVO.getShop_order_payment()%></td> --%>
			<td><%=soVO.getShop_order_memo()%></td>
			<td><%=soVO.getShop_order_ship_date()%></td>
			<td><%=soVO.getShop_order_track_no()%></td>
			<td><span  id="status"></span></td>
<%-- 			<td><%=soVO.getShop_order_status()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_apply()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_apply_date()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_reason()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_confirm()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_message()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_rej_reason()%></td> --%>
<%-- 			<td><%=soVO.getShop_return_status()%></td> --%>
		</tr>
	</c:forEach>
</table>

</body>
</html>