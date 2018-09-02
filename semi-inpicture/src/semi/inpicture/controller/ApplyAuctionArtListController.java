package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.AuctionApplyDAO;
import semi.inpicture.model.dao.ListVO;
import semi.inpicture.model.dao.PagingBean;
import semi.inpicture.model.dto.AuctionApplyDTO;

public class ApplyAuctionArtListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuctionApplyDAO auctionDAO =AuctionApplyDAO.getInstance();
		ArrayList<AuctionApplyDTO> auctionList;
		String url = "";
		int totalPostCount = 0;
		String nowPage = request.getParameter("nowPage");
		try {
			totalPostCount = auctionDAO.getAuctionApplyListCount();
			PagingBean pb = new PagingBean(totalPostCount); 
			if(nowPage != null) {
				pb.setNowPage(Integer.parseInt(nowPage));
			}
			auctionList = auctionDAO.getAllAuctionApplyList(pb);
			ListVO lvo = new ListVO();
			lvo.setAuctionApplyList(auctionList);
			lvo.setPb(pb);
			request.setAttribute("lvo", lvo);
			request.setAttribute("url", "/auction/apply_auction_list.jsp");
			url ="/template/layout.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return url;
	}

}
