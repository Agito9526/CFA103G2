package com.shop_order.model;

import java.util.*;

public interface Shop_OrderBackDAO_interface {

	public void updateOrderForBack(Shop_OrderBackVO Shop_OrderVO); //後台更新清單
	public void updateOrderReturn(Shop_OrderBackVO shop_OrderVO); //後台更新退貨清單
	public Shop_OrderBackVO findOrderByShopOrderId(Integer shop_order_id); //單一會員一筆訂單查詢(使用訂單ID)
	public Shop_OrderBackVO findOrderReturnByShopOrderId(Integer shop_order_id); //單一會員一筆退貨訂單查詢(使用訂單ID)
	public Set<Shop_OrderBackVO> getAllReturnOfOne(Integer mem_id); //顯示一個人全部的退貨訂單
	public Set<Shop_OrderBackVO> getAllOrderOfOne(Integer mem_id); //顯示一人全部訂單
	public List<Shop_OrderBackVO> getAllReturn(); //顯示全部退貨列表
	public List<Shop_OrderBackVO> getAllOrder(); //顯示全部列表
	public void deleteShopOrder(Integer shop_order_id); //刪除清單
}
