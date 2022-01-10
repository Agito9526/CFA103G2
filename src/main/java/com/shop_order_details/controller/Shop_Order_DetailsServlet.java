package com.shop_order_details.controller;

import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop_order_details.model.*;

/**
 * Servlet implementation class Shop_Order_DetailsServlet
 */
@WebServlet("/Shop_Order_DetailsServlet")
public class Shop_Order_DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Shop_Order_DetailsServlet() {
        super();
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		//顯示一個訂單所有的明細
		if("getAll_Details_Of_One".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id").trim());
				
				/*************************** 2.開始查詢資料 ****************************************/
				Shop_Order_DetailsService sodSvc = new Shop_Order_DetailsService();
				Set<Shop_Order_DetailsVO> set = sodSvc.getAllDetailsOfID(shop_order_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("listAllOrderDetailsOfOne", set);
				String url = "/front-end/shop_order/listAllOrderDetailsOfOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				throw new ServletException(e);
			}
		}
		
		//更新明細(評分)頁面
		if("getOne_For_Update_Details".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id"));
				Integer prod_id = new Integer(req.getParameter("prod_id"));

				/*************************** 2.開始查詢資料 ****************************************/
				Shop_Order_DetailsService sodSvc = new Shop_Order_DetailsService();
				Shop_Order_DetailsVO sodVO = sodSvc.getDetailOfSOidAndProdid(shop_order_id, prod_id);
				
				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("sodVO", sodVO);
				String url = "/front-end/shop_order/update_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			} catch (Exception e) {
				errorMsgs.add("無法取得要修的資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/shop_order/listAllOrderReturnOfOne.jsp");
				failureView.forward(req, res);
			}
		}
		
		//更新訂單明細按鈕(評分)
		if("update_detail".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			
			req.setAttribute("errorMsgs", errorMsgs);
			
			try {
				Integer shop_order_id = new Integer(req.getParameter("shop_order_id"));
				Integer prod_id = new Integer(req.getParameter("prod_id"));
				
				Integer commemt_satis = null;
				try {
					commemt_satis = new Integer(req.getParameter("commemt_satis"));
				} catch (NumberFormatException e) {
					commemt_satis = 0;
					errorMsgs.add("請選擇滿意度.");
				}
				
				String commemt_content = new String(req.getParameter("commemt_content"));
				String commemt_contentReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_，)]{0,100}$";
				if (commemt_content == null || commemt_content.trim().length() == 0) {
					errorMsgs.add("評價內容: 請勿空白");
				} else if (!commemt_content.trim().matches(commemt_contentReg)) {
					errorMsgs.add("評價內容: 只能是中文英文和_， 且長度必須是100個");
				}
				
				Shop_Order_DetailsVO sodVO = new Shop_Order_DetailsVO();
				sodVO.setShop_order_id(shop_order_id);
				sodVO.setProd_id(prod_id);
				sodVO.setCommemt_satis(commemt_satis);
				sodVO.setCommemt_content(commemt_content);
				System.out.println("成功");
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("sodVO", sodVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/shop_order/update_detail.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/*************************** 2.開始修改資料 *****************************************/
				Shop_Order_DetailsService sodSvc = new Shop_Order_DetailsService();
				sodVO = sodSvc.updateDetails(commemt_satis, commemt_content, shop_order_id, prod_id);
				sodVO = sodSvc.getDetailOfSOidAndProdid(shop_order_id, prod_id);
				
				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				req.setAttribute("sodVO", sodVO);
				String url = "/front-end/shop_order/listOneDetail.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				
				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/shop_order/update_detail.jsp");
				failureView.forward(req, res);
			}
		}
	}

}
