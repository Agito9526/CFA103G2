<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

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
	
<table id="table-1">
	<tr><td><h4>Test</h4></td></tr>
</table>

<h3>資料查詢</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>	
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<li><a href='<%=request.getContextPath()%>/back-end/shop_order/listAllShopOrder.jsp'>List</a> all ShopOrder.  <br><br></li>
<li><a href='<%=request.getContextPath()%>/back-end/shop_order/listAllShopOrderReturn.jsp'>List</a> all ShopOrderReturn.  <br><br></li>

	<li>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" >
	        <b>輸入會員編號 (如1001):</b>
	        <input type="text" name="mem_id">
	        <input type="hidden" name="action" value="getAll_For_Shop_Oreder">
	        <input type="submit" value="送出">
	    </FORM>
	</li>
	<li>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" >
	        <b>輸入會員編號 (退貨申請列表)(如1001):</b>
	        <input type="text" name="mem_id">
	        <input type="hidden" name="action" value="getAll_For_Shop_Oreder_Return">
	        <input type="submit" value="送出">
	    </FORM>
 	</li>
	<li>
	    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" >
	        <b>輸入訂單編號 (退貨申請列表)(如1001):</b>
	        <input type="text" name="shop_order_id">
	        <input type="hidden" name="action" value="getOneReturn_For_Display">
	        <input type="submit" value="送出">
	    </FORM>
 	</li>
  
       <!--   拿後台的service -->
<%--   <jsp:useBean id="soSvc" scope="page" class="com.shop_order.model.Shop_OrderBackService" /> --%>
  
<!--     <li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do" > --%>
<!--        <b>選擇員工編號:</b> -->
<!--        <select size="1" name="mem_id"> -->
<%--          <c:forEach var="soVO" items="${soSvc.all}" >  --%>
<%--           <option value="${soVO.mem_id}">${soVO.mem_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getAll_For_Shop_Oreder"> -->
<!--        <input type="submit" value="送出"> -->
<!--     	</FORM> -->
<!--   	</li> -->
  	

  	
<%--   	  <jsp:useBean id="soSvc" scope="page" class="com.shop_order.model.Shop_OrderFrontService" /> --%>
  	
<!--   	<li> -->
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderfront.do" > --%>
<!--        <b>選擇員工編號(2):</b> -->
<!--        <select size="1" name="mem_id"> -->
<%--          <c:forEach var="soVO" items="${soSvc.all}" >  --%>
<%--           <option value="${soVO.mem_id}">${soVO.mem_id} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getAll_For_Shop_Oreder"> -->
<!--        <input type="submit" value="送出"> -->
<!--     	</FORM> -->
<!--   	</li> -->

</body>
</html>