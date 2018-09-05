package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtDAO;
import semi.inpicture.model.dao.ArtistDAO;
import semi.inpicture.model.dao.PagingArt;
import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.ArtistDTO;

public class DetailArtistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ArtDTO> list=null;      
	    PagingArt paging = null;
	    String rnum = request.getParameter("rnum");
		String id = request.getParameter("id");
		ArtistDTO adto = null;
		try {
			 int totalArt = ArtDAO.getInstance().getTotalArt(id);
	         if(rnum ==null) {
	            paging = new PagingArt(totalArt);
	         }else {
	            paging = new PagingArt(Integer.parseInt(rnum),totalArt);
	         }
	         list = ArtDAO.getInstance().getArtOfArtist(paging,id);
	         System.out.println(list.size());
			 adto = ArtistDAO.getInstance().detailArtist(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("paging",paging);
	    request.setAttribute("list", list);
		request.setAttribute("adto",adto);
		request.setAttribute("url2", "/art/art_list.jsp");  
		request.setAttribute("url", "/artist/artist_detail.jsp");
		return "/template/layout.jsp";	
	}

}
