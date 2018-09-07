package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.inpicture.model.dto.InpictureMemberDTO;


public class ApplyArtistFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		InpictureMemberDTO dto = (InpictureMemberDTO)session.getAttribute("mvo");
		String url = null;
		if(dto==null) {
			url = "/member/need_login.jsp";
		}else if(dto.getMemberType().equals("3")) {
			url = "/member/apply_artist_overlap.jsp";
		}else if(dto.getMemberType().equals("2")) {
			url = "/member/apply_artist_overlap.jsp";
		}else {
			url = "/template/layout.jsp";
			request.setAttribute("url", "/artist/apply_artist_form.jsp");					
		}
		return url;
	}

}
