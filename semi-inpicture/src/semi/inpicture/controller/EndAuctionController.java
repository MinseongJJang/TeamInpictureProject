package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.AuctionDAO;
import semi.inpicture.model.dto.AuctionDTO;

public class EndAuctionController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String auctionNo = request.getParameter("auctionNo");
		AuctionDAO dao = AuctionDAO.getInstance();
		String url = "";
		try {
			AuctionDTO auction = dao.getAuctionDetailInfo(auctionNo);
			System.out.println("경매종료될쯤 auction info"  + auction);
			request.setAttribute("auction", auction);
			url = "/auction/end_auction.jsp";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}

}
