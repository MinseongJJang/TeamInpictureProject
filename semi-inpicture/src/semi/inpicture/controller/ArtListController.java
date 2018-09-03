package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtDAO;
import semi.inpicture.model.dto.ArtDTO;

public class ArtListController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<ArtDTO> list=null;
		try {
			list = ArtDAO.getInstance().artAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("list", list);
		request.setAttribute("url", "/layout/artlist.jsp");		
		return "/template/layout.jsp";

	}
	
}
