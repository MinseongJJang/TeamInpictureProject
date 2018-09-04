package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import semi.inpicture.model.dao.BidderDAO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class BidController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		InpictureMemberDTO member = (InpictureMemberDTO)session.getAttribute("mvo");
		String id = member.getId();
		String auctionNo = request.getParameter("auctionNo");
		System.out.println(auctionNo);
		System.out.println(id);
		int bidPrice = Integer.parseInt(request.getParameter("bidPrice"));
		System.out.println(bidPrice);
		int price = 0;
		BidderDAO dao = BidderDAO.getInstance();
		try {
			boolean flag = dao.bidIdCheck(id, auctionNo);
			System.out.println("flag : " + flag);
			if(!flag) {
				price = dao.registerBid(auctionNo, bidPrice, id);
				System.out.println("입찰하자마자 받아온 " + price);
			}else {
				price = dao.updateBid(auctionNo,bidPrice,id);
				System.out.println("두번째부터 받아온" + price);
			}
			request.setAttribute("responsebody", price);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "AjaxView";
	}

}
