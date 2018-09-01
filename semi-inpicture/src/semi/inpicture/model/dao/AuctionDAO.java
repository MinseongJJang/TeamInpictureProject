package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.AuctionDTO;
import semi.inpicture.model.dto.BidderDTO;

public class AuctionDAO {
	private DataSource dataSource;
	private static AuctionDAO instance;
	private AuctionDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static AuctionDAO getInstance() {
		if(instance==null){
			instance = new AuctionDAO();
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
	
	/**
	 * 경매의 전체목록을 불러오기 위하여
	 * self join과 subquery 사용 후
	 * map을 반환
	 * @kms
	 */
	public Map<AuctionDTO,BidderDTO> getAuctionList() throws SQLException{
		Map<AuctionDTO, BidderDTO> map = new HashMap<AuctionDTO,BidderDTO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select auction_no,auction_title,auction_promptly_price,(select max(auction_bid_price) " + 
					"from bid where auction_no = a.auction_no) as auction_bid_price, to_char(auction_end_time,'HH:MI:SS') " + 
					"from auction a ";
			// 현재 경매 진행중인 전체 경매 상품의 no,title,promptlyprice,최고입찰가,endtime을 받아온다.
			// 각각의 no의 해당되는 최고입찰가를 받아옴
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				AuctionDTO auction = new AuctionDTO();
				AuctionApplyDTO applyAuction = new AuctionApplyDTO(); // auction setAuctionApply에 담기위함
				BidderDTO bidder = new BidderDTO();//해당경매의 최고입찰가를 가져오기위함.
				System.out.println(Integer.MAX_VALUE);
				applyAuction.setAuctionNo(rs.getString(1));
				applyAuction.setAuctionTitle(rs.getString(2));
				applyAuction.setAuctionPromptlyPrice(rs.getInt(3));
				
				bidder.setAuctionBidPrice(rs.getInt(4));
				applyAuction.setAuctionEndTime(rs.getString(5));
				bidder.setAuctionDTO(auction);
				auction.setAuctionApplyDTO(applyAuction);
				
				map.put(auction, bidder);
				System.out.println(map.toString());
				
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return map;
	}
	
	
}
