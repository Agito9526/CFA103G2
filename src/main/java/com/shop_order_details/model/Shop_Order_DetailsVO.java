package com.shop_order_details.model;

public class Shop_Order_DetailsVO implements java.io.Serializable{
	private Integer shop_order_id;
	private Integer prod_id;
	private Integer store_id;
	private Integer shop_order_qty;
	private Integer shop_order_unit_price;
	private Integer commemt_satis;
	private String commemt_content;
	private String commemt_date;
	
	public Integer getShop_order_id() {
		return shop_order_id;
	}
	public void setShop_order_id(Integer shop_order_id) {
		this.shop_order_id = shop_order_id;
	}
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Integer getShop_order_qty() {
		return shop_order_qty;
	}
	public void setShop_order_qty(Integer shop_order_qty) {
		this.shop_order_qty = shop_order_qty;
	}
	public Integer getShop_order_unit_price() {
		return shop_order_unit_price;
	}
	public void setShop_order_unit_price(Integer shop_order_unit_price) {
		this.shop_order_unit_price = shop_order_unit_price;
	}
	public Integer getCommemt_satis() {
		return commemt_satis;
	}
	public void setCommemt_satis(Integer commemt_satis) {
		this.commemt_satis = commemt_satis;
	}
	public String getCommemt_content() {
		return commemt_content;
	}
	public void setCommemt_content(String commemt_content) {
		this.commemt_content = commemt_content;
	}
	public String getCommemt_date() {
		return commemt_date;
	}
	public void setCommemt_date(String commet_date) {
		this.commemt_date = commet_date;
	} 
}
