package com.shop_order.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.shop_order.model.*;

public class Shop_OrderFrontService {

	private Shop_OrderFrontDAO_interface dao;

	public Shop_OrderFrontService() {
		dao = new Shop_OrderFrontDAO();
	}
	
	
//	//前台更新退訂
//	public Shop_OrderFrontVO updateReturnForFront(Integer shop_order_id, Integer mem_id,
//			String shop_order_date, Integer shop_order_shipment, Double shop_order_ship_fee, 
//			Double shop_order_amount, Integer shop_order_payment, String shop_order_memo, 
//			String shop_order_ship_date, String shop_order_track_no, Integer shop_order_status,
//			Integer shop_return_apply, String shop_return_apply_date, String shop_return_reason,
//			Integer shop_return_confirm, String shop_return_message, String shop_return_reh_reason,
//			Integer shop_return_status) {
//		
//		Shop_OrderFrontVO soVO = new Shop_OrderFrontVO();
//		
//		soVO.setShop_order_id(shop_order_id);
//		soVO.setMem_id(mem_id);
//		soVO.setShop_order_date(shop_order_date);
//		soVO.setShop_order_shipment(shop_order_shipment);
//		soVO.setShop_order_ship_fee(shop_order_ship_fee);
//		soVO.setShop_order_amount(shop_order_amount);
//		soVO.setShop_order_payment(shop_order_payment);
//		soVO.setShop_order_memo(shop_order_memo);
//		soVO.setShop_order_ship_date(shop_order_ship_date);
//		soVO.setShop_order_track_no(shop_order_track_no);
//		soVO.setShop_order_status(shop_order_status);
//		soVO.setShop_return_apply(shop_return_apply);
//		soVO.setShop_return_apply_date(shop_return_apply_date);
//		soVO.setShop_return_reason(shop_return_reason);
//		soVO.setShop_return_confirm(shop_return_confirm);
//		soVO.setShop_return_message(shop_return_message);
//		soVO.setShop_return_rej_reason(shop_return_reh_reason);
//		soVO.setShop_return_status(shop_return_status);
//		dao.updateOrderReturnFront(soVO);
//
//		
//		return soVO;
//		
//	}
	
	//新增訂單
	public Shop_OrderFrontVO insertShopOrder(Integer mem_id, Integer shop_order_shipment, 
			Double shop_order_ship_fee, Double shop_order_amount, Integer shop_order_payment, 
			String shop_order_memo, Integer shop_order_status) {
		Shop_OrderFrontVO soVO = new Shop_OrderFrontVO();
		
		soVO.setMem_id(mem_id);
		soVO.setShop_order_shipment(shop_order_shipment);
		soVO.setShop_order_ship_fee(shop_order_ship_fee);
		soVO.setShop_order_amount(shop_order_amount);
		soVO.setShop_order_payment(shop_order_payment);
		soVO.setShop_order_memo(shop_order_memo);
		soVO.setShop_order_status(shop_order_status);
		dao.insertOrder(soVO);
		
		System.out.println("訂單ID: " + soVO.getShop_order_id());
		return dao.findByShopOrderId(soVO.getShop_order_id());
	}
	
	//前台更新退訂(修改)
	public Shop_OrderFrontVO updateReturnForFront(Integer shop_order_id, Integer mem_id,
			String shop_return_reason) {
		
		Shop_OrderFrontVO soVO = new Shop_OrderFrontVO();
		
		soVO.setShop_order_id(shop_order_id);
		soVO.setMem_id(mem_id);
//		soVO.setShop_return_apply_date(shop_return_apply_date);
		soVO.setShop_return_reason(shop_return_reason);
		dao.updateOrderReturnFront(soVO);

		
		return soVO;
		
	}
	
	//前台更新訂單
	public Shop_OrderFrontVO updateOrderForFront(Integer shop_order_id, Integer mem_id,
			String shop_order_date, Integer shop_order_shipment, Double shop_order_ship_fee, 
			Double shop_order_amount, Integer shop_order_payment, String shop_order_memo, 
			String shop_order_ship_date, String shop_order_track_no, Integer shop_order_status) {

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
		dao.updateOrderForFront(soVO);
		
		return soVO;
	}	
	
	//查詢列表使用訂單ID
	public Shop_OrderFrontVO getOrderOfOrderID(Integer shop_order_id) {
		return dao.findByShopOrderId(shop_order_id);
	}
	
	//單一會員一筆訂單查詢
	public Shop_OrderFrontVO getOrderOfMemID(Integer mem_id) {
		return dao.findByPrimaryKey(mem_id);
	}
	
	//顯示一個人全部的退貨訂單
	public Set<Shop_OrderFrontVO> getAllReturnOfOne(Integer mem_id) {
		return dao.getAllReturnOfOne(mem_id);
	}
	
	//顯示一人全部訂單
	public Set<Shop_OrderFrontVO> getAllOrderOfOne(Integer mem_id) {
		return dao.getAllOrderOfOne(mem_id);
	}
}
