package semi.inpicture.model.dto;

public class AuctionDTO {
	private AuctionApplyDTO auctionApplyDTO;
	private String auctionTitle;
	private String auctionContent;
	private String auctionBeginTime;
	private String auctionEndTime;
	private int auctionFinalBidPrice;
	private InpictureMemberDTO inpictureMemberDTO;
	private int auctionPromptlyPrice;
	private boolean auctionState;
	private String auctionMainPic;
	private int auctionBeginPrice;
	private String auctionFinalBidder;
	
	public AuctionDTO(AuctionApplyDTO auctionApplyDTO, String auctionTitle, String auctionContent,
			String auctionBeginTime, String auctionEndTime, InpictureMemberDTO inpictureMemberDTO,
			int auctionPromptlyPrice, boolean auctionState, String auctionMainPic, int auctionBeginPrice) {
		super();
		this.auctionApplyDTO = auctionApplyDTO;
		this.auctionTitle = auctionTitle;
		this.auctionContent = auctionContent;
		this.auctionBeginTime = auctionBeginTime;
		this.auctionEndTime = auctionEndTime;
		this.inpictureMemberDTO = inpictureMemberDTO;
		this.auctionPromptlyPrice = auctionPromptlyPrice;
		this.auctionState = auctionState;
		this.auctionMainPic = auctionMainPic;
		this.auctionBeginPrice = auctionBeginPrice;
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

	public String getAuctionTitle() {
		return auctionTitle;
	}

	public void setAuctionTitle(String auctionTitle) {
		this.auctionTitle = auctionTitle;
	}

	public String getAuctionContent() {
		return auctionContent;
	}

	public void setAuctionContent(String auctionContent) {
		this.auctionContent = auctionContent;
	}

	public String getAuctionBeginTime() {
		return auctionBeginTime;
	}

	public void setAuctionBeginTime(String auctionBeginTime) {
		this.auctionBeginTime = auctionBeginTime;
	}

	public String getAuctionEndTime() {
		return auctionEndTime;
	}

	public void setAuctionEndTime(String auctionEndTime) {
		this.auctionEndTime = auctionEndTime;
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

	public String getAuctionMainPic() {
		return auctionMainPic;
	}

	public void setAuctionMainPic(String auctionMainPic) {
		this.auctionMainPic = auctionMainPic;
	}

	public int getAuctionBeginPrice() {
		return auctionBeginPrice;
	}

	public void setAuctionBeginPrice(int auctionBeginPrice) {
		this.auctionBeginPrice = auctionBeginPrice;
	}

	public String getAuctionFinalBidder() {
		return auctionFinalBidder;
	}

	public void setAuctionFinalBidder(String auctionFinalBidder) {
		this.auctionFinalBidder = auctionFinalBidder;
	}

	@Override
	public String toString() {
		return "AuctionDTO [auctionTitle=" + auctionTitle + ", auctionContent=" + auctionContent + ", auctionBeginTime="
				+ auctionBeginTime + ", auctionEndTime=" + auctionEndTime + ", auctionFinalBidPrice="
				+ auctionFinalBidPrice + ", inpictureMemberDTO=" + inpictureMemberDTO + ", auctionPromptlyPrice="
				+ auctionPromptlyPrice + ", auctionState=" + auctionState + ", auctionMainPic=" + auctionMainPic
				+ ", auctionBeginPrice=" + auctionBeginPrice + ", auctionFinalBidder=" + auctionFinalBidder + "]";
	}
	
}
