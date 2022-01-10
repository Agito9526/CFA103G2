package com.shop_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Shop_OrderFrontDAO implements Shop_OrderFrontDAO_interface{

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch (NamingException e ) {
			e.printStackTrace();
		}
	}
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cfa103g2?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "9526";
	
	private static final String INSERT_ORDER = //新增訂單
			"INSERT INTO shop_order (mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,shop_order_amount, shop_order_payment,"
			+ "shop_order_memo,shop_order_status) VALUES (?, NOW(), ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_FOR_FRONT = //前台更新訂單
			"UPDATE shop_order set shop_order_shipment=?, shop_order_payment=?, shop_order_memo=?"
			+ " where mem_id=?";
	private static final String GET_ORDER_OFID = //使用訂單ID查詢
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
			+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
			+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
			+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status FROM shop_order where shop_order_id = ?";
	private static final String GET_ORDER_OFONE = //使用會員ID查詢
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
			+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
			+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
			+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status FROM shop_order where mem_id = ?";
	private static final String UPDATE_SHOP_RETURN_FRONT = //前台更新退貨訂單(使用訂單ID)
			"UPDATE shop_order set shop_return_apply=1, shop_return_apply_date=now(), shop_return_reason=? where shop_order_id=?";
	private static final String GET_ALL_OFONE_RETURN = //前台查詢送出的退貨訂單(使用會員ID以及退貨申請查詢)
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
			+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
			+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
			+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status FROM shop_order where mem_id = ? AND shop_return_apply = 1";
	
	
	//新增訂單
	@Override
	public void insertOrder(Shop_OrderFrontVO soVO) {

//		Shop_OrderFrontVO soVO = new Shop_OrderFrontVO();
//		soVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
			String[] cols = { "shop_order_id" };
			
			pstmt = con.prepareStatement(INSERT_ORDER , cols);
			
			pstmt.setInt(1, soVO.getMem_id());
			pstmt.setInt(2, soVO.getShop_order_shipment());
			pstmt.setDouble(3, soVO.getShop_order_ship_fee());
			pstmt.setDouble(4, soVO.getShop_order_amount());
			pstmt.setInt(5, soVO.getShop_order_payment());
			pstmt.setString(6, soVO.getShop_order_memo());
			pstmt.setInt(7, soVO.getShop_order_status());
			
			pstmt.executeUpdate();

			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				soVO.setShop_order_id(new Integer(rs.getString(1))); // 只支援欄位索引值取得自增主鍵值
			} else {
				System.out.println("NO KEYS WERE GENERATED.");
			}
			
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
	
	//前台更新訂單
	@Override
	public void updateOrderForFront(Shop_OrderFrontVO soVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_FOR_FRONT);

			pstmt.setInt(1, soVO.getShop_order_shipment());
			pstmt.setInt(2, soVO.getShop_order_payment());
			pstmt.setString(3, soVO.getShop_order_memo());
			pstmt.setInt(4, soVO.getMem_id());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
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
	
	//更新退貨訂單
	@Override
	public void updateOrderReturnFront(Shop_OrderFrontVO soVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_SHOP_RETURN_FRONT);
			
			pstmt.setString(1, soVO.getShop_return_reason());
			pstmt.setInt(2, soVO.getShop_order_id());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A datebase error occured. "
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	//單一會員一筆訂單查詢
	@Override
	public Shop_OrderFrontVO findByPrimaryKey(Integer mem_id) {
		
		Shop_OrderFrontVO soVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDER_OFONE);
			
			pstmt.setInt(1, mem_id);
			
			rs= pstmt.executeQuery();
			while (rs.next()) {
				soVO = new Shop_OrderFrontVO();
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getString("shop_order_ship_date"));
				soVO.setShop_order_track_no(rs.getString("shop_order_track_no"));
				soVO.setShop_order_status(rs.getInt("shop_order_status"));
				soVO.setShop_return_apply(rs.getInt("shop_return_apply"));
				soVO.setShop_return_apply_date(rs.getString("shop_return_apply_date"));
				soVO.setShop_return_reason(rs.getString("shop_return_reason"));
				soVO.setShop_return_confirm(rs.getInt("shop_return_confirm"));
				soVO.setShop_return_message(rs.getString("shop_return_message"));
				soVO.setShop_return_rej_reason(rs.getString("shop_return_rej_reason"));
				soVO.setShop_return_status(rs.getInt("shop_return_status"));
			}
			
			
		} catch(SQLException se) {
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return soVO;
	}
	
	//單一會員一筆訂單查詢(使用訂單ID)
	@Override
	public Shop_OrderFrontVO findByShopOrderId(Integer shop_order_id) {
		Shop_OrderFrontVO soVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDER_OFID);
			
			pstmt.setInt(1, shop_order_id);
			
			rs= pstmt.executeQuery();
			while (rs.next()) {
				soVO = new Shop_OrderFrontVO();
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getString("shop_order_ship_date"));
				soVO.setShop_order_track_no(rs.getString("shop_order_track_no"));
				soVO.setShop_order_status(rs.getInt("shop_order_status"));
				soVO.setShop_return_apply(rs.getInt("shop_return_apply"));
				soVO.setShop_return_apply_date(rs.getString("shop_return_apply_date"));
				soVO.setShop_return_reason(rs.getString("shop_return_reason"));
				soVO.setShop_return_confirm(rs.getInt("shop_return_confirm"));
				soVO.setShop_return_message(rs.getString("shop_return_message"));
				soVO.setShop_return_rej_reason(rs.getString("shop_return_rej_reason"));
				soVO.setShop_return_status(rs.getInt("shop_return_status"));
			}
			
			
		} catch(SQLException se) {
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
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return soVO;
	}
	
	//顯示一個人全部的退貨訂單
	@Override
	public Set<Shop_OrderFrontVO> getAllReturnOfOne(Integer mem_id) {
		Set<Shop_OrderFrontVO> set = new LinkedHashSet<Shop_OrderFrontVO>();
		Shop_OrderFrontVO soVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_OFONE_RETURN);
			
			pstmt.setInt(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				soVO = new Shop_OrderFrontVO();
				
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getString("shop_order_ship_date"));
				soVO.setShop_order_track_no(rs.getString("shop_order_track_no"));
				soVO.setShop_order_status(rs.getInt("shop_order_status"));
				soVO.setShop_return_apply(rs.getInt("shop_return_apply"));
				soVO.setShop_return_apply_date(rs.getString("shop_return_apply_date"));
				soVO.setShop_return_reason(rs.getString("shop_return_reason"));
				soVO.setShop_return_confirm(rs.getInt("shop_return_confirm"));
				soVO.setShop_return_message(rs.getString("shop_return_message"));
				soVO.setShop_return_rej_reason(rs.getString("shop_return_rej_reason"));
				soVO.setShop_return_status(rs.getInt("shop_return_status"));
				set.add(soVO);
			}
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
	
	//顯示一人全部訂單
	@Override
	public Set<Shop_OrderFrontVO> getAllOrderOfOne(Integer mem_id) {
		Set<Shop_OrderFrontVO> set = new LinkedHashSet<Shop_OrderFrontVO>();
		Shop_OrderFrontVO soVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ORDER_OFONE);
			
			pstmt.setInt(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				soVO = new Shop_OrderFrontVO();
				
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getString("shop_order_ship_date"));
				soVO.setShop_order_track_no(rs.getString("shop_order_track_no"));
				soVO.setShop_order_status(rs.getInt("shop_order_status"));
				soVO.setShop_return_apply(rs.getInt("shop_return_apply"));
				soVO.setShop_return_apply_date(rs.getString("shop_return_apply_date"));
				soVO.setShop_return_reason(rs.getString("shop_return_reason"));
				soVO.setShop_return_confirm(rs.getInt("shop_return_confirm"));
				soVO.setShop_return_message(rs.getString("shop_return_message"));
				soVO.setShop_return_rej_reason(rs.getString("shop_return_rej_reason"));
				soVO.setShop_return_status(rs.getInt("shop_return_status"));
				
				set.add(soVO);
			}
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

	public static void main(String[] args) {
		Shop_OrderFrontDAO dao = new Shop_OrderFrontDAO();
		
//		Set<Shop_OrderFrontVO> set = dao.getAllOrderOfOne(10001);
//		for (Shop_OrderFrontVO soVO : set) {
//			System.out.println(soVO.getMem_id() + ",");
//			System.out.println(soVO.getShop_order_date());
//			
//			System.out.println();
//		}
//		System.out.println("o");
		
////		//查詢
//		Shop_OrderFrontVO soVO = dao.findByShopOrderId(1);
//		System.out.print(soVO.getShop_order_shipment() + ",");
//		System.out.print(soVO.getMem_id() + ",");
//		System.out.print(soVO.getShop_order_date() + ",");
//		System.out.println(soVO.getShop_order_amount());
//		System.out.println(soVO.getShop_order_ship_date() + ",");
////		System.out.print(soVO.getSal() + ",");
////		System.out.print(soVO.getComm() + ",");
////		System.out.println(soVO.getDeptno());
//		System.out.println("---------------------");
		
		//更新退貨訂單
//		Shop_OrderFrontVO soVO3 = new Shop_OrderFrontVO();
////		soVO3.setMem_id(10002);
//		soVO3.setShop_order_id(2);
//		soVO3.setShop_return_reason("測試1");
//		dao.updateOrderReturnFront(soVO3);
		
		// 新增
		Shop_OrderFrontVO soVO1 = new Shop_OrderFrontVO();
		soVO1.setMem_id(10005);
		soVO1.setShop_order_shipment(1);
		soVO1.setShop_order_ship_fee(new Double(123));
		soVO1.setShop_order_amount(new Double(123));
		soVO1.setShop_order_payment(1);
		soVO1.setShop_order_memo("測試");
		soVO1.setShop_order_status(1);
		dao.insertOrder(soVO1);

	}


}
