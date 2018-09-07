package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.inpicture.model.dao.InpictureMemberDAO;
import semi.inpicture.model.dao.PointHistoryDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;
import semi.inpicture.model.dto.PointHistoryDTO;

public class MemberPointChargeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		int point = Integer.parseInt(request.getParameter("point"));
		String method = request.getParameter("method");
		System.out.println(method);
		String url = "";
		InpictureMemberDAO memberDAO = InpictureMemberDAO.getInstance();
		PointHistoryDAO pointDAO = PointHistoryDAO.getInstance();
		try {
			memberDAO.pointCharge(id, point);
			HttpSession session = request.getSession(false);
			if(session!=null) {
				InpictureMemberDTO member = memberDAO.myInfoById(id);
				PointHistoryDTO pointDTO = new PointHistoryDTO();
				pointDTO.setMemberDTO(member);
				pointDTO.setPaymentMethod(method);
				pointDTO.setPoint(point);
				pointDAO.registerPointHistory(pointDTO);
				url = "redirect:front?command=MemberPointChargeForm";
				session.setAttribute("mvo", member);
			}else {
				url = "/member/session_invalid.jsp";
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

}
