package semi.inpicture.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import semi.inpicture.model.dto.InpictureMemberDTO;

public class InpictureMemberDAO {
	private DataSource dataSource;
	private static InpictureMemberDAO instance;
	private InpictureMemberDAO() {
		this.dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static InpictureMemberDAO getInstance() {
		if(instance==null){
			instance = new InpictureMemberDAO();
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
	public InpictureMemberDTO login(String id, String password) throws SQLException {
		InpictureMemberDTO dto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();
			String sql = "select name,point,member_type from inpicture_member where id=? and password=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto = new InpictureMemberDTO(id, null, rs.getString(1), null, 
						null, null, rs.getInt(2), rs.getString(3));
			}
		} finally {
			closeAll(pstmt, rs, con);
		}
		return dto;
	}
	public void registerInpictureMember(String id,String password,String name,String address,String ssn,String email) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = getConnection();
			String sql = "insert into inpicture_member(id,password,name,address,ssn,email) values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,password);
			pstmt.setString(3,name);
			pstmt.setString(4,address);
			pstmt.setString(5,ssn);
			pstmt.setString(6,email);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt,null,con);
		}
	}
	public int idCheck(String id) throws SQLException {		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try {
			con = getConnection();
			String sql = "select count(*) from inpicture_member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}finally {
			closeAll(pstmt,rs,con);
		}
		return count;
	}
	   public InpictureMemberDTO myInfoById(String id) throws SQLException {
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      InpictureMemberDTO imDTO = null;
		      try {
		         con = getConnection();
		         String sql = "select id,password,name,address,ssn,email,point,member_type from INPICTURE_MEMBER where id=?";
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1, id);
		         rs = pstmt.executeQuery();
		         if(rs.next()) {
		            imDTO = new InpictureMemberDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getString(8));
		         }
		      }finally {
		         closeAll(pstmt,rs,con);
		      }
		      
		      return imDTO;
		   }
		   public void memberUpdate (InpictureMemberDTO imDTO) throws SQLException {
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      try {
		         con = getConnection();
		         String sql = "update INPICTURE_MEMBER set password=?,address=?,email=? where id=?";
		         pstmt = con.prepareStatement(sql);
		         pstmt.setString(1,imDTO.getPassword());
		         pstmt.setString(2,imDTO.getAddress());
		         pstmt.setString(3,imDTO.getEmail());
		         pstmt.setString(4,imDTO.getId());
		         pstmt.executeUpdate();
		      }finally {
		         closeAll(pstmt,null,con);
		      }
		   }
}
