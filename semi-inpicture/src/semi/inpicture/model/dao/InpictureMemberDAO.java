package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

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
}
