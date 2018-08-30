package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import semi.inpicture.model.dto.InpictureMemberDTO;

public class InpictureMemberDAO {
	private DataSource dataSource;
	private static InpictureMemberDAO instance;
	private InpictureMemberDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static InpictureMemberDAO getInstance() {
		if(instance==null){
			instance = new InpictureMemberDAO();
		}
		return instance;
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

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		closeAll(pstmt, null, con);
	}
	public InpictureMemberDTO login(String id, String password) throws SQLException {
		InpictureMemberDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "select name,point from inpicture_member where id=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new InpictureMemberDTO(id, null, rs.getString(1), null, 
						null, null, rs.getInt(2), null);
			}
		} finally {
			closeAll(pstmt, rs, con);
		}
		return dto;
	}
}
