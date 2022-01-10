<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order.model.*"%>

<%
	Shop_OrderFrontVO soVO = (Shop_OrderFrontVO) request.getAttribute("soVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>Insert title here</title>
</head>

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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do" name="form1">
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=soVO.getShop_order_id()%></td>
	</tr>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=soVO.getMem_id()%></td>
	</tr>
	<tr>
		<td>退貨理由:</td>
		<td><input type="TEXT" name="shop_return_reason" size="45" value="<%=soVO.getShop_return_reason()%>" /></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>退貨申請:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_apply()%></td> --%>
<!-- 	</tr> -->
</table>

<input type="hidden" name="action" value="updateOrderReturn_Front">
<input type="hidden" name="shop_order_id" value="<%=soVO.getShop_order_id()%>">
<input type="hidden" name="mem_id" value="<%=soVO.getMem_id()%>">
<input type="hidden" name="shop_return_apply_date" value="<%=soVO.getShop_return_apply_date()%>">
<input type="submit" value="送出修改"></FORM>

</body>
</html>