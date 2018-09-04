package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.InpictureMemberDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class LoginCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String result = "";
		InpictureMemberDTO mvo;
		System.out.println("loginCheck");
		try {
			mvo = InpictureMemberDAO.getInstance().login(id, password);
			if (mvo != null) {
				result ="ok";
			}else {
				result ="fail";
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println(result);
		request.setAttribute("responsebody", result);
		return "AjaxView";
	}

}
