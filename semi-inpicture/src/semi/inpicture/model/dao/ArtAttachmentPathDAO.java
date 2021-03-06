package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ArtAttachmentPathDAO {
	private DataSource dataSource;
	private static ArtAttachmentPathDAO instance;
	private ArtAttachmentPathDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static ArtAttachmentPathDAO getInstance() {
		if(instance==null){
			instance = new ArtAttachmentPathDAO();
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
