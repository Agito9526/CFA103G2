package com.shop_product.model;

import java.sql.Timestamp;

public class Shop_ProductVO implements java.io.Serializable{
	private Integer prod_id;
	private String  prod_name;
	private Integer store_id;
	private Integer prod_type_id;
	private Integer prod_status;
	private Integer prod_price;
	private Integer prod_qty;
	private String prod_intro;
	private Timestamp prod_reg_date;
	private Integer prod_comt_number_count;
	private Integer prod_comt_sum_score;
	private Integer prod_comt_avg_score;
	
	public Integer getProd_id() {
		return prod_id;
	}
	public void setProd_id(Integer prod_id) {
		this.prod_id = prod_id;
	}
	public String getProd_name() {
		return prod_name;
	}
	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Integer getProd_type_id() {
		return prod_type_id;
	}
	public void setProd_type_id(Integer prod_type_id) {
		this.prod_type_id = prod_type_id;
	}
	public Integer getProd_status() {
		return prod_status;
	}
	public void setProd_status(Integer prod_status) {
		this.prod_status = prod_status;
	}
	public Integer getProd_price() {
		return prod_price;
	}
	public void setProd_price(Integer prod_price) {
		this.prod_price = prod_price;
	}
	public Integer getProd_qty() {
		return prod_qty;
	}
	public void setProd_qty(Integer prod_qty) {
		this.prod_qty = prod_qty;
	}
	public String getProd_intro() {
		return prod_intro;
	}
	public void setProd_intro(String prod_intro) {
		this.prod_intro = prod_intro;
	}
	public Timestamp getProd_reg_date() {
		return prod_reg_date;
	}
	public void setProd_reg_date(Timestamp prod_reg_date) {
		this.prod_reg_date = prod_reg_date;
	}
	public Integer getProd_comt_number_count() {
		return prod_comt_number_count;
	}
	public void setProd_comt_number_count(Integer prod_comt_number_count) {
		this.prod_comt_number_count = prod_comt_number_count;
	}
	public Integer getProd_comt_sum_score() {
		return prod_comt_sum_score;
	}
	public void setProd_comt_sum_score(Integer prod_comt_sum_score) {
		this.prod_comt_sum_score = prod_comt_sum_score;
	}
	public Integer getProd_comt_avg_score() {
		return prod_comt_avg_score;
	}
	public void setProd_comt_avg_score(Integer prod_comt_avg_score) {
		this.prod_comt_avg_score = prod_comt_avg_score;
	}
}
