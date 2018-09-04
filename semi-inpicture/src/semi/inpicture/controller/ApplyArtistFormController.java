package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplyArtistFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/template/layout.jsp";
		request.setAttribute("url", "/artist/apply_artist_form.jsp");
		return url;
	}

}