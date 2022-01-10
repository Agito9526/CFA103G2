package com.shop_order.model;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class Shop_OrderBackService {
	
	private Shop_OrderBackDAO_interface dao;
	
	public Shop_OrderBackService() {
		dao = new Shop_OrderBackDAO();
	}
	
	//後台修改訂單
	public Shop_OrderBackVO updateOrderForBack(Integer shop_order_id, Integer mem_id,
			String shop_order_date, Integer shop_order_shipment, Double shop_order_ship_fee, 
			Double shop_order_amount, Integer shop_order_payment, String shop_order_memo, 
			Date shop_order_ship_date, String shop_order_track_no, Integer shop_order_status) {

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
		dao.updateOrderForBack(soVO);
		
		return soVO;
	}
	
	//後台退貨訂單修改
	public Shop_OrderBackVO updateOrderReturnForBack(Integer shop_order_id,
			 Integer shop_return_confirm, String shpo_return_message, 
			 String shop_return_rej_reason, Integer shop_return_status) {
				
		Shop_OrderBackVO soVO = new Shop_OrderBackVO();
		
		soVO.setShop_order_id(shop_order_id);
//		soVO.setMem_id(mem_id);
//		soVO.setShop_order_date(shop_order_date);
//		soVO.setShop_return_apply(shop_return_apply);
//		soVO.setShop_return_apply_date(shop_return_apply_date);
		soVO.setShop_return_confirm(shop_return_confirm);
		soVO.setShop_return_message(shpo_return_message);
		soVO.setShop_return_rej_reason(shop_return_rej_reason);
		soVO.setShop_return_status(shop_return_status);
		dao.updateOrderReturn(soVO);
		
		return soVO;
	}
	//查詢退貨訂單使用訂單ID
	public Shop_OrderBackVO getOrderReturnOfOrderID(Integer shop_order_id) {
		return dao.findOrderReturnByShopOrderId(shop_order_id);
	}
	
	//查詢訂單使用訂單ID
	public Shop_OrderBackVO getOrderOfOrderID(Integer shop_order_id) {
		return dao.findOrderByShopOrderId(shop_order_id);
	}
	
	//一個人全部退貨訂單
	public Set<Shop_OrderBackVO> getAllOrderReturnOfOne(Integer mem_id) {
		return dao.getAllReturnOfOne(mem_id);
	}
	
	//一個人全部訂單
	public Set<Shop_OrderBackVO> getAllOrderOfOne(Integer mem_id) {
		return dao.getAllOrderOfOne(mem_id);
	}
	
	//全部退貨訂單
	public List<Shop_OrderBackVO> getAllOrderReturn() {
		return dao.getAllReturn();
	}
	
	//全部訂單
	public List<Shop_OrderBackVO> getAllOrder() {
		return dao.getAllOrder();
	}
	
	//刪除
	public void deleteShopOrderForBack(Integer shop__order_id) {
		dao.deleteShopOrder(shop__order_id);
	}

//	public Shop_OrderBackVO updateOrderReturnForBack(Integer shop_order_id, Integer mem_id, String shop_order_date,
//			Integer shop_return_confirm, String shop_return_message, String shop_return_rej_reasonReg,
//			Integer shop_return_status) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
