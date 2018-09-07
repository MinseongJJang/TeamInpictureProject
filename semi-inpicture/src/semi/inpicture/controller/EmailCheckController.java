package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.InpictureMemberDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class EmailCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id=request.getParameter("id");
		String email=request.getParameter("email");
		InpictureMemberDTO dto =null;
		try {
			dto=InpictureMemberDAO.getInstance().emailCheck(id,email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(dto.getPassword()!=null) {
			request.setAttribute("responsebody", "ok");
		}else
			request.setAttribute("responsebody", "fail");
		return "/AjaxView";
	}
}
