package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtistDAO;
import semi.inpicture.model.dto.ArtistDTO;

public class DetailArtistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		ArtistDTO adto = null;
		try {
			adto = ArtistDAO.getInstance().detailArtist(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("adto",adto);
		request.setAttribute("url", "/artist/artist_detail.jsp");
		return "/template/layout.jsp";	
	}

}
