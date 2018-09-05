package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.ArtistDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class ArtistDAO {
	private DataSource dataSource;
	private static ArtistDAO instance;
	private ArtistDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static ArtistDAO getInstance() {
		if(instance==null){
			instance = new ArtistDAO();
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
	public ArrayList<ArtistDTO> getArtistList(PagingBean pagingBean) throws SQLException{
	      ArrayList<ArtistDTO> list=new ArrayList<ArtistDTO>();
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      try {
	         con=getConnection();
	         StringBuilder sql=new StringBuilder();
	         sql.append("select a.id, im.name, a.artist_main_pic ");
	         sql.append("from ( select id, artist_main_pic, row_number() over(order by id desc) as rnum from artist ");
	         sql.append(") a, inpicture_member im where im.id=a.id and rnum between ? and ?");
	         pstmt=con.prepareStatement(sql.toString());
		 	 pstmt.setInt(1, pagingBean.getStartRowNumber());
		 	 pstmt.setInt(2, pagingBean.getEndRowNumber());
	         rs=pstmt.executeQuery();
	         while(rs.next()) {
	            InpictureMemberDTO dto=new InpictureMemberDTO();
	            dto.setId(rs.getString(1));
	            dto.setName(rs.getString(2));
	            ArtistDTO adto = new ArtistDTO();
	            adto.setArtist_main_pic(rs.getString(3));
	            adto.setInpictureMemberDTO(dto);
	            list.add(adto);
	         }
	      }finally {
	         closeAll(pstmt,rs,con);
	      }
	      return list;
	   }
	public ArtistDTO detailArtist(String id) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArtistDTO dto = null;
		try {
			con=getConnection();
	        StringBuilder sql=new StringBuilder();
	        sql.append("select a.id, a.artist_intro, im.name, im.email ");
	        sql.append("from ( select id, artist_intro from artist ) a, inpicture_member im ");
	        sql.append("where a.id=im.id and a.id=?");
	        pstmt = con.prepareStatement(sql.toString());
	        pstmt.setString(1, id);
	        rs=pstmt.executeQuery();
	        while(rs.next()) {
	        	InpictureMemberDTO idto = new InpictureMemberDTO();
	        	idto.setId(rs.getString(1));
	        	idto.setName(rs.getString(3));
	        	idto.setEmail(rs.getString(4));
	        	dto = new ArtistDTO();
	        	dto.setArtistIntro(rs.getString(2));
	        	dto.setInpictureMemberDTO(idto);
	        }
		} finally {
			closeAll(pstmt,rs,con);
		}
		return dto;
	}
	public int getTotalPostCount() throws SQLException {
		int totalCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=getConnection();
			String sql="select count(*) from artist";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCount=rs.getInt(1);
		}finally {
			closeAll(pstmt, rs, con);
		}
		return totalCount;
	}
}
