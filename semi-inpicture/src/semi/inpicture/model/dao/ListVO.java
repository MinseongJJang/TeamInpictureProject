package semi.inpicture.model.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.ArtistApplyBoardDTO;
import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.AuctionDTO;
import semi.inpicture.model.dto.BidderDTO;

public class ListVO {
	private PagingBean pb;
	private ArrayList<AuctionApplyDTO> auctionApplyList;
	private ArrayList<ArtistApplyBoardDTO> artistApplyList;
	private ArrayList<ArtDTO> artList;
	private LinkedHashMap<AuctionDTO, BidderDTO> mapList;
	
	public ListVO() {
		super();
	}
	
	public LinkedHashMap<AuctionDTO, BidderDTO> getMapList() {
		return mapList;
	}

	public void setMapList(LinkedHashMap<AuctionDTO, BidderDTO> mapList) {
		this.mapList = mapList;
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
	
}
