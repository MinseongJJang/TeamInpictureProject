package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.AuctionApplyDAO;

public class DeleteApplyAuctionController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String auctionNo = request.getParameter("auctionNo");
		String url = "";
		AuctionApplyDAO dao = AuctionApplyDAO.getInstance();
		try {
			dao.deleteAuctionApply(auctionNo);
			url = "redirect:index.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

}
