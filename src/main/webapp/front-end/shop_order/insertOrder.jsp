<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order.model.*"%>
<%@ page import="com.shop_order_details.model.*"%>

<%
  Shop_OrderFrontVO soVO = (Shop_OrderFrontVO) request.getAttribute("soVO");
%>
<%
  Shop_Order_DetailsVO sodVO = (Shop_Order_DetailsVO) request.getAttribute("sodVO");
%>
--<%= soVO==null %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
</head>
<body>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do" name="form1">
<table>
	<tr>
		<td>�|��ID:</td>
		<td><input type="TEXT" name="mem_id" size="45"value="<%= (soVO==null)? "" : soVO.getMem_id()%>" /></td>
	</tr>
	<tr>
		<td>�X�f�覡:</td>
		<td><input type="TEXT" name="shop_order_shipment" size="45"value="<%= (soVO==null)? "" : soVO.getShop_order_shipment()%>" /></td>
	</tr>
	<tr>
		<td>�B�O:</td>
		<td><input type="TEXT" name="shop_order_ship_fee" size="45"value="<%= (soVO==null)? "" : soVO.getShop_order_ship_fee()%>" /></td>
	</tr>
	<tr>
		<td>�`���B:</td>
		<td><input type="TEXT" name="shop_order_amount" size="45"value="<%= (soVO==null)? "" : soVO.getShop_order_amount()%>" /></td>
	</tr>
	<tr>
		<td>�I�ڤ覡:</td>
		<td><input type="TEXT" name="shop_order_payment" size="45"value="<%= (soVO==null)? "" : soVO.getShop_order_payment()%>" /></td>
	</tr>
	<tr>
		<td>�q��Ƶ�:</td>
		<td><input type="TEXT" name="shop_order_memo" size="45"value="<%= (soVO==null)? "" : soVO.getShop_order_memo()%>" /></td>
	</tr>
	<tr>
		<td>�q�檬�A:</td>
		<td><input type="TEXT" name="shop_order_status" size="45"value="<%= (soVO==null)? "" : soVO.getShop_order_status()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>================================================================================================================================</td> -->
<!-- 	</tr> -->
	<tr>
		<td>�ӫ~�s��:</td>
		<td><input type="TEXT" name="prod_id" size="45"value="<%= (sodVO==null)? "" : sodVO.getProd_id()%>" /></td>
	</tr>
	<tr>
		<td>�t�ӷ|���s��:</td>
		<td><input type="TEXT" name="store_id" size="45"value="<%= (sodVO==null)? "" : sodVO.getStore_id()%>" /></td>
	</tr>
	<tr>
		<td>�ʶR�ƶq:</td>
		<td><input type="TEXT" name="shop_order_qty" size="45"value="<%= (sodVO==null)? "" : sodVO.getShop_order_qty()%>" /></td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="TEXT" name="shop_order_unit_price" size="45"value="<%= (sodVO==null)? "" : sodVO.getShop_order_unit_price()%>" /></td>
	</tr>
	<tr>
		<td>�ӫ~�s��:</td>
		<td><input type="TEXT" name="prod_id" size="45"value="<%= (sodVO==null)? "" : sodVO.getProd_id()%>" /></td>
	</tr>
	<tr>
		<td>�t�ӷ|���s��:</td>
		<td><input type="TEXT" name="store_id" size="45"value="<%= (sodVO==null)? "" : sodVO.getStore_id()%>" /></td>
	</tr>
	<tr>
		<td>�ʶR�ƶq:</td>
		<td><input type="TEXT" name="shop_order_qty" size="45"value="<%= (sodVO==null)? "" : sodVO.getShop_order_qty()%>" /></td>
	</tr>
	<tr>
		<td>���:</td>
		<td><input type="TEXT" name="shop_order_unit_price" size="45"value="<%= (sodVO==null)? "" : sodVO.getShop_order_unit_price()%>" /></td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="insert_Shop_Order_And_Details">
<input type="submit" value="�e�X�s�W"></FORM>
</body>
</html>