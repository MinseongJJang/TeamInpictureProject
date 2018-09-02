package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import semi.inpicture.model.dto.AuctionApplyDTO;

public class AuctionApplyDAO {
	private DataSource dataSource;
	private static AuctionApplyDAO instance;
	private AuctionApplyDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static AuctionApplyDAO getInstance() {
		if(instance==null){
			instance = new AuctionApplyDAO();
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
	
	public void appyAuction(AuctionApplyDTO auction) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "insert into auction_apply(auction_no,auction_title,auction_content,auction_begin_time,auction_end_time,auction_main_pic,auction_promptly_price,id,auction_begin_price) values"
					+ "(auction_apply_seq.nextval,?,?,to_date(?,'MM/DD/YYYY HH:MI'),to_date(?,'MM/DD/YYYY HH:MI'),?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auction.getAuctionTitle());
			pstmt.setString(2, auction.getAuctionContent());
			pstmt.setString(3, auction.getAuctionBeginTime());
			pstmt.setString(4, auction.getAuctionEndTime());
			pstmt.setString(5, auction.getAuctionMainPic());
			pstmt.setInt(6, auction.getAuctionPromptlyPrice());
			pstmt.setString(7, auction.getInpictureMemberDTO().getId());
			pstmt.setInt(8, auction.getAuctionBeginPrice());
			
			pstmt.executeUpdate();
			
		}finally {
			closeAll(pstmt, con);
		}
	}
}
