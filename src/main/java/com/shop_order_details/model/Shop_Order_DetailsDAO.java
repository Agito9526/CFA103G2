package com.shop_order_details.model;

import java.sql.*;
import java.util.*;

public class Shop_Order_DetailsDAO implements Shop_Order_DetailsDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cfa103g2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "9526";
	
	
	private static final String INSERT_DETAILS = //新增訂單明細
			"INSERT INTO shop_order_details (shop_order_id, prod_id, store_id, shop_order_qty, shop_order_unit_price) "
			+ "VALUES (?, ?, ?, ?, ?)";
	private static final String UPDATE_DETAILS_OFID = //更新訂單明細(給前台評分)
			"UPDATE shop_order_details set commemt_satis=?, commemt_content=?, commemt_date=now() where "
			+ "shop_order_id=? AND prod_id=?";
	private static final String GET_DETAILS_OFSOID_AND_PRODID = //使用訂單ID以及商品ID查詢訂單明細
			"SELECT shop_order_id,prod_id,store_id,shop_order_qty,shop_order_unit_price,"
			+ "commemt_satis,commemt_content,commemt_date FROM shop_order_details where shop_order_id=? AND prod_id=?";
	private static final String GET_DETAILS_OFID = //使用訂單ID查詢訂單明細
			"SELECT shop_order_id,prod_id,store_id,shop_order_qty,shop_order_unit_price,"
			+ "commemt_satis,commemt_content,commemt_date FROM shop_order_details where shop_order_id=?";
	private static final String DELETE_FOR_SHOP_DETAILS = //刪除明細
			"DELETE FROM shop_order_details where shop_order_id=?";
	

	//新增訂單明細
	@Override
	public void insertOrderDetails(Shop_Order_DetailsVO sodVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_DETAILS);
			
			pstmt.setInt(1, sodVO.getShop_order_id());
			pstmt.setInt(2, sodVO.getProd_id());
			pstmt.setInt(3, sodVO.getStore_id());
			pstmt.setInt(4, sodVO.getShop_order_qty());
			pstmt.setInt(5, sodVO.getShop_order_unit_price());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}
	
	//使用訂單ID以及商品ID查詢訂單明細
	@Override
	public Shop_Order_DetailsVO findOneDetail(Integer shop_order_id, Integer prod_id) {
		Shop_Order_DetailsVO sodVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_DETAILS_OFSOID_AND_PRODID);
			
			pstmt.setInt(1, shop_order_id);
			pstmt.setInt(2, prod_id);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sodVO = new Shop_Order_DetailsVO();
				sodVO.setShop_order_id(rs.getInt("shop_order_id"));
				sodVO.setProd_id(rs.getInt("prod_id"));
				sodVO.setStore_id(rs.getInt("store_id"));
				sodVO.setShop_order_qty(rs.getInt("shop_order_qty"));
				sodVO.setShop_order_unit_price(rs.getInt("shop_order_unit_price"));
				sodVO.setCommemt_satis(rs.getInt("commemt_satis"));
//				String i;
//				if (rs.getString("commemt_content")==null) {
//					i = "";
//				} else {
//					i = rs.getString("commemt_content");
//				}
				sodVO.setCommemt_content(rs.getString("commemt_content"));
				sodVO.setCommemt_date(rs.getString("commemt_date"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't lod database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return sodVO;
	}
	//更新訂單明細評分
	@Override
	public void updateOrderDetails(Shop_Order_DetailsVO sodVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url , userid, passwd);
			pstmt = con.prepareStatement(UPDATE_DETAILS_OFID);
			
			pstmt.setInt(1, sodVO.getCommemt_satis());
			pstmt.setString(2, sodVO.getCommemt_content());
			pstmt.setInt(3, sodVO.getShop_order_id());
			pstmt.setInt(4, sodVO.getProd_id());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	//顯示一筆訂單所有明細
	@Override
	public Set<Shop_Order_DetailsVO> getAllOfOne(Integer shop_order_id) {
		Set<Shop_Order_DetailsVO> set = new LinkedHashSet<Shop_Order_DetailsVO>();
		Shop_Order_DetailsVO sodVO = null;
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			pstmt = con.prepareStatement(GET_DETAILS_OFID);
			
			pstmt.setInt(1, shop_order_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				sodVO = new Shop_Order_DetailsVO();
				
				sodVO.setShop_order_id(rs.getInt("shop_order_id"));
				sodVO.setProd_id(rs.getInt("prod_id"));
				sodVO.setStore_id(rs.getInt("store_id"));
				sodVO.setShop_order_qty(rs.getInt("shop_order_qty"));
				sodVO.setShop_order_unit_price(rs.getInt("shop_order_unit_price"));
				sodVO.setCommemt_satis(rs.getInt("commemt_satis"));
				sodVO.setCommemt_content(rs.getString("commemt_content"));
				sodVO.setCommemt_date(rs.getString("commemt_date"));
				set.add(sodVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return set;
	}
	
	//刪除明細(需要跟訂單一起動作)
	@Override
		public void deleteOrderDetails(Integer shop_order_id) {

			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(DELETE_FOR_SHOP_DETAILS);
				
				pstmt.setInt(1, shop_order_id);
				
				pstmt.executeUpdate();
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver, "+ e.getMessage());
			} catch (SQLException se) {
				throw new RuntimeException("A database error ocured. " + se.getMessage());
			} finally {
				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
 				}
				if (con != null) {
					try {
						con.close();
					} catch (SQLException se) {
						se.printStackTrace(System.err);
					}
				}
			}
		}
	
	public static void main(String[] args) {
		Shop_Order_DetailsDAO dao = new Shop_Order_DetailsDAO();
//		Set<Shop_Order_DetailsVO> set = dao.getAllOfOne(3);
//		for (Shop_Order_DetailsVO sodVO : set) {
//			System.out.println(sodVO.getShop_order_id());
//		}
		
//		Shop_Order_DetailsVO sodVO = dao.findOneDetail(3, 3);
//		System.out.println(sodVO.getShop_order_id());
//		System.out.print(sodVO.getCommemt_content());
		
		Shop_Order_DetailsVO sodVO1 = new Shop_Order_DetailsVO();
//		sodVO1.setShop_order_id(3);
//		sodVO1.setProd_id(3);
//		sodVO1.setCommemt_satis(2);
//		sodVO1.setCommemt_content("測試");
//		dao.updateOrderDetails(sodVO1);
		
		
		sodVO1.setShop_order_id(2);
		sodVO1.setProd_id(1);
		sodVO1.setStore_id(1);
		sodVO1.setShop_order_qty(1);
		sodVO1.setShop_order_unit_price(1);
		dao.insertOrderDetails(sodVO1);
	}
	
	
}
