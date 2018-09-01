package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.AuctionDAO;
import semi.inpicture.model.dto.AuctionDTO;
import semi.inpicture.model.dto.BidderDTO;

public class AuctionArtListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuctionDAO dao = AuctionDAO.getInstance();
		String url = "/template/layout";
		try {
			HashMap<AuctionDTO,BidderDTO> map =(HashMap<AuctionDTO, BidderDTO>) dao.getAuctionList();
			request.setAttribute("auctionList", map);
			request.setAttribute("url", "/auction/auction_list.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

}
