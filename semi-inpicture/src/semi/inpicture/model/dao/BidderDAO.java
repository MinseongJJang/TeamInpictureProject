package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.AuctionDTO;
import semi.inpicture.model.dto.BidderDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class BidderDAO {
	private DataSource dataSource;
	private static BidderDAO instance;
	private BidderDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static BidderDAO getInstance() {
		if(instance==null){
			instance = new BidderDAO();
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
	
	public int registerBid(String auctionNo,int bidPrice,String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int price = 0;
		try {
			con = getConnection();
			String sql = "insert into bid(id,auction_no,auction_bid_price) values(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, auctionNo);
			pstmt.setInt(3, bidPrice);
			pstmt.executeUpdate();
			pstmt.close();
			String sql2 = "select auction_bid_price from bid where id=? and auction_no=?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, id);
			pstmt.setString(2, auctionNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				price = rs.getInt(1);
			}
		}finally {
			closeAll(pstmt, rs,con);
		}
		return price;
	}
	
	public boolean bidIdCheck(String id,String auctionNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "select count(-1) from bid where id=? and auction_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, auctionNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getInt(1)==1)
					flag = true;
			}
		}finally {
			closeAll(pstmt,rs, con);
		}
		
		return flag;
	}
	public int updateBid(String auctionNo, int bidPrice, String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int price =0;
		try {
			con = getConnection();
			String sql = "update bid set auction_bid_price=? where id=? and auction_no =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bidPrice);
			pstmt.setString(2, id);
			pstmt.setString(3, auctionNo);
			pstmt.executeUpdate();
			pstmt.close();
			String sql2 = "select auction_bid_price from bid where id=? and auction_no=?";
			pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, id);
			pstmt.setString(2, auctionNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				price = rs.getInt(1);
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return price;
	}
	
	public BidderDTO getFinalBidder(String auctionNo) throws SQLException {
		BidderDTO dto = new BidderDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select id,auction_bid_price from bid "
					+ "where auction_bid_price = (select max(auction_bid_price) from bid where auction_no=? ) and auction_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auctionNo);
			pstmt.setString(2, auctionNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				InpictureMemberDTO member = new InpictureMemberDTO();
				AuctionDTO auctionDTO = new AuctionDTO();
				AuctionApplyDTO applyDTO = new AuctionApplyDTO();
				applyDTO.setAuctionNo(auctionNo);
				auctionDTO.setAuctionApplyDTO(applyDTO);
				member.setId(rs.getString(1));
				dto.setAuctionBidPrice(rs.getInt(2));
				dto.setInpictureMemberDTO(member);
				dto.setAuctionDTO(auctionDTO);
			}
		}finally {
			closeAll(pstmt,rs, con);
		}
		return dto;
	}
}
