<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order_details.model.*"%>

<%
	Shop_Order_DetailsVO sodVO = (Shop_Order_DetailsVO) request.getAttribute("sodVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<body bgcolor='white'>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_order_details.do" name="form1">
	<table>
		<tr>
			<td>訂單編號:<font color=red><b>*</b></font></td>
			<td><%=sodVO.getShop_order_id()%></td>
		</tr>
		<tr>
			<td>商品編號:<font color=red><b>*</b></font></td>
			<td><%=sodVO.getProd_id()%></td>
		</tr>
		<tr>
			<td>廠商會員編號:<font color=red><b>*</b></font></td>
			<td><%=sodVO.getStore_id()%></td>
		</tr>
		<tr>
			<td>購買數量:<font color=red><b>*</b></font></td>
			<td><%=sodVO.getShop_order_qty()%></td>
		</tr>
		<tr>
			<td>單價:<font color=red><b>*</b></font></td>
			<td><%=sodVO.getShop_order_unit_price()%></td>
		</tr>
		<tr>
			<td>評價滿意度:</td>
			<td><input type="TEXT" name="commemt_satis" size="45" value="<%=sodVO.getCommemt_satis()%>" /></td>
		</tr>
		<tr>
			<td>評價內容:</td>
			<td><input type="TEXT" name="commemt_content" size="45" value="<%=sodVO.getCommemt_content()%>" /></td>
		</tr>
	</table>
	<input type="hidden" name="action" value="update_detail">
	<input type="hidden" name="shop_order_id" value="<%=sodVO.getShop_order_id()%>">
	<input type="hidden" name="prod_id" value="<%=sodVO.getProd_id()%>">
	<input type="submit" value="送出修改"></FORM>



</body>
</html>