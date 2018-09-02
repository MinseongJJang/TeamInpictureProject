package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.InpictureMemberDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class MemberUpdateFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
	      InpictureMemberDTO imDTO = null;
	      try {
	         imDTO = InpictureMemberDAO.getInstance().myInfoById(id);
	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();
	      }
	      request.setAttribute("im",imDTO);
	      request.setAttribute("url","/template/member_update.jsp");
	      return "/template/layout.jsp";
	}

}
