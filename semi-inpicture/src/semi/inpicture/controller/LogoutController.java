package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String url = "";
		HttpSession session = request.getSession(false);
		if (session != null)
			session.invalidate();
		url = "redirect:index.jsp";
		return url;
	}
}
