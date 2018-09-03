package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.AuctionDAO;
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
		try {
			totalPostCount = dao.getAuctionListCount();
			PagingBean pb = new PagingBean(totalPostCount);
			if (nowPage != null) {
				pb.setNowPage(Integer.parseInt(request.getParameter("nowPage")));
			} else {
				if (pb.getNowPage() != 1) {
					int page = Integer.parseInt(request.getParameter("nowPage"));
					// nowPage가 1이 아니라면 view에서 nowPage를 가져온다
					pb = new PagingBean(totalPostCount, page);
				}
			}
			HashMap<AuctionDTO, BidderDTO> map = (HashMap<AuctionDTO, BidderDTO>) dao.getAuctionList(pb);
			request.setAttribute("auctionList", map);
			request.setAttribute("url", "/auction/auction_list.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

}
