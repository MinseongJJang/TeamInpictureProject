package semi.inpicture.model.dto;

public class BidderDTO {
	private InpictureMemberDTO inpictureMemberDTO;
	private AuctionDTO auctionDTO;
	private int auctionBidPrice;
	
	public BidderDTO(InpictureMemberDTO inpictureMemberDTO, AuctionDTO auctionDTO, int auctionBidPrice) {
		super();
		this.inpictureMemberDTO = inpictureMemberDTO;
		this.auctionDTO = auctionDTO;
		this.auctionBidPrice = auctionBidPrice;
	}

	public BidderDTO() {
		super();
	}

	public InpictureMemberDTO getInpictureMemberDTO() {
		return inpictureMemberDTO;
	}

	public void setInpictureMemberDTO(InpictureMemberDTO inpictureMemberDTO) {
		this.inpictureMemberDTO = inpictureMemberDTO;
	}

	public AuctionDTO getAuctionDTO() {
		return auctionDTO;
	}

	public void setAuctionDTO(AuctionDTO auctionDTO) {
		this.auctionDTO = auctionDTO;
	}

	public int getAuctionBidPrice() {
		return auctionBidPrice;
	}

	public void setAuctionBidPrice(int auctionBidPrice) {
		this.auctionBidPrice = auctionBidPrice;
	}

	@Override
	public String toString() {
		return "BidderDTO [inpictureMemberDTO=" + inpictureMemberDTO + ", auctionDTO=" + auctionDTO
				+ ", auctionBidPrice=" + auctionBidPrice + "]";
	}
	
	
	
}
