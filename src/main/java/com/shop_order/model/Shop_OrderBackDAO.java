package com.shop_order.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Shop_OrderBackDAO implements Shop_OrderBackDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB3");
		} catch(NamingException e) {
			e.printStackTrace();
		}
	}

//	String driver = "com.mysql.cj.jdbc.Driver";
//	String url = "jdbc:mysql://localhost:3306/cfa103g2?serverTimezone=Asia/Taipei";
//	String userid = "root";
//	String passwd = "9526";

	private static final String GET_ORDER_OFID = // 使用訂單ID查詢
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
			+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
			+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
			+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status FROM shop_order where shop_order_id = ?";
	private static final String GET_ORDER_OFONE = // 使用會員ID查詢
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
					+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
					+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
					+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status FROM shop_order where mem_id = ?";
	private static final String GET_ALL_OFONE_RETURN = // 查詢訂單(使用會員ID以及退貨申請查詢)
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
					+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
					+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
					+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status FROM shop_order where mem_id = ? AND shop_return_apply = 1";
	private static final String GET_ORDER_RETURN_BY_ORDERID = // 查詢退貨訂單(使用訂單ID以及退貨申請查詢)
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
					+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
					+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
					+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status FROM shop_order where shop_order_id = ? AND shop_return_apply = 1";
	private static final String UPDATE_ORDER_BACK = // 更改訂單使用訂單ID
			"UPDATE shop_order set shop_order_shipment=?, shop_order_ship_fee=?, shop_order_amount=?,"
					+ " shop_order_payment=?, shop_order_ship_date=?, shop_order_track_no=?, shop_order_status=?"
					+ " where shop_order_id=?";
	private static final String UPDATE_ORDER_RETURN_BACK = // 更改退貨訂單使用訂單ID
			"UPDATE shop_order set shop_return_confirm=?, shop_return_message=?, shop_return_rej_reason=?,"
			+ " shop_return_status=? where shop_order_id=?";
	private static final String GET_ALL_ORDER_RETURN = // 搜尋全部退貨
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
					+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
					+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
					+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status"
					+ " FROM shop_order where shop_return_apply = 1 order by shop_order_id";
	private static final String GET_ALL_ORDER = // 搜尋全部
			"SELECT shop_order_id,mem_id,shop_order_date,shop_order_shipment,shop_order_ship_fee,"
					+ "shop_order_amount,shop_order_payment,shop_order_memo,shop_order_ship_date,shop_order_track_no,"
					+ "shop_order_status,shop_return_apply,shop_return_apply_date,shop_return_reason,"
					+ "shop_return_confirm,shop_return_message,shop_return_rej_reason,shop_return_status"
					+ " FROM shop_order order by shop_order_id";
	private static final String DELETE_FOR_BACK = "DELETE FROM shop_order where shop_order_id=?";

	//更新訂單
	@Override
	public void updateOrderForBack(Shop_OrderBackVO soVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDER_BACK);

			pstmt.setInt(1, soVO.getShop_order_shipment());
			pstmt.setDouble(2, soVO.getShop_order_ship_fee());
			pstmt.setDouble(3, soVO.getShop_order_amount());
			pstmt.setInt(4, soVO.getShop_order_payment());
			pstmt.setDate(5, soVO.getShop_order_ship_date());
			pstmt.setString(6, soVO.getShop_order_track_no());
			pstmt.setInt(7, soVO.getShop_order_status());
			pstmt.setInt(8, soVO.getShop_order_id());

			pstmt.executeUpdate();

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

	//更新退貨訂單
	@Override
	public void updateOrderReturn(Shop_OrderBackVO soVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDER_RETURN_BACK );
			
			pstmt.setInt(1, soVO.getShop_return_confirm());
			pstmt.setString(2, soVO.getShop_return_message());
			pstmt.setString(3, soVO.getShop_return_rej_reason());
			pstmt.setInt(4, soVO.getShop_return_status());
			pstmt.setInt(5, soVO.getShop_order_id());
			
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

	//訂單查詢(使用訂單ID)
	@Override
	public Shop_OrderBackVO findOrderByShopOrderId(Integer shop_order_id) {
		Shop_OrderBackVO soVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDER_OFID);

			pstmt.setInt(1, shop_order_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				soVO = new Shop_OrderBackVO();
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getDate("shop_order_ship_date"));
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
		return soVO;
	}

	//退貨訂單查詢(使用訂單ID)
	@Override
	public Shop_OrderBackVO findOrderReturnByShopOrderId(Integer shop_order_id) {
		Shop_OrderBackVO soVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ORDER_RETURN_BY_ORDERID);

			pstmt.setInt(1, shop_order_id);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				soVO = new Shop_OrderBackVO();
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getDate("shop_order_ship_date"));
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
		return soVO;
	}

	//查詢一個人全部退貨訂單
	@Override
	public Set<Shop_OrderBackVO> getAllReturnOfOne(Integer mem_id) {
		Set<Shop_OrderBackVO> set = new LinkedHashSet<Shop_OrderBackVO>();
		Shop_OrderBackVO soVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_OFONE_RETURN);
			
			pstmt.setInt(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				soVO = new Shop_OrderBackVO();
				
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getDate("shop_order_ship_date"));
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

	//查詢一個人全部訂單
	@Override
	public Set<Shop_OrderBackVO> getAllOrderOfOne(Integer mem_id) {
		Set<Shop_OrderBackVO> set = new LinkedHashSet<Shop_OrderBackVO>();
		Shop_OrderBackVO soVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(GET_ORDER_OFONE);
			
			pstmt.setInt(1, mem_id);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				soVO = new Shop_OrderBackVO();
				
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getDate("shop_order_ship_date"));
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

	//顯示全部退貨訂單
	@Override
	public List<Shop_OrderBackVO> getAllReturn() {
		List<Shop_OrderBackVO> list = new ArrayList<Shop_OrderBackVO>();
		Shop_OrderBackVO soVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ORDER_RETURN);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				soVO = new Shop_OrderBackVO();
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getDate("shop_order_ship_date"));
				soVO.setShop_order_track_no(rs.getString("shop_order_track_no"));
				soVO.setShop_order_status(rs.getInt("shop_order_status"));
				soVO.setShop_return_apply(rs.getInt("shop_return_apply"));
				soVO.setShop_return_apply_date(rs.getString("shop_return_apply_date"));
				soVO.setShop_return_reason(rs.getString("shop_return_reason"));
				soVO.setShop_return_confirm(rs.getInt("shop_return_confirm"));
				soVO.setShop_return_message(rs.getString("shop_return_message"));
				soVO.setShop_return_rej_reason(rs.getString("shop_return_rej_reason"));
				soVO.setShop_return_status(rs.getInt("Shop_return_status"));
				list.add(soVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error ocured. " + se.getMessage());
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
		return list;
	}

	//顯示全部訂單
	@Override
	public List<Shop_OrderBackVO> getAllOrder() { 
		List<Shop_OrderBackVO> list = new ArrayList<Shop_OrderBackVO>();
		Shop_OrderBackVO soVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_ORDER);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				soVO = new Shop_OrderBackVO();
				soVO.setShop_order_id(rs.getInt("shop_order_id"));
				soVO.setMem_id(rs.getInt("mem_id"));
				soVO.setShop_order_date(rs.getString("shop_order_date"));
				soVO.setShop_order_shipment(rs.getInt("shop_order_shipment"));
				soVO.setShop_order_ship_fee(rs.getDouble("shop_order_ship_fee"));
				soVO.setShop_order_amount(rs.getDouble("Shop_order_amount"));
				soVO.setShop_order_payment(rs.getInt("shop_order_payment"));
				soVO.setShop_order_memo(rs.getString("Shop_order_memo"));
				soVO.setShop_order_ship_date(rs.getDate("shop_order_ship_date"));
				soVO.setShop_order_track_no(rs.getString("shop_order_track_no"));
				soVO.setShop_order_status(rs.getInt("shop_order_status"));
				soVO.setShop_return_apply(rs.getInt("shop_return_apply"));
				soVO.setShop_return_apply_date(rs.getString("shop_return_apply_date"));
				soVO.setShop_return_reason(rs.getString("shop_return_reason"));
				soVO.setShop_return_confirm(rs.getInt("shop_return_confirm"));
				soVO.setShop_return_message(rs.getString("shop_return_message"));
				soVO.setShop_return_rej_reason(rs.getString("shop_return_rej_reason"));
				soVO.setShop_return_status(rs.getInt("Shop_return_status"));
				list.add(soVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error ocured. " + se.getMessage());
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
		return list;
	}

	//刪除
	@Override
	public void deleteShopOrder(Integer shop_order_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE_FOR_BACK);

			pstmt.setInt(1, shop_order_id);

			pstmt.executeUpdate();

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
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}



	public static void main(String[] args) {
		Shop_OrderBackDAO dao = new Shop_OrderBackDAO();

//		dao.deleteShopOrder(6);

//		Shop_OrderVO soVO2 = new Shop_OrderVO();
//		soVO2.setMem_id(1006);
//		soVO2.setShop_order_shipment(1);
//		soVO2.setShop_order_ship_fee(100.0);
//		soVO2.setShop_order_amount(1200.0);
//		soVO2.setShop_order_payment(2);
//		soVO2.setShop_order_ship_date(null);
//		soVO2.setShop_order_track_no(null);
//		soVO2.setShop_order_status(3);
//		dao.updateForBack(soVO2);
		
//		Shop_OrderBackVO soVO3 = new Shop_OrderBackVO();
//		
//		soVO3.setShop_return_confirm(4);
//		soVO3.setShop_return_message("測試");
//		soVO3.setShop_return_rej_reason("測試");
//		soVO3.setShop_return_status(4);
//		soVO3.setShop_order_id(3);
//		
//		dao.updateOrderReturn(soVO3);

		// 查詢
		Shop_OrderBackVO soVO = dao.findOrderByShopOrderId(1);
		System.out.print(soVO.getShop_order_shipment() + ",");
		System.out.print(soVO.getMem_id() + ",");
		System.out.print(soVO.getShop_order_date() + ",");
		System.out.println(soVO.getShop_order_amount());
//		System.out.print(soVO.getSal() + ",");
//		System.out.print(soVO.getComm() + ",");
//		System.out.println(soVO.getDeptno());
		System.out.println("---------------------");

		// 顯示全部
//		List<Shop_OrderBackVO> list = dao.getAllOrder();
//		for (Shop_OrderBackVO aSo : list) {
//			System.out.print(aSo.getShop_order_id() + ",");
//			System.out.print(aSo.getMem_id() + ",");
//			System.out.print(aSo.getShop_order_date() + ",");
//			System.out.println();
//		}
	}

}
