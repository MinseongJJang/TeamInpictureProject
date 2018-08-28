package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class AuctionAttachmentPathDAO {
	private DataSource dataSource;
	private static AuctionAttachmentPathDAO instance;
	private AuctionAttachmentPathDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static AuctionAttachmentPathDAO getInstance() {
		if(instance==null){
			instance = new AuctionAttachmentPathDAO();
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
