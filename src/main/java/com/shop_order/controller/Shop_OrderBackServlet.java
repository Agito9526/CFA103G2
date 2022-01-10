package com.shop_order.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.shop_order.model.*;
import com.shop_order_details.model.Shop_Order_DetailsService;

public class Shop_OrderBackServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		if ("getOneReturn_For_Display".equals(action)) { // 請求
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("shop_order_id");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/select.jsp");
					failureView.forward(req, res);
					return;
				}

				Integer shop_order_id = null;
				try {
					shop_order_id = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格是不正確");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/select.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 2.開始查詢資料 *****************************************/
				Shop_OrderBackService soSvc = new Shop_OrderBackService();
				Shop_OrderBackVO soVO = soSvc.getOrderReturnOfOrderID(shop_order_id);
				if (soVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/select.jsp");
					failureView.forward(req, res);
					return;
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soVO", soVO);
				String url = "/back-end/shop_order/listOneMemReturn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/select.jsp");
				failureView.forward(req, res);
			}
		}
		
		//顯示退貨訂單頁面
		if ("getOne_For_Update_Order_Back".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Shop_OrderBackService soSvc = new Shop_OrderBackService();
				Shop_OrderBackVO soVO = soSvc.getOrderOfOrderID(shop_order_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("soVO", soVO);
				String url = "/back-end/shop_order/update_shop_order_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop_order/listAllOrderOfOne.jsp");
				failureView.forward(req, res);
			}
		}


		if ("Update_Back".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id").trim());
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				String shop_order_date = new String(req.getParameter("shop_order_date").trim());
				
				Integer shop_order_shipment = null;
				try {
					shop_order_shipment = new Integer(req.getParameter("shop_order_shipment"));
				} catch (NumberFormatException e) {
					shop_order_shipment = 0;
					errorMsgs.add("請選擇出貨方式.");
				}

				Double shop_order_ship_fee = null;
				try {
					shop_order_ship_fee = new Double(req.getParameter("shop_order_ship_fee").trim());
				} catch (NumberFormatException e) {
					shop_order_ship_fee = 0.0;
					errorMsgs.add("運費請填數字.");
				}

				Double shop_order_amount = null;
				try {
					shop_order_amount = new Double(req.getParameter("shop_order_amount").trim());
				} catch (NumberFormatException e) {
					shop_order_amount = 0.0;
					errorMsgs.add("總金額請填數字.");
				}

				Integer shop_order_payment = null;
				try {
					shop_order_payment = new Integer(req.getParameter("shop_order_payment").trim());
				} catch (NumberFormatException e) {
					shop_order_payment = 0;
					errorMsgs.add("請選擇付款方式.");
				}

				String shop_order_memo = new String(req.getParameter("shop_order_memo"));
				
				java.sql.Date shop_order_ship_date = null;
				try {
					shop_order_ship_date = java.sql.Date.valueOf(req.getParameter("shop_order_ship_date").trim());
				} catch (IllegalArgumentException e) {
					shop_order_ship_date = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入日期");
				}
				
				String shop_order_track_no = req.getParameter("shop_order_track_no");
				String shop_order_track_noReg = "^[(a-zA-Z0-9_)]{20}$";
				if (shop_order_track_no == null || shop_order_track_no.trim().length() == 0) {
					errorMsgs.add("貨運追蹤號碼: 請勿空白");
				} else if (!shop_order_track_no.matches(shop_order_track_noReg)) {
					errorMsgs.add("貨運追蹤號碼: 只能是英文和_, 且長度必須是20個");
				}

				Integer shop_order_status = null;
				try {
					shop_order_status = new Integer(req.getParameter("shop_order_status").trim());
				} catch (NumberFormatException e) {
					shop_order_status = 0;
					errorMsgs.add("請選擇訂單狀態.");
				}
				
				if ((shop_order_shipment == 0 && shop_order_payment == 3) ||
						(shop_order_shipment == 1 &&  shop_order_payment == 2)) {
					errorMsgs.add("請選擇正確的宅配方式或付款方式");
				}

				Shop_OrderBackVO soVO = new Shop_OrderBackVO();
				soVO.setShop_order_id(shop_order_id);
				soVO.setMem_id(mem_id);
				soVO.setShop_order_date(shop_order_date);
				soVO.setShop_order_shipment(shop_order_shipment);
				soVO.setShop_order_ship_fee(shop_order_ship_fee);
				soVO.setShop_order_amount(shop_order_amount);
				soVO.setShop_order_payment(shop_order_payment);
				soVO.setShop_order_memo(shop_order_memo);
				soVO.setShop_order_ship_date(shop_order_ship_date);
				soVO.setShop_order_track_no(shop_order_track_no);
				soVO.setShop_order_status(shop_order_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soVO", soVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/shop_order/update_shop_order_back.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Shop_OrderBackService soSvc = new Shop_OrderBackService();
				soVO = soSvc.updateOrderForBack(shop_order_id, mem_id, shop_order_date, 
						shop_order_shipment, shop_order_ship_fee, shop_order_amount, shop_order_payment,
						shop_order_memo, shop_order_ship_date, shop_order_track_no, shop_order_status);
				soVO = soSvc.getOrderOfOrderID(shop_order_id);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soVO", soVO);
				String url = "/back-end/shop_order/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/shop_order/update_shop_order_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 後台退貨訂單修改頁面
		if ("getOne_For_Update_Order_Return_Back".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id"));
				
				/*************************** 2.開始查詢資料 ****************************************/
				Shop_OrderBackService soSvc = new Shop_OrderBackService();
				Shop_OrderBackVO soVO = soSvc.getOrderOfOrderID(shop_order_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("soVO", soVO);
				String url = "/back-end/shop_order/update_shop_order_return_back.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/update_shop_order_return_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		//後台退貨訂單更新送出按鈕
		if("update_Order_Return_Back".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id").trim());
//				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
//				String shop_order_date = new String(req.getParameter("shop_order_date").trim());
				
				Integer shop_return_confirm = null;
				try {
					shop_return_confirm = new Integer(req.getParameter("shop_return_confirm").trim());
				} catch (NumberFormatException e) {
					shop_return_confirm = 0;
					errorMsgs.add("請選擇退貨審核.");
				}
				
				String shop_return_message = req.getParameter("shop_return_message");
				String shop_return_messageReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_，)]{2,300}$";
				if (shop_return_message == null || shop_return_message.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if (!shop_return_message.trim().matches(shop_return_messageReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
				}
				
				String shop_return_rej_reason = req.getParameter("shop_return_rej_reason");
				String shop_return_rej_reasonReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_，)]{2,300}$";
				if (shop_return_rej_reason == null || shop_return_rej_reason.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if (!shop_return_rej_reason.trim().matches(shop_return_rej_reasonReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
				}
				
				Integer shop_return_status = null;
				try {
					shop_return_status = new Integer(req.getParameter("shop_return_status").trim());
				} catch (NumberFormatException e) {
					shop_return_status = 0;
					errorMsgs.add("請選擇退貨狀態.");
				}
				
				Shop_OrderBackVO soVO = new Shop_OrderBackVO();
				soVO.setShop_order_id(shop_order_id);
//				soVO.setMem_id(mem_id);
//				soVO.setShop_order_date(shop_order_date);
				soVO.setShop_return_confirm(shop_return_confirm);
				soVO.setShop_return_message(shop_return_message);
				soVO.setShop_return_rej_reason(shop_return_rej_reason);
				soVO.setShop_return_status(shop_return_status);
				
				if (!errorMsgs.isEmpty()) {
					
					req.setAttribute("soVO", soVO); 
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/shop_order/update_shop_order_return_back.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				Shop_OrderBackService soSvc = new Shop_OrderBackService();
				soVO = soSvc.updateOrderReturnForBack(shop_order_id, shop_return_confirm, 
						shop_return_message, shop_return_rej_reason, shop_return_status);
				soVO = soSvc.getOrderOfOrderID(shop_order_id);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soVO", soVO);
				String url = "/back-end/shop_order/listOneMem.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				System.out.println("錯誤3");
				successView.forward(req, res);
				
			} catch (Exception e) {
				System.out.println("錯誤2");
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/shop_order/update_shop_order_return_back.jsp");
				failureView.forward(req, res);
			}
		}
		
		// 顯示一個人全部的退貨訂單列表
		if ("getAll_For_Shop_Oreder_Return".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				/*************************** 2.開始查詢資料 ****************************************/
				Shop_OrderBackService soSvc = new Shop_OrderBackService();
				Set<Shop_OrderBackVO> set = soSvc.getAllOrderReturnOfOne(mem_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAllOrderOfOne", set);
				String url = "/back-end/shop_order/listAllOrderReturnOfOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		// 顯示一個人全部的訂單列表
		if ("getAll_For_Shop_Oreder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				
				/*************************** 2.開始查詢資料 ****************************************/
				Shop_OrderBackService soSvc = new Shop_OrderBackService();
				Set<Shop_OrderBackVO> set = soSvc.getAllOrderOfOne(mem_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAllOrderOfOne", set);
				String url = "/back-end/shop_order/listAllOrderOfOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		//刪除訂單
		if ("delete_Shop_Order_Id_Back".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/***************************1.接收請求參數***************************************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id"));
				/***************************2.開始刪除資料***************************************/
				Shop_OrderBackService soSvc = new Shop_OrderBackService();
				Shop_Order_DetailsService sodSvc = new Shop_Order_DetailsService();
				
				sodSvc.deleteOrderDetails(shop_order_id);
				soSvc.deleteShopOrderForBack(shop_order_id);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/shop_order/listAllShopOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
				/***************************其他可能的錯誤處理**********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/shop_order/listAllShopOrder.jsp");
				failureView.forward(req, res);
			}
		}
	}
}
