package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class ArtDAO {
	private DataSource dataSource;
	private static ArtDAO instance;
	private ArtDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static ArtDAO getInstance() {
		if(instance==null){
			instance = new ArtDAO();
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
	public ArtDTO artDetail(String art_no) throws SQLException {
		ArtDTO artDTO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select a.id,b.art_title,b.art_content,b.art_main_pic "
					+ "from artist a,art b "
					+ "where a.id=b.id and art_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(art_no));
			rs = pstmt.executeQuery();
			if(rs.next()) {
				artDTO = new ArtDTO();
				artDTO.setArtNo(art_no);
				artDTO.setArtTitle(rs.getString(2));
				artDTO.setArtContent(rs.getString(3));
				artDTO.setArtMainPic(rs.getString(4));
				InpictureMemberDTO member = new InpictureMemberDTO();
				member.setId(rs.getString(1));
				artDTO.setInpictureMemberDTO(member);
			}
		}finally {
			closeAll(pstmt,rs,con);
		}
		return artDTO;
	}
	public void artRegister(ArtDTO aDTO) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "insert into art(art_no,art_title,art_content,art_main_pic,id) "
					+ "values(art_seq.nextval,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, aDTO.getArtTitle());
			pstmt.setString(2, aDTO.getArtContent());
			pstmt.setString(3, aDTO.getArtMainPic());
			pstmt.setString(4, aDTO.getInpictureMemberDTO().getId());
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt,null,con);
		}
	}
	public void artDelete(String artNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "delete from art where art_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(artNo));
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt,null,con);
		}
	}
	public ArrayList<ArtDTO> artAll() throws SQLException {
	      ArrayList<ArtDTO> list = new ArrayList<ArtDTO>();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = getConnection();
	         StringBuilder sql = new StringBuilder();
	         sql.append("select * from art");	        
	         pstmt = con.prepareStatement(sql.toString());
	         // start, endRowNumber 할당해야 한다
	         //pstmt.setInt(1, pagingBean.getStartRowNumber());
	         //pstmt.setInt(2, pagingBean.getEndRowNumber());
	         rs = pstmt.executeQuery();
	         // 목록에서 게시물 content는 필요없으므로 null로 setting
	         // select no,title,time_posted,hits,id,name
	         while (rs.next()) {
	            list.add(new ArtDTO(rs.getString(2),rs.getString(3),rs.getString(4),null));
	         }
	      } finally {
	         closeAll(pstmt, rs , con);
	      }
	      return list;
	   }
	public void deleteMyArt(String artNo) throws SQLException {
		 Connection con = null;
	     PreparedStatement pstmt = null;
	     try {
	    	 con = getConnection();
	    	 String sql = "delete from art where art_no=?";
	    	 pstmt = con.prepareStatement(sql);
	    	 pstmt.setInt(1,Integer.parseInt(artNo));
	    	 pstmt.executeUpdate();
	     }finally {
	    	 closeAll(pstmt,null,con);
	     }
		
	}
	public void updateMyArt(ArtDTO aDTO) throws SQLException {
		 Connection con = null;
	     PreparedStatement pstmt = null;
	     try {
	    	 con = getConnection();
	    	 String sql = "update ART set art_title=?,art_content=?,art_main_pic=? where art_no=?";
	    	 pstmt = con.prepareStatement(sql);
	    	 pstmt.setString(1,aDTO.getArtTitle());
	    	 pstmt.setString(2, aDTO.getArtContent());
	    	 pstmt.setString(3, aDTO.getArtMainPic());
	    	 pstmt.setInt(4, Integer.parseInt(aDTO.getArtNo()));
	    	 pstmt.executeUpdate();
	     }finally {
	    	 closeAll(pstmt,null,con);
	     }
	}
}
