package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.inpicture.model.dao.InpictureMemberDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String url = "";
		String password = request.getParameter("password");
		InpictureMemberDTO mvo;
		try {
			mvo = InpictureMemberDAO.getInstance().login(id, password);
			if (mvo != null) {
				HttpSession session = request.getSession();
				session.setAttribute("mvo", mvo);
				url = "redirect:index.jsp";
			} else {
				url = "redirect:fail/login_fail.jsp";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return url;
	}
}
