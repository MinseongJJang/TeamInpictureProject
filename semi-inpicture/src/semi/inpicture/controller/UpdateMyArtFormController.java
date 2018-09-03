package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtDAO;
import semi.inpicture.model.dto.ArtDTO;

public class UpdateMyArtFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArtDTO aDTO = new ArtDTO();
		String artNo = request.getParameter("artNo");
		try {
			aDTO = ArtDAO.getInstance().artDetail(artNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("aDTO",aDTO);
		request.setAttribute("url","/art/update_my_art_form.jsp");
		return "/template/layout.jsp";
	}

}
