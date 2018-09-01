package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import semi.inpicture.model.dto.ArtistAttachmentPathDTO;

public class ArtistAttachmentPathDAO {
	private DataSource dataSource;
	private static ArtistAttachmentPathDAO instance;
	private ArtistAttachmentPathDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static ArtistAttachmentPathDAO getInstance() {
		if(instance==null){
			instance = new ArtistAttachmentPathDAO();
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
	public void upload(ArtistAttachmentPathDTO attachDto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "insert into artist_attachment_path values (artist_attachment_no_seq.nextval,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, attachDto.getArtAttachmentPath());
			pstmt.setInt(2, attachDto.getArtist_post_no());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
}
