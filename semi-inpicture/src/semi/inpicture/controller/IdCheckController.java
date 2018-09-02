package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.InpictureMemberDAO;

public class IdCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int idCheck = 0;
		try {
			idCheck = InpictureMemberDAO.getInstance().idCheck(id);
			if(idCheck==0) {
				request.setAttribute("responsebody","ok");
			}else {
				request.setAttribute("responsebody","fail");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "AjaxView";
	}

}
