package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import semi.inpicture.model.dto.ArtistApplyBoardDTO;

public class ArtistApplyBoardDAO {
	private DataSource dataSource;
	private static ArtistApplyBoardDAO instance;
	private ArtistApplyBoardDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static ArtistApplyBoardDAO getInstance() {
		if(instance==null){
			instance = new ArtistApplyBoardDAO();
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
	public int applyArtist(ArtistApplyBoardDTO dto) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int artist_post_no=0;
		try {
			con = getConnection();
			String sql = "insert into artist_apply_board values(artist_apply_board_seq.nextval,?,?,sysdate,?,?)";
			String sql2 = "select artist_apply_board_seq.currval from dual";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, dto.getArtistApplyTitle());
			pstmt.setString(2, dto.getArtistApplyContent());
			pstmt.setString(3, "main");
			pstmt.setString(4, "artist");
			pstmt.executeUpdate();
			pstmt.close();
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				artist_post_no = rs.getInt(1);
			}
			
		} finally {
			closeAll(pstmt, rs, con);
		}
		return artist_post_no;
	}
}
