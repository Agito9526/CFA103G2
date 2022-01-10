package com.shop_order.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop_order.model.*;
import com.shop_order_details.model.*;

@WebServlet("/Shop_OrderReturnServlet")
public class Shop_OrderFrontServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Shop_OrderFrontServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 新增訂單(明細)
		if ("insert_Shop_Order_And_Details".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				Integer shop_order_shipment = new Integer(req.getParameter("shop_order_shipment").trim());
				Double shop_order_ship_fee = new Double(req.getParameter("shop_order_ship_fee").trim());
				Double shop_order_amount = new Double(req.getParameter("shop_order_amount").trim());
				Integer shop_order_payment = new Integer(req.getParameter("shop_order_payment").trim());
				String shop_order_memo = new String(req.getParameter("shop_order_memo").trim());
				Integer shop_order_status = new Integer(req.getParameter("shop_order_status").trim());

				Shop_OrderFrontVO soVO = new Shop_OrderFrontVO();
				soVO.setMem_id(mem_id);
				soVO.setShop_order_shipment(shop_order_shipment);
				soVO.setShop_order_ship_fee(shop_order_ship_fee);
				soVO.setShop_order_amount(shop_order_amount);
				soVO.setShop_order_payment(shop_order_payment);
				soVO.setShop_order_memo(shop_order_memo);
				soVO.setShop_order_status(shop_order_status);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soVO", soVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop_order/insertOrder.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 2.開始新增資料 ***************************************/
				Shop_OrderFrontService soSvc = new Shop_OrderFrontService();

				soVO = soSvc.insertShopOrder(mem_id, shop_order_shipment, shop_order_ship_fee, shop_order_amount,
						shop_order_payment, shop_order_memo, shop_order_status);

				System.out.println("測試ID" + soVO.getShop_order_id());
				/*********************** 3.接收請求參數 - 輸入格式的錯誤處理 *************************/
//				while(true) {
//					if(req.getParameter("mem_id").trim() == null) break;
//					int i = 0; i++;
//					Shop_OrderFrontVO[][] soVO2 = {
//					Integer shop_order_id = soVO.getShop_order_id();
//					};
//				};
				while (true) {
					if (req.getParameter("prod_id").trim().trim() == null)
						break;
					int i = 0;
					String shop_order = "s"+ i;
					Integer shop_order_id = soVO.getShop_order_id();
					Integer prod_id = new Integer(req.getParameter("prod_id").trim());
					Integer store_id = new Integer(req.getParameter("store_id").trim());
					Integer shop_order_qty = new Integer(req.getParameter("shop_order_qty").trim());
					Integer shop_order_unit_price = new Integer(req.getParameter("shop_order_unit_price").trim());
					
					Shop_Order_DetailsVO sodVO = new Shop_Order_DetailsVO();
					sodVO.setShop_order_id(shop_order_id);
					sodVO.setProd_id(prod_id);
					sodVO.setStore_id(store_id);
					sodVO.setShop_order_qty(shop_order_qty);
					sodVO.setShop_order_unit_price(shop_order_unit_price);
					
					/*************************** 4.開始新增資料 ***************************************/
					Shop_Order_DetailsService sodSvc = new Shop_Order_DetailsService();
					sodVO = sodSvc.insertDetails(shop_order_id, prod_id, store_id, shop_order_qty, shop_order_unit_price);
					
					System.out.println("迴圈測試");
				}
				/*************************** 5.新增完成,準備轉交(Send the Success view) ***********/
				req.setAttribute("soVO", soVO);
				String url = "/front-end/shop_order/listOneOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add(e.getMessage() + "123");
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop_order/insertOrder.jsp");
				failureView.forward(req, res);
				System.out.println("成功3");
			}
		}

		// 顯示一人全部的訂單列表
		if ("getAll_For_Shop_Oreder".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				Shop_OrderFrontService soSvc = new Shop_OrderFrontService();
				Set<Shop_OrderFrontVO> set = soSvc.getAllOrderOfOne(mem_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAllOrderOfOne", set);
				String url = "/front-end/shop_order/listAllOrderOfOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		// 查詢一人全部退貨列表
		if ("getAll_For_Shop_Return".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());

				/*************************** 2.開始查詢資料 ****************************************/
				Shop_OrderFrontService soSvc = new Shop_OrderFrontService();
				Set<Shop_OrderFrontVO> set = soSvc.getAllReturnOfOne(mem_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("shop_order_return_of_one", set);
				String url = "/front-end/shop_order/listAllOrderReturnOfOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				throw new ServletException(e);
			}
		}

		// 前台退貨訂單修改頁面
		if ("getOne_For_Update_Order_Return_Front".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Shop_OrderFrontService soSvc = new Shop_OrderFrontService();
				Shop_OrderFrontVO soVO = soSvc.getOrderOfOrderID(shop_order_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("soVO", soVO);
				String url = "/front-end/shop_order/update_shop_order_return_front.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop_order/listAllOrderOfOne.jsp");
				failureView.forward(req, res);
			}
		}

		// 前台跟新退貨訂單
		if ("updateOrderReturn_Front".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id").trim());

				Integer mem_id = new Integer(req.getParameter("mem_id").trim());

//				String shop_retrun_apply_date = new String(req.getParameter("shop_retrun_apply_date").trim());

				String shop_return_reason = req.getParameter("shop_return_reason");
				String shop_return_reasonReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_，)]{2,300}$";
				if (shop_return_reason == null || shop_return_reason.trim().length() == 0) {
					errorMsgs.add("請勿空白");
				} else if (!shop_return_reason.trim().matches(shop_return_reasonReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("只能是中、英文字母、數字和_ , 且長度必需在2到300之間");
				}

				Shop_OrderFrontVO soVO = new Shop_OrderFrontVO();
				soVO.setMem_id(mem_id);
				soVO.setShop_order_id(shop_order_id);
				soVO.setShop_return_reason(shop_return_reason);
//				soVO.setShop_return_apply_date(shop_retrun_apply_date);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("soVO", soVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop_order/update_shop_order_return_front.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Shop_OrderFrontService soSvc = new Shop_OrderFrontService();
				soVO = soSvc.updateReturnForFront(shop_order_id, mem_id, shop_return_reason);
				soVO = soSvc.getOrderOfOrderID(shop_order_id);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soVO", soVO);
				String url = "/front-end/shop_order/listOneOrederReturn.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop_order/update_shop_order_return_front.jsp");
				failureView.forward(req, res);
			}
		}

		// 前台修改訂單資料
		if ("getOne_For_Update_Order_Front".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Shop_OrderFrontService soSvc = new Shop_OrderFrontService();
				Shop_OrderFrontVO soVO = soSvc.getOrderOfOrderID(shop_order_id);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("soVO", soVO);
				String url = "/front-end/shop_order/update_shop_order_front.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得要修改的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop_order/listOneMem.jsp");
				failureView.forward(req, res);
			}
		}

		// 送出更新訂單按鈕
		if ("Update_Front".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);

			try {

				Integer shop_order_id = new Integer(req.getParameter("shop_order_id").trim());
				Integer mem_id = new Integer(req.getParameter("mem_id").trim());
				String shop_order_date = new String(req.getParameter("shop_order_date").trim());

				Integer shop_order_shipment = null;
				try {
					shop_order_shipment = new Integer(req.getParameter("shop_order_shipment").trim());
				} catch (NumberFormatException e) {
					shop_order_shipment = 0;
					errorMsgs.add("請選擇出貨方式.");
				}

				Double shop_order_ship_fee = new Double(req.getParameter("shop_order_ship_fee").trim());

				Double shop_order_amount = new Double(req.getParameter("shop_order_amount").trim());

				Integer shop_order_payment = null;
				try {
					shop_order_payment = new Integer(req.getParameter("shop_order_payment").trim());
				} catch (NumberFormatException e) {
					shop_order_payment = 0;
					errorMsgs.add("請選擇付款方式.");
				}

				String shop_order_memo = new String(req.getParameter("shop_order_memo").trim());

				String shop_order_ship_date = new String(req.getParameter("shop_order_ship_date").trim());

				String shop_order_track_no = (req.getParameter("shop_order_track_no").trim());

				Integer shop_order_status = new Integer(req.getParameter("shop_order_status").trim());

				if ((shop_order_shipment == 0 && shop_order_payment == 2))
//						|| (shop_order_shipment == 1 && shop_order_payment == 2)) 
				{
					errorMsgs.add("請選擇正確的宅配方式或付款方式");
				}

				Shop_OrderFrontVO soVO = new Shop_OrderFrontVO();
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
							.getRequestDispatcher("/front-end/shop_order/update_shop_order_front.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始修改資料 *****************************************/
				Shop_OrderFrontService soSvc = new Shop_OrderFrontService();
				soVO = soSvc.updateOrderForFront(shop_order_id, mem_id, shop_order_date, shop_order_shipment,
						shop_order_ship_fee, shop_order_amount, shop_order_payment, shop_order_memo,
						shop_order_ship_date, shop_order_track_no, shop_order_status);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("soVO", soVO);
				String url = "/front-end/shop_order/listOneOrder.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop_order/update_shop_order_front.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
