package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import semi.inpicture.model.dto.ArtistApplyBoardDTO;
import semi.inpicture.model.dto.ArtistAttachmentPathDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

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
			pstmt.setString(3, dto.getArtistMainPic());
			pstmt.setString(4, dto.getInpictureMemberDTO().getId());
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
	public ArrayList<ArtistApplyBoardDTO> applyArtistList(PagingBean pagingBean) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArtistApplyBoardDTO> list = new ArrayList<ArtistApplyBoardDTO>();
		try {
			con = getConnection();
			StringBuilder sql=new StringBuilder();		
			sql.append("select aab.artist_post_no,aab.artist_apply_title,aab.reg_date,aab.id,im.name ");
			sql.append("from ( ");
			sql.append("select artist_post_no, artist_apply_title, to_char(reg_date,'YYYY.MM.DD') ");			
			sql.append("as reg_date,id,row_number() over(order by artist_post_no desc) ");
			sql.append("as rnum from artist_apply_board ");
			sql.append(") aab, inpicture_member im ");
			sql.append("where aab.id=im.id and rnum between ? and ? ");
			sql.append("order by artist_post_no desc ");				
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()){		
				ArtistApplyBoardDTO dto=new ArtistApplyBoardDTO();
				dto.setArtistPostNo(rs.getString(1));
				dto.setArtistApplyTitle(rs.getString(2));
				dto.setRegdate(rs.getString(3));
				InpictureMemberDTO idto = new InpictureMemberDTO();
				idto.setId(rs.getString(4));
				idto.setName(rs.getString(5));
				dto.setInpictureMemberDTO(idto);
				list.add(dto);			
			}			
		} finally {
			closeAll(pstmt, rs, con);
		}
		return list;
	}
	public ArrayList<ArtistAttachmentPathDTO> applyArtisAttachmentList(String artistApplyNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ArtistAttachmentPathDTO> list = new ArrayList<ArtistAttachmentPathDTO>();
		try {
			con = getConnection();
			StringBuilder sql=new StringBuilder();		
			sql.append("select aap.artist_attachment_path ");
			sql.append("from ( ");
			sql.append("select artist_post_no from artist_apply_board ");			
			sql.append(") aab, artist_attachment_path aap ");
			sql.append("where aab.artist_post_no=aap.artist_post_no and aab.artist_post_no=? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, artistApplyNo);
			rs = pstmt.executeQuery();
			while(rs.next()){		
				ArtistAttachmentPathDTO adto = new ArtistAttachmentPathDTO();
				adto.setArtAttachmentPath(rs.getString(1));
				list.add(adto);			
			}			
		} finally {
			closeAll(pstmt, rs, con);
		}
		return list;
	}
	public int getTotalPostCount() throws SQLException {
		int totalCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="select count(*) from artist_apply_board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCount=rs.getInt(1);
		}finally {
			closeAll(pstmt, rs, con);
		}
		return totalCount;
	}
	public ArtistApplyBoardDTO getPostingByNo(String artistApplyNo) throws SQLException {
		ArtistApplyBoardDTO dto=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select aab.artist_apply_title,to_char(aab.reg_date,'YYYY.MM.DD  HH24:MI:SS') as reg_date, ");
			sql.append("aab.artist_apply_content,aab.id,im.name ");
			sql.append("from artist_apply_board aab,inpicture_member im ");
			sql.append("where aab.id=im.id and aab.artist_post_no=? ");		
			pstmt=con.prepareStatement(sql.toString());	
			pstmt.setString(1,artistApplyNo);
			rs=pstmt.executeQuery();
			if(rs.next()){
				dto=new ArtistApplyBoardDTO();
				dto.setArtistPostNo(artistApplyNo);
				dto.setArtistApplyTitle(rs.getString(1));
				dto.setArtistApplyContent(rs.getString(3));				
				dto.setRegdate(rs.getString(2));
				InpictureMemberDTO idto = new InpictureMemberDTO();
				idto.setId(rs.getString(4));
				idto.setName(rs.getString(5));
				dto.setInpictureMemberDTO(idto);
			}
		}finally{
			closeAll(pstmt, rs,con);
		}
		return dto;
	}

}
