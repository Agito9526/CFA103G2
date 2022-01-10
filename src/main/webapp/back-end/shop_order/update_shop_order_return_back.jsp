<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.shop_order.model.*"%>

<%
Shop_OrderBackVO soVO = (Shop_OrderBackVO) request.getAttribute("soVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
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
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

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

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shop_order/shop_orderback.do" name="form1">
<table>
	<tr>
		<td>訂單編號:<font color=red><b>*</b></font></td>
		<td><%=soVO.getShop_order_id()%></td>
	</tr>
<!-- 	<tr> -->
<!-- 		<td>會員編號:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getMem_id()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單日期:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_date()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>出貨方式:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_shipment()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>運費:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_ship_fee()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>總金額:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_amount()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>付款方式:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_payment()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單備註:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_memo()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>出貨日期:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_ship_date()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>貨運追蹤號碼:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_track_no()%></td> --%>
<!-- 	</tr> -->
<!-- 	<tr> -->
<!-- 		<td>訂單狀態:<font color=red><b>*</b></font></td> -->
<%-- 		<td><%=soVO.getShop_order_status()%></td> --%>
<!-- 	</tr> -->
	<tr>
		<td>退貨申請:<font color=red><b>*</b></font></td>
		<td><%=soVO.getShop_return_apply()%></td>
	</tr>
	<tr>
		<td>退貨申請時間:<font color=red><b>*</b></font></td>
		<td><%=soVO.getShop_return_apply_date()%></td>
	</tr>
	<tr>
		<td>退貨理由:<font color=red><b>*</b></font></td>
		<td><%=soVO.getShop_return_reason()%></td>
	</tr>
	<tr>
		<td>退貨審核:</td>
		<td><input type="TEXT" name="shop_return_confirm" size="45" value="<%=soVO.getShop_return_confirm()%>" /></td>
	</tr>
	<tr>
		<td>退貨指示訊息:</td>
		<td><input type="TEXT" name="shop_return_message" size="45" value="<%=soVO.getShop_return_message()%>" /></td>
	</tr>
	<tr>
		<td>拒絕退貨理由:</td>
		<td><input type="TEXT" name="shop_return_rej_reason" size="45" value="<%=soVO.getShop_return_rej_reason()%>" /></td>
	</tr>
	<tr>
		<td>退貨狀態:</td>
		<td><input type="TEXT" name="shop_return_status" size="45" value="<%=soVO.getShop_return_status()%>" /></td>
	</tr>
	

<%-- 	<jsp:useBean id="deptSvc" scope="page" class="com.dept.model.DeptService" /> --%>
<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(empVO.deptno==deptVO.deptno)?'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>

<br>
<input type="hidden" name="action" value="update_Order_Return_Back">
<input type="hidden" name="shop_order_id" value="<%=soVO.getShop_order_id()%>">
<input type="submit" value="送出修改"></FORM>

</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=soVO.getShop_order_ship_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>



</html>