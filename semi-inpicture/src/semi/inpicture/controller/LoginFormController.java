package semi.inpicture.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "redirect:layout/login_form.jsp";
	}
	
}
