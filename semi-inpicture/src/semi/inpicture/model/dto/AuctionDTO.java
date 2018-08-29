package semi.inpicture.model.dto;

public class AuctionDTO {
	private AuctionApplyDTO auctionApplyDTO;
	private int auctionFinalBidPrice;
	private InpictureMemberDTO inpictureMemberDTO;
	private int auctionPromptlyPrice;
	private boolean auctionState;
	private String auctionFinalBidder;
	
	public AuctionDTO(AuctionApplyDTO auctionApplyDTO, int auctionFinalBidPrice, InpictureMemberDTO inpictureMemberDTO,
			int auctionPromptlyPrice, boolean auctionState, String auctionFinalBidder) {
		super();
		this.auctionApplyDTO = auctionApplyDTO;
		this.auctionFinalBidPrice = auctionFinalBidPrice;
		this.inpictureMemberDTO = inpictureMemberDTO;
		this.auctionPromptlyPrice = auctionPromptlyPrice;
		this.auctionState = auctionState;
		this.auctionFinalBidder = auctionFinalBidder;
	}

	public AuctionDTO() {
		super();
	}

	public AuctionApplyDTO getAuctionApplyDTO() {
		return auctionApplyDTO;
	}

	public void setAuctionApplyDTO(AuctionApplyDTO auctionApplyDTO) {
		this.auctionApplyDTO = auctionApplyDTO;
	}

	public int getAuctionFinalBidPrice() {
		return auctionFinalBidPrice;
	}

	public void setAuctionFinalBidPrice(int auctionFinalBidPrice) {
		this.auctionFinalBidPrice = auctionFinalBidPrice;
	}

	public InpictureMemberDTO getInpictureMemberDTO() {
		return inpictureMemberDTO;
	}

	public void setInpictureMemberDTO(InpictureMemberDTO inpictureMemberDTO) {
		this.inpictureMemberDTO = inpictureMemberDTO;
	}

	public int getAuctionPromptlyPrice() {
		return auctionPromptlyPrice;
	}

	public void setAuctionPromptlyPrice(int auctionPromptlyPrice) {
		this.auctionPromptlyPrice = auctionPromptlyPrice;
	}

	public boolean isAuctionState() {
		return auctionState;
	}

	public void setAuctionState(boolean auctionState) {
		this.auctionState = auctionState;
	}

	public String getAuctionFinalBidder() {
		return auctionFinalBidder;
	}

	public void setAuctionFinalBidder(String auctionFinalBidder) {
		this.auctionFinalBidder = auctionFinalBidder;
	}

	@Override
	public String toString() {
		return "AuctionDTO [auctionApplyDTO=" + auctionApplyDTO + ", auctionFinalBidPrice=" + auctionFinalBidPrice
				+ ", inpictureMemberDTO=" + inpictureMemberDTO + ", auctionPromptlyPrice=" + auctionPromptlyPrice
				+ ", auctionState=" + auctionState + ", auctionFinalBidder=" + auctionFinalBidder + "]";
	}
	
	
	
}
