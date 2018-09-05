package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllInfoListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("123");
		String gg = "";
		int age = 0;
		System.out.println(age + gg);
		String url = "/template/layout.jsp";
		request.setAttribute("url", "/template/main.jsp");
		return url;
	}

}
