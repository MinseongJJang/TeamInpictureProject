package semi.inpicture.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.inpicture.model.dao.ListVO;
import semi.inpicture.model.dao.PagingBean;
import semi.inpicture.model.dao.PointHistoryDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;
import semi.inpicture.model.dto.PointHistoryDTO;

public class MemberPointChargeFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		String url = "";
		String page = request.getParameter("nowPage");
		ListVO lvo = null; 
		int nowPage = 0;
		if(page != null) {
			 nowPage = Integer.parseInt(page);
		}
		ArrayList<PointHistoryDTO> list = null;
		PagingBean pb = null;
		int count = 0;
		try {
			if (session != null) {
				InpictureMemberDTO member = (InpictureMemberDTO) session.getAttribute("mvo");
				if(member != null) {
					count = PointHistoryDAO.getInstance().totalHistoryCount(member.getId());
				}//if(nowPage == null || nowPage.equals("")) {
				if(page == null) {
					pb = new PagingBean(count);
				}else {
					pb = new PagingBean(count, nowPage);
				}
				list = PointHistoryDAO.getInstance().pointHistoryList(member.getId(),pb);
				lvo = new ListVO();
				lvo.setPointList(list);
				lvo.setPb(pb);
				url = "/template/layout.jsp";
			} else {
				url = "/member/session_invalid.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("lvo", lvo);
		request.setAttribute("url", "/member/point_charge.jsp");
		return url;
	}

}
