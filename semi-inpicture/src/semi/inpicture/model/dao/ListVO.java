package semi.inpicture.model.dao;

import java.util.ArrayList;

import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.ArtistApplyBoardDTO;
import semi.inpicture.model.dto.AuctionApplyDTO;

public class ListVO {
	private PagingBean pb;
	private ArrayList<AuctionApplyDTO> auctionApplyList;
	private ArrayList<ArtistApplyBoardDTO> artistApplyList;
	private ArrayList<ArtDTO> artList;
	
	public ListVO() {
		super();
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
