package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFormController implements Controller{
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String url = "/template/layout.jsp";
		request.setAttribute("url", "/member/login_form.jsp");
		System.out.println("로그인?");
		return url;
	}
	
}
