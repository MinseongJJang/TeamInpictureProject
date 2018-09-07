package semi.inpicture.model.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.ArtistApplyBoardDTO;
import semi.inpicture.model.dto.ArtistDTO;
import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.AuctionDTO;
import semi.inpicture.model.dto.BidderDTO;
import semi.inpicture.model.dto.PointHistoryDTO;

public class ListVO {
	private PagingBean pb;
	private PagingMain pbMain;
	private PagingMainCarousel pbMainCarousel;
	private ArrayList<AuctionApplyDTO> auctionApplyList;
	private ArrayList<ArtistApplyBoardDTO> artistApplyList;
	private ArrayList<ArtDTO> artList;
	private ArrayList<ArtistDTO> artistList;
	private ArrayList<AuctionDTO> auctionList;
	private LinkedHashMap<AuctionDTO, BidderDTO> mapList;
	private ArrayList<PointHistoryDTO> pointList;
	
	public ArrayList<PointHistoryDTO> getPointList() {
		return pointList;
	}

	public void setPointList(ArrayList<PointHistoryDTO> pointList) {
		this.pointList = pointList;
	}

	public ArrayList<AuctionDTO> getAuctionList() {
		return auctionList;
	}

	public void setAuctionList(ArrayList<AuctionDTO> auctionList) {
		this.auctionList = auctionList;
	}

	public ListVO() {
		super();
	}

	public ListVO(PagingBean pb, ArrayList<AuctionApplyDTO> auctionApplyList,
			ArrayList<ArtistApplyBoardDTO> artistApplyList, ArrayList<ArtDTO> artList, ArrayList<ArtistDTO> artistList,
			LinkedHashMap<AuctionDTO, BidderDTO> mapList,PagingMain pbMain,PagingMainCarousel pbMainCarousel) {
		super();
		this.pb = pb;
		this.auctionApplyList = auctionApplyList;
		this.artistApplyList = artistApplyList;
		this.artList = artList;
		this.artistList = artistList;
		this.mapList = mapList;
		this.pbMain = pbMain;
		this.pbMainCarousel = pbMainCarousel;
	}
	
	public PagingMainCarousel getPbMainCarousel() {
		return pbMainCarousel;
	}

	public void setPbMainCarousel(PagingMainCarousel pbMainCarousel) {
		this.pbMainCarousel = pbMainCarousel;
	}

	public LinkedHashMap<AuctionDTO, BidderDTO> getMapList() {
		return mapList;
	}

	public void setMapList(LinkedHashMap<AuctionDTO, BidderDTO> mapList) {
		this.mapList = mapList;
	}

	public PagingMain getPbMain() {
		return pbMain;
	}

	public void setPbMain(PagingMain pbMain) {
		this.pbMain = pbMain;
	}

	public PagingBean getPb() {
		return pb;
	}

	public void setPb(PagingBean pb) {
		this.pb = pb;
	}

	public ArrayList<AuctionApplyDTO> getAuctionApplyList() {
		return auctionApplyList;
	}

	public void setAuctionApplyList(ArrayList<AuctionApplyDTO> auctionApplyList) {
		this.auctionApplyList = auctionApplyList;
	}

	public ArrayList<ArtistApplyBoardDTO> getArtistApplyList() {
		return artistApplyList;
	}

	public void setArtistApplyList(ArrayList<ArtistApplyBoardDTO> artistApplyList) {
		this.artistApplyList = artistApplyList;
	}

	public ArrayList<ArtDTO> getArtList() {
		return artList;
	}

	public void setArtList(ArrayList<ArtDTO> artList) {
		this.artList = artList;
	}
	public ArrayList<ArtistDTO> getArtistList() {
		return artistList;
	}
	public void setArtistList(ArrayList<ArtistDTO> artistList) {
		this.artistList = artistList;
	}

	@Override
	public String toString() {
		return "ListVO [pb=" + pb + ", pbMain=" + pbMain + ", pbMainCarousel=" + pbMainCarousel + ", auctionApplyList="
				+ auctionApplyList + ", artistApplyList=" + artistApplyList + ", artList=" + artList + ", artistList="
				+ artistList + ", auctionList=" + auctionList + ", mapList=" + mapList + ", pointList=" + pointList
				+ "]";
	}
	
	
}
