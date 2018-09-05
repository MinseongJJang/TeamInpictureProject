package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.InpictureMemberDAO;

public class ChangeMemberTypeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		try {
			System.out.println("change");
			InpictureMemberDAO.getInstance().changeMemberType(request.getParameter("id"), request.getParameter("content"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/front?command=ApplyArtistList";
	}

}
