package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PointOperatorController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int point = Integer.parseInt(request.getParameter("point"));
		int after = Integer.parseInt(request.getParameter("after"));
		int result = point  + after;
		System.out.println(result);
		request.setAttribute("responsebody", result);
		return "AjaxView";
	}

}
