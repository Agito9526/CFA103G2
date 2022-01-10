package com.shop_order.model;

import java.util.*;

public interface Shop_OrderFrontDAO_interface {
	public void insertOrder(Shop_OrderFrontVO shop_OrderFrontVO);//新增訂單
	public void updateOrderForFront(Shop_OrderFrontVO Shop_OrderVO); //前台更新訂單
	public void updateOrderReturnFront(Shop_OrderFrontVO shop_OrderVO); //更新退貨訂單
	public Shop_OrderFrontVO findByPrimaryKey(Integer mem_id); //單一會員一筆訂單查詢(使用會員ID)
	public Shop_OrderFrontVO findByShopOrderId(Integer shop_order_id); //單一會員一筆訂單查詢(使用訂單ID)
	public Set<Shop_OrderFrontVO> getAllReturnOfOne(Integer mem_id); //顯示一個人全部的退貨訂單
	public Set<Shop_OrderFrontVO> getAllOrderOfOne(Integer mem_id); //顯示一人全部訂單

}
