package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.AuctionDAO;
import semi.inpicture.model.dto.AuctionDTO;

public class AuctionArtDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String auctionNo = request.getParameter("auctionNo");
		String url = "";
		AuctionDAO dao = AuctionDAO.getInstance();
		try {
			AuctionDTO dto = dao.getAuctionDetailInfo(auctionNo);
			if(dto != null) {
				url = "/template/layout.jsp";
				request.setAttribute("url", "/auction/auction_detail.jsp");
				request.setAttribute("auctionDTO", dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}
}
