package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.InpictureMemberDAO;

public class RegisterMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String ssn = request.getParameter("ssn");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String email = email1+"@"+email2;
		try {
			InpictureMemberDAO.getInstance().registerInpictureMember(id, password, name, address, ssn, email);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return "redirect:front?command=LoginForm";
	}

}
