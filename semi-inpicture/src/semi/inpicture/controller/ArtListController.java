package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtDAO;
import semi.inpicture.model.dao.PagingArt;
import semi.inpicture.model.dto.ArtDTO;

public class ArtListController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	      ArrayList<ArtDTO> list=null;      
	      PagingArt paging = null;
	      String rnum = request.getParameter("rnum");
	      String id=request.getParameter("id");
	      try {
	         int totalArt = ArtDAO.getInstance().getTotalArt(id);
	         if(rnum ==null) {
	            paging = new PagingArt(totalArt);
	         }else {
	            paging = new PagingArt(Integer.parseInt(rnum),totalArt);
	         }
	         list = ArtDAO.getInstance().getArtOfArtist(paging,id);
	         System.out.println(list.size());
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      request.setAttribute("paging",paging);
	      request.setAttribute("list", list);
	      request.setAttribute("url", "/art/art_list.jsp");      
	      return "/template/layout.jsp";
	}
}
