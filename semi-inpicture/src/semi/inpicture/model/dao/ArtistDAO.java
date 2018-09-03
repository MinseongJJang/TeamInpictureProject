package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import semi.inpicture.model.dto.ArtDTO;
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
	public ArrayList<ArtDTO> getArtistList(PagingBean pagingBean) throws SQLException{
	      ArrayList<ArtDTO> list=new ArrayList<ArtDTO>();
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      try {
	         con=getConnection();
	         StringBuilder sql=new StringBuilder();
	         sql.append("select im.id, im.name, a.art_main_pic ");
	         sql.append("from ( select id, name, row_number() over(order by id desc) as rnum from inpicture_member ");
	         sql.append(") im, art a where im.id=a.id and rnum between ? and ?");
	         pstmt=con.prepareStatement(sql.toString());
		 	 pstmt.setInt(1, pagingBean.getStartRowNumber());
		 	 pstmt.setInt(2, pagingBean.getEndRowNumber());
	         rs=pstmt.executeQuery();
	         while(rs.next()) {
	            InpictureMemberDTO dto=new InpictureMemberDTO();
	            dto.setId(rs.getString(1));
	            dto.setName(rs.getString(2));
	            ArtDTO adto = new ArtDTO();
	            adto.setArtMainPic(rs.getString(3));
	            adto.setInpictureMemberDTO(dto);
	            list.add(adto);
	         }
	      }finally {
	         closeAll(pstmt,rs,con);
	      }
	      return list;
	   }
}
