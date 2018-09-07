package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import semi.inpicture.model.dto.InpictureMemberDTO;
import semi.inpicture.model.dto.PointHistoryDTO;

public class PointHistoryDAO {
	private DataSource dataSource;
	private static PointHistoryDAO instance;
	
	private PointHistoryDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static PointHistoryDAO getInstance() {
		if (instance == null) {
			instance = new PointHistoryDAO();
		}
		return instance;
	}
	
	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		closeAll(pstmt, null, con);
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public void closeAll(PreparedStatement pstmt, ResultSet rs, Connection con) throws SQLException {
		if (pstmt != null)
			pstmt.close();
		if (rs != null)
			rs.close();
		if (con != null)
			con.close();
	}
	
	public void registerPointHistory(PointHistoryDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "insert into point_history(point_history_no,reg_date,id,payment_method,point) values(point_history_seq.nextval,sysdate,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getMemberDTO().getId());
			pstmt.setString(2, dto.getPaymentMethod());
			pstmt.setInt(3, dto.getPoint());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, rs, con);
		}
	}
	
	public ArrayList<PointHistoryDTO> pointHistoryList(String id,PagingBean pb) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PointHistoryDTO> list = new ArrayList<PointHistoryDTO>();
		
		
		try {
			con = getConnection();
			String sql = "select p.point_history_no,p.reg_date,p.id,p.payment_method,p.point from"
					+ "(select point_history_no,to_char(reg_date,'YYYY-MM-DD HH24:MI') as reg_date,id,payment_method,point,"
					+ "row_number() over(order by point_history_no desc) as rnum from point_history) p where id =? and p.rnum between ? and ? order by point_history_no desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, pb.getStartRowNumber());
			pstmt.setInt(3, pb.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				InpictureMemberDTO member = new InpictureMemberDTO();
				PointHistoryDTO point = new  PointHistoryDTO();
				point.setPointHistoryNo(rs.getString(1));
				point.setRegDate(rs.getString(2));
				member.setId(rs.getString(3));
				point.setMemberDTO(member);
				point.setPaymentMethod(rs.getString(4));
				point.setPoint(rs.getInt(5));
				list.add(point);
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return list;
	}
	
	public ArrayList<PointHistoryDTO> pointHistoryList(String id) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<PointHistoryDTO> list = new ArrayList<PointHistoryDTO>();
		try {
			con = getConnection();
			String sql = "select point_history_no,reg_date,id,payment_method,point from" +
					      " point_history where id =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				InpictureMemberDTO member = new InpictureMemberDTO();
				PointHistoryDTO point = new  PointHistoryDTO();
				point.setPointHistoryNo(rs.getString(1));
				point.setRegDate(rs.getString(2));
				member.setId(rs.getString(3));
				point.setMemberDTO(member);
				point.setPaymentMethod(rs.getString(4));
				point.setPoint(rs.getInt(5));
				list.add(point);
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return list;
	}
	public int totalHistoryCount(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = getConnection();
			String sql = "select count(-1) from point_history where id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt(1);
			}
		}finally {
			closeAll(pstmt,rs, con);
		}
		return result;
	}
}
