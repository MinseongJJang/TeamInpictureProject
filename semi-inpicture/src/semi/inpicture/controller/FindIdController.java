package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.InpictureMemberDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class FindIdController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String ssn = request.getParameter("ssn");
		InpictureMemberDTO dto = null;
		try {
			dto = InpictureMemberDAO.getInstance().findId(name, ssn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("dto", dto);
		if (dto.getId() != null) {
			request.setAttribute("url", "/member/find_id_result.jsp");
			return "/template/layout.jsp";
		} else {
			return "/member/find_id_fail.jsp";
		}
	}
}
