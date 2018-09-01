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
		String art_no=request.getParameter("art_no");
		try {
			ArtDTO dto = ArtDAO.getInstance().artDetail(art_no);
			request.setAttribute("dto", dto);
			request.setAttribute("url", "/layout/artdetail.jsp");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/template/layout.jsp";
	}
}
