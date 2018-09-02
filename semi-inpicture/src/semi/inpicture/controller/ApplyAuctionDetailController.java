package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.AuctionApplyDAO;
import semi.inpicture.model.dto.AuctionApplyDTO;

public class ApplyAuctionDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String auctionNo = request.getParameter("auctionNo");
		String url = "";
		AuctionApplyDAO dao = AuctionApplyDAO.getInstance();
		try {
			AuctionApplyDTO auctionDTO = dao.getAuctionApplyDetailInfo(auctionNo);
			url = "/template/layout.jsp";
			request.setAttribute("url", "/auction/apply_auction_detail.jsp");
			request.setAttribute("auctionDTO", auctionDTO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

}
