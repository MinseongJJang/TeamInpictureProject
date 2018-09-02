package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.inpicture.model.dao.InpictureMemberDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class MemberUpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  HttpSession session = request.getSession();
		  
		  InpictureMemberDTO imDTO = new InpictureMemberDTO();
		  InpictureMemberDTO im = (InpictureMemberDTO)session.getAttribute("mvo");
	      String password = request.getParameter("password");
	      String address = request.getParameter("address");
	      String email = request.getParameter("email");
	      imDTO.setPassword(password);
	      imDTO.setAddress(address);
	      imDTO.setEmail(email);
	      imDTO.setId(im.getId());
	      try {
	         InpictureMemberDAO.getInstance().memberUpdate(imDTO);
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      return "redirect:index.jsp";
	}

}
