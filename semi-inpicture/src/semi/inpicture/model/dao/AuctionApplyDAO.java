package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

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
	/**
	 * auction을 신청하는 로직
	 * auction을 신청하면 form값을 받아와
	 * auction_apply table에 추가함
	 * @kms
	 */
	public void appyAuction(AuctionApplyDTO auction) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "insert into auction_apply(auction_no,auction_title,auction_content,auction_begin_time,auction_end_time,auction_main_pic,auction_promptly_price,id,auction_begin_price) values"
					+ "(auction_apply_seq.nextval,?,?,to_date(?,'MM/DD/YYYY HH24:MI'),to_date(?,'MM/DD/YYYY HH24:MI'),?,?,?,?)";
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
	/**
	 * auction신청 list를 받아오는 로직
	 * @kms
	 */
	public ArrayList<AuctionApplyDTO> getAllAuctionApplyList(PagingBean pb) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<AuctionApplyDTO> auctionList = new ArrayList<AuctionApplyDTO>();
		try {
			con = getConnection();
			//paging 처리를 하기 위하여 auction_no에 row_number를 부여.
			//join과 subquery를 사용
			String sql = "select a.auction_no,a.auction_title,a.id,m.name,a.auction_state,a.auction_main_pic from "
					+ "(select auction_no,auction_title,id,row_number() over(order by auction_no desc) as rnum,auction_state,auction_main_pic from auction_apply) a "
					+ ", inpicture_member m "
					+ " where a.id=m.id and rnum between ? and ? and a.auction_state = 0 order by a.auction_no desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, pb.getStartRowNumber());
			pstmt.setInt(2, pb.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				//setter를 통해 dto에 값을 넣어준다.
				AuctionApplyDTO auctionDTO = new AuctionApplyDTO();
				InpictureMemberDTO memberDTO = new InpictureMemberDTO();
				memberDTO.setName(rs.getString(3));	
				memberDTO.setId(rs.getString(4));
				auctionDTO.setAuctionNo(rs.getString(1));
				auctionDTO.setAuctionTitle(rs.getString(2));
				auctionDTO.setAuctionState(rs.getString(5));
				auctionDTO.setAuctionMainPic(rs.getString(6));
				auctionDTO.setInpictureMemberDTO(memberDTO);
				auctionList.add(auctionDTO);
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return auctionList;
	}
	//경매신청의 상세정보를 가져온다.
	public AuctionApplyDTO getAuctionApplyDetailInfo(String auctionNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AuctionApplyDTO auctionDTO = new AuctionApplyDTO();
		try {
			con = getConnection();
			String sql ="select "
					+ "a.auction_title,a.auction_content,"
					+ "to_char(a.auction_begin_time,'YYYY-MM-DD HH24:MI'),"
					+ "to_char(a.auction_end_time,'YYYY-MM-DD HH24:MI'),"
					+ "a.auction_main_pic,a.auction_promptly_price,a.auction_begin_price,"
					+ "m.id,m.name,a.auction_state from auction_apply a , inpicture_member m "
					+ "where m.id=a.id and a.auction_no=? and a.auction_state = 0";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auctionNo);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				InpictureMemberDTO memberDTO = new InpictureMemberDTO();
				memberDTO.setId(rs.getString(8));
				memberDTO.setName(rs.getString(9));
				auctionDTO.setAuctionNo(auctionNo);
				auctionDTO.setAuctionTitle(rs.getString(1));
				auctionDTO.setAuctionContent(rs.getString(2));
				auctionDTO.setAuctionBeginTime(rs.getString(3));
				auctionDTO.setAuctionEndTime(rs.getString(4));
				auctionDTO.setAuctionMainPic(rs.getString(5));
				auctionDTO.setAuctionPromptlyPrice(rs.getInt(6));
				auctionDTO.setAuctionBeginPrice(rs.getInt(7));
				auctionDTO.setAuctionState(rs.getString(8));
				auctionDTO.setInpictureMemberDTO(memberDTO);
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return auctionDTO;
	}
	
	public int getAuctionApplyListCount() throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		try {
			con = getConnection();
			String sql = "select count(-1) from auction_apply where auction_state='0'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		}finally {
			closeAll(pstmt, rs, con);
		}
		return totalCount;
	}
	
	public void deleteAuctionApply(String auctionNo) throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "delete from auction_apply where auction_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auctionNo);
			pstmt.executeUpdate();
			
		}finally {
			closeAll(pstmt, con);
		}
	}
	
	public int changeApplyAuctionState(String auctionNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = getConnection();
			String sql = "update auction_apply set auction_state=1 where auction_no =?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, auctionNo);
			result = pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
		
		return result;
	}
}
