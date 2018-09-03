package semi.inpicture.model.dto;

public class AuctionApplyDTO {
	private String auctionNo;
	private String auctionTitle;
	private String auctionContent;
	private String auctionBeginTime;
	private String auctionEndTime;
	private String auctionMainPic;
	private int auctionPromptlyPrice;
	private int auctionBeginPrice;
	private InpictureMemberDTO inpictureMemberDTO;
	private String auctionState;
	
	public AuctionApplyDTO(String auctionTitle, String auctionContent, String auctionBeginTime, String auctionEndTime,
			String auctionMainPic, int auctionPromptlyPrice, int auctionBeginPrice,
			InpictureMemberDTO inpictureMemberDTO,String auctionState) {
		super();
		this.auctionTitle = auctionTitle;
		this.auctionContent = auctionContent;
		this.auctionBeginTime = auctionBeginTime;
		this.auctionEndTime = auctionEndTime;
		this.auctionMainPic = auctionMainPic;
		this.auctionPromptlyPrice = auctionPromptlyPrice;
		this.auctionBeginPrice = auctionBeginPrice;
		this.inpictureMemberDTO = inpictureMemberDTO;
		this.auctionState = auctionState;
	}

	public AuctionApplyDTO() {
		super();
	}
	
	
	public String getAuctionState() {
		return auctionState;
	}

	public void setAuctionState(String auctionState) {
		this.auctionState = auctionState;
	}

	public String getAuctionNo() {
		return auctionNo;
	}

	public void setAuctionNo(String auctionNo) {
		this.auctionNo = auctionNo;
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

	public String getAuctionMainPic() {
		return auctionMainPic;
	}

	public void setAuctionMainPic(String auctionMainPic) {
		this.auctionMainPic = auctionMainPic;
	}

	public int getAuctionPromptlyPrice() {
		return auctionPromptlyPrice;
	}

	public void setAuctionPromptlyPrice(int auctionPromptlyPrice) {
		this.auctionPromptlyPrice = auctionPromptlyPrice;
	}

	public int getAuctionBeginPrice() {
		return auctionBeginPrice;
	}

	public void setAuctionBeginPrice(int auctionBeginPrice) {
		this.auctionBeginPrice = auctionBeginPrice;
	}

	public InpictureMemberDTO getInpictureMemberDTO() {
		return inpictureMemberDTO;
	}

	public void setInpictureMemberDTO(InpictureMemberDTO inpictureMemberDTO) {
		this.inpictureMemberDTO = inpictureMemberDTO;
	}

	@Override
	public String toString() {
		return "AuctionApplyDTO [auctionNo=" + auctionNo + ", auctionTitle=" + auctionTitle + ", auctionContent="
				+ auctionContent + ", auctionBeginTime=" + auctionBeginTime + ", auctionEndTime=" + auctionEndTime
				+ ", auctionMainPic=" + auctionMainPic + ", auctionPromptlyPrice=" + auctionPromptlyPrice
				+ ", auctionBeginPrice=" + auctionBeginPrice + ", inpictureMemberDTO=" + inpictureMemberDTO + "]";
	}
	
	
}
