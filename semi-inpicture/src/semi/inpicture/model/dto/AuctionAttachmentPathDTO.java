package semi.inpicture.model.dto;

public class AuctionAttachmentPathDTO {
	private String auctionAttachmentNo;
	private String auctionAttachmentPath;
	private AuctionApplyDTO auctionApply;
	
	public AuctionAttachmentPathDTO(String auctionAttachmentPath, AuctionApplyDTO auctionApply) {
		super();
		this.auctionAttachmentPath = auctionAttachmentPath;
		this.auctionApply = auctionApply;
	}

	public AuctionAttachmentPathDTO() {
		super();
	}

	public String getAuctionAttachmentNo() {
		return auctionAttachmentNo;
	}

	public void setAuctionAttachmentNo(String auctionAttachmentNo) {
		this.auctionAttachmentNo = auctionAttachmentNo;
	}

	public String getAuctionAttachmentPath() {
		return auctionAttachmentPath;
	}

	public void setAuctionAttachmentPath(String auctionAttachmentPath) {
		this.auctionAttachmentPath = auctionAttachmentPath;
	}

	public AuctionApplyDTO getAuctionApply() {
		return auctionApply;
	}

	public void setAuctionApply(AuctionApplyDTO auctionApply) {
		this.auctionApply = auctionApply;
	}

	@Override
	public String toString() {
		return "AuctionAttachmentPathDTO [auctionAttachmentNo=" + auctionAttachmentNo + ", auctionAttachmentPath="
				+ auctionAttachmentPath + ", auctionApply=" + auctionApply + "]";
	}
	
}
