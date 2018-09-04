package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.sql.DataSource;

import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.AuctionDTO;
import semi.inpicture.model.dto.BidderDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

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
	 * join과 subquery 사용 후
	 * map을 반환
	 * @kms
	 */
	public Map<AuctionDTO,BidderDTO> getAuctionList(PagingBean pb) throws SQLException{
		Map<AuctionDTO, BidderDTO> map = new LinkedHashMap<AuctionDTO,BidderDTO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			// 현재 경매 진행중인 전체 경매 상품의 no,title,promptlyprice,최고입찰가,endtime을 받아온다.
			// 그리고 paging 처리를 위해 row_number over를 사용해 auction_no를 desc로 정렬한다.
			String sql = "select a.auction_no,a.auction_title,a.auction_promptly_price,a.bid_price, " + 
					"a.begin_time,a.end_time,a.auction_state,a.auction_seller from " + 
					"(select auction_no,auction_title,auction_promptly_price, " + 
					"(select max(auction_bid_price) from bid where auction_no = a.auction_no) as bid_price, " + 
					"to_char(auction_begin_time, 'HH24:MI') as begin_time, " + 
					"to_char(auction_end_time, 'HH24:MI') as end_time,row_number() over(order by auction_no desc) as rnum, " + 
					"auction_state,auction_seller " + 
					"from auction a) a " + 
					"where rnum between ? and ? and a.auction_state=0 order by a.auction_no desc";
					
			
			// 각각의 no의 해당되는 최고입찰가를 받아옴
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pb.getStartRowNumber());
			pstmt.setInt(2, pb.getEndRowNumber());
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
	
	public int registerAuction(AuctionApplyDTO applyDTO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = getConnection();
			String sql = "insert into auction"
					+ "(auction_no,auction_title,auction_content,auction_begin_time,auction_end_time,"
					+ "auction_final_bid_price,auction_final_bidder,auction_seller,auction_promptly_price,"
					+ "auction_main_pic,auction_begin_price,auction_seller_id) "
					+ "values(?,?,?,to_date(?,'YYYY-MM-DD HH24:MI'),to_date(?,'YYYY-MM-DD HH24:MI'),0,'a',?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, applyDTO.getAuctionNo());
			pstmt.setString(2, applyDTO.getAuctionTitle());
			pstmt.setString(3, applyDTO.getAuctionContent());
			pstmt.setString(4, applyDTO.getAuctionBeginTime());
			pstmt.setString(5, applyDTO.getAuctionEndTime());
			pstmt.setString(6, applyDTO.getInpictureMemberDTO().getName());
			pstmt.setInt(7, applyDTO.getAuctionPromptlyPrice());
			pstmt.setString(8, applyDTO.getAuctionMainPic());
			pstmt.setInt(9, applyDTO.getAuctionBeginPrice());
			pstmt.setString(10, applyDTO.getInpictureMemberDTO().getId());
			result = pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
		return result;
	}
	
	public int getAuctionListCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = getConnection();
			String sql = "select count(-1) from auction where auction_state='0'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				result = rs.getInt(1);
			}
		
		}finally {
			closeAll(pstmt, rs, con);
		}
		return result;
	}
	
	public AuctionDTO getAuctionDetailInfo(String auctionNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctionDTO dto = new AuctionDTO();
		try {
			con = getConnection();
			String sql = "select a.auction_title,a.auction_content, " + 
					"to_char(a.auction_begin_time,'YYYY-MM-DD HH24:MI'), " + 
					"to_char(a.auction_end_time,'YYYY-MM-DD HH24:MI'),a.auction_seller, " + 
					"a.auction_promptly_price,a.auction_state,a.auction_main_pic, " + 
					"a.auction_begin_price,m.id,m.name " + 
					"from auction a , inpicture_member m " + 
					"where a.auction_seller_id = m.id and a.auction_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auctionNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				AuctionApplyDTO applyDTO = new AuctionApplyDTO();
				InpictureMemberDTO memberDTO = new InpictureMemberDTO();
				memberDTO.setName(rs.getString(5));
				applyDTO.setAuctionNo(auctionNo);
				applyDTO.setAuctionTitle(rs.getString(1));
				applyDTO.setAuctionContent(rs.getString(2));
				applyDTO.setAuctionBeginTime(rs.getString(3));
				applyDTO.setAuctionEndTime(rs.getString(4));
				applyDTO.setAuctionPromptlyPrice(rs.getInt(6));
				applyDTO.setAuctionMainPic(rs.getString(8));
				applyDTO.setAuctionBeginPrice(rs.getInt(9));
				dto.setAuctionState(rs.getString(7));
				applyDTO.setInpictureMemberDTO(memberDTO);
				dto.setAuctionApplyDTO(applyDTO);
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return dto;
	}
	
	public int getAuctionMaxBidPrice(String auctionNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int price = 0;
		try {
			con = getConnection();
			String sql = "select max(auction_bid_price) from bidder where auction_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auctionNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				price = rs.getInt(1);
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return price;
	}
	public void endAuction(BidderDTO bidder) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "update auction set auction_final_bid_price=? , auction_final_bidder=?, auction_state=1 where auction_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bidder.getAuctionBidPrice());
			pstmt.setString(2, bidder.getInpictureMemberDTO().getName());
			pstmt.setString(3, bidder.getAuctionDTO().getAuctionApplyDTO().getAuctionNo());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	
}
