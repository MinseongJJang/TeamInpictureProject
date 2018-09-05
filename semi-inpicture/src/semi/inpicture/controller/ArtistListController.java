package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtistDAO;
import semi.inpicture.model.dao.ListVO;
import semi.inpicture.model.dao.PagingBean;
import semi.inpicture.model.dto.ArtistDTO;


public class ArtistListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException{
		try {
		int totalPostCount=ArtistDAO.getInstance().getTotalPostCount();
		String pageNo=request.getParameter("pageNo");
		PagingBean pagingBean=null;
		if(pageNo==null) {
			pagingBean=new PagingBean(totalPostCount);
		}
		else {
			pagingBean=new PagingBean(totalPostCount,Integer.parseInt(pageNo));	
		}
		ArrayList<ArtistDTO> list = ArtistDAO.getInstance().getArtistList(pagingBean);
		ListVO ldto=new ListVO();
		ldto.setArtistList(list);
		ldto.setPb(pagingBean);
		request.setAttribute("ldto", ldto);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("url", "/artist/artist_list.jsp");		
		return "/template/layout.jsp";
	}
	
}
