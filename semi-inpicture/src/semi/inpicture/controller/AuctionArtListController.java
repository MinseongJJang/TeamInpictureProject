package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.inpicture.model.dao.AuctionDAO;
import semi.inpicture.model.dao.ListVO;
import semi.inpicture.model.dao.PagingBean;
import semi.inpicture.model.dto.AuctionDTO;
import semi.inpicture.model.dto.BidderDTO;

public class AuctionArtListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuctionDAO dao = AuctionDAO.getInstance();
		String url = "/template/layout.jsp";
		int totalPostCount = 0;
		String nowPage = request.getParameter("nowPage");
		PagingBean pb = null;
		try {
			totalPostCount = dao.getAuctionListCount();
			
			if (nowPage == null) {
				 pb = new PagingBean(totalPostCount);
			} else {
				pb = new PagingBean(totalPostCount,Integer.parseInt(nowPage));
			}
			LinkedHashMap<AuctionDTO, BidderDTO> map = (LinkedHashMap<AuctionDTO, BidderDTO>) dao.getAuctionList(pb);
			ListVO lvo = new ListVO();
			lvo.setMapList(map);
			lvo.setPb(pb);
			request.setAttribute("lvo", lvo);
			request.setAttribute("url", "/auction/auction_list.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

}
