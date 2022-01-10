package com.shop_order_details.model;

import java.util.Set;

public class Shop_Order_DetailsService {

	private Shop_Order_DetailsDAO_interface dao;
	
	public Shop_Order_DetailsService() {
		dao = new Shop_Order_DetailsDAO();
	}
	
	
	//新增訂單明細
	public Shop_Order_DetailsVO insertDetails(Integer shop_order_id, Integer prod_id,
			Integer store_id, Integer shop_order_qty, Integer shop_order_unit_price) {
		Shop_Order_DetailsVO sodVO = new Shop_Order_DetailsVO();

		sodVO.setShop_order_id(shop_order_id);
		sodVO.setProd_id(prod_id);
		sodVO.setStore_id(store_id);
		sodVO.setShop_order_qty(shop_order_qty);
		sodVO.setShop_order_unit_price(shop_order_unit_price);
		dao.insertOrderDetails(sodVO);
		
		return sodVO;
	}
	
	//更新訂單明細評分
	public Shop_Order_DetailsVO updateDetails(Integer commemt_satis, String commemt_content,
			Integer  shop_order_id, Integer pro_id) {
		Shop_Order_DetailsVO sodVO = new Shop_Order_DetailsVO();
		
		sodVO.setCommemt_satis(commemt_satis);
		sodVO.setCommemt_content(commemt_content);
		sodVO.setShop_order_id(shop_order_id);
		sodVO.setProd_id(pro_id);
		dao.updateOrderDetails(sodVO);
		
		return sodVO;
	}
	
	//使用訂單ID跟商品ID查詢明細
	public Shop_Order_DetailsVO getDetailOfSOidAndProdid (Integer shop_order_id, Integer prod_id) {
		return dao.findOneDetail(shop_order_id, prod_id);
	}
	
	//顯示一筆訂單全部明細
	public Set<Shop_Order_DetailsVO> getAllDetailsOfID(Integer shop_order_id) {
		return dao.getAllOfOne(shop_order_id);
	}
	
	//刪除明細
	public void deleteOrderDetails(Integer shop_order_id) {
		dao.deleteOrderDetails(shop_order_id);
	}
}
