package semi.inpicture.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtDAO;
import semi.inpicture.model.dao.ArtistDAO;
import semi.inpicture.model.dao.AuctionDAO;
import semi.inpicture.model.dao.ListVO;
import semi.inpicture.model.dao.PagingMain;
import semi.inpicture.model.dao.PagingMainCarousel;
import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.ArtistDTO;
import semi.inpicture.model.dto.AuctionDTO;

public class GetAllInfoListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		ArtDAO artDAO = ArtDAO.getInstance();
		ArtistDAO artistDAO = ArtistDAO.getInstance();
		PagingMain pbMain = null;
		PagingMainCarousel pbCarousel = null;
		ListVO listVO1 = new ListVO();
		ListVO listVO2 = new ListVO();
		ListVO listVO3 = new ListVO();
		String artNowPage = request.getParameter("artNowPage");
		String artistNowPage = request.getParameter("artistNowPage");
		String auctionNowPage = request.getParameter("artistNowPage");
		ArrayList<AuctionDTO> carouselList = null;
		try {
				int totalArt = artDAO.getTotalArt();
				if (artNowPage == null) {
					pbMain = new PagingMain(totalArt);
				} else {
					pbMain = new PagingMain(Integer.parseInt(artNowPage),totalArt);
				}
				ArrayList<ArtDTO> artList = artDAO.getArtOfArtist(pbMain);
				listVO1.setArtList(artList);
				listVO1.setPbMain(pbMain);

				int totalArtist = artistDAO.getTotalPostCount();
				if (artistNowPage == null) {
					pbMain = new PagingMain(totalArtist);
				}else {
					pbMain = new PagingMain(Integer.parseInt(artistNowPage),totalArtist);
				}
				ArrayList<ArtistDTO> artistList = artistDAO.getArtistList(pbMain);
				listVO2.setArtistList(artistList);
				listVO2.setPbMain(pbMain);

				int totalAuction1 = auctionDAO.getAuctionListCount();
				if (auctionNowPage == null) {
					pbMain = new PagingMain(totalAuction1);
				}else {
					pbMain = new PagingMain(Integer.parseInt(auctionNowPage), totalAuction1);
				}
				ArrayList<AuctionDTO> auctionList = auctionDAO.getMainAuctionList(pbMain);
				listVO3.setAuctionList(auctionList);
				listVO3.setPbMain(pbMain);
			int totalAuction2 = auctionDAO.getAuctionListCount();
			pbCarousel = new PagingMainCarousel(totalAuction2);
			carouselList = auctionDAO.getMainAuctionList(pbCarousel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String url = "/template/layout.jsp";
		request.setAttribute("carousel", carouselList);
		request.setAttribute("lvo1", listVO1);
		request.setAttribute("lvo2", listVO2);
		request.setAttribute("lvo3", listVO3);
		request.setAttribute("url", "/template/main.jsp");
		return url;
	}

}
