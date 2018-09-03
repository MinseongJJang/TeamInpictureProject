package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtDAO;
import semi.inpicture.model.dto.ArtDTO;

public class DetailArtController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String artNo=request.getParameter("artNo");
		try {
			ArtDTO dto = ArtDAO.getInstance().artDetail(artNo);
			request.setAttribute("dto", dto);
			request.setAttribute("url", "/art/art_detail.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/template/layout.jsp";
	}
}
