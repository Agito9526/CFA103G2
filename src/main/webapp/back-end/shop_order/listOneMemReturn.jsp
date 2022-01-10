<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.shop_order.model.*"%>

<%
	Shop_OrderBackVO soVO = (Shop_OrderBackVO) request.getAttribute("soVO");
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
	<tr><td>
		 <h3>listOneMemReturn.jsp</h3>
		 <h4><a href="select.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
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
	<td>
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do" style="margin-bottom: 0px;">
		<input type="submit" value="�h�f�q��">
		<input type="hidden" name="mem_id"  value="${soVO.mem_id}">
		<input type="hidden" name="action"  value="getAll_For_Shop_Return"></FORM>
	</td>
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
		<th>�h�f�ӽ�</th>
		<th>�ݩΥӽЮɶ�</th>
		<th>�h�f�z��</th>
		<th>�h�f�f��</th>
		<th>�h�f���ܰT��</th>
		<th>�ڵ��h�f�z��</th>
		<th>�h�f���A</th>
	</tr>
	<c:forEach var="soVO" items="${list}">
	
		<tr>
			<td><%=soVO.getShop_order_id()%></td>
			<td><%=soVO.getMem_id()%></td>
			<td><%=soVO.getShop_order_date()%></td>
			<td><%=soVO.getShop_order_shipment()%></td>
			<td><%=soVO.getShop_order_ship_fee()%></td> 
			<td><%=soVO.getShop_order_amount()%></td>
			<td><%=soVO.getShop_order_payment()%></td>
			<td><%=soVO.getShop_order_memo()%></td>
			<td><%=soVO.getShop_order_ship_date()%></td>
			<td><%=soVO.getShop_order_track_no()%></td>
			<td><%=soVO.getShop_order_status()%></td>
			<td><%=soVO.getShop_return_apply()%></td>
			<td><%=soVO.getShop_return_apply_date()%></td>
			<td><%=soVO.getShop_return_reason()%></td>
			<td><%=soVO.getShop_return_confirm()%></td>
			<td><%=soVO.getShop_return_message()%></td>
			<td><%=soVO.getShop_return_rej_reason()%></td>
			<td><%=soVO.getShop_return_status()%></td>
			<td>
			  	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�h�f�q��ק�">
			    <input type="hidden" name="shop_order_id"  value="${soVO.shop_order_id}">
			     <input type="hidden" name="action" value="getOne_For_Update_Order_Return_Back"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>

</body>
</html>