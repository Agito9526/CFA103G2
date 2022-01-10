package com.shop_order_details.model;

import java.util.*;

public interface Shop_Order_DetailsDAO_interface {
	public void insertOrderDetails(Shop_Order_DetailsVO Shop_Order_DetailsVO);//新增訂單明細
	public void updateOrderDetails(Shop_Order_DetailsVO Shop_Order_DetailsVO);//更新訂單明細(評價)
	public Shop_Order_DetailsVO findOneDetail(Integer shop_order_id, Integer prod_id);//顯示一筆明細
	public Set<Shop_Order_DetailsVO> getAllOfOne(Integer shop_order_id);//使用訂單ID查詢所有訂單明細
	public void deleteOrderDetails(Integer shop_order_id);//刪除訂單明細
}
