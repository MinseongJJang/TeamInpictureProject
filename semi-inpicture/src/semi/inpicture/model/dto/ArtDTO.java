package semi.inpicture.model.dto;

public class ArtDTO {
	private String artNo;
	private String artTitle;
	private String artContent;
	private String artMainPic;
	private InpictureMemberDTO InpictureMemberDTO;

	public ArtDTO(String artNo, String artTitle, String artContent, String artMainPic,
			semi.inpicture.model.dto.InpictureMemberDTO inpictureMemberDTO) {
		super();
		this.artNo = artNo;
		this.artTitle = artTitle;
		this.artContent = artContent;
		this.artMainPic = artMainPic;
		InpictureMemberDTO = inpictureMemberDTO;
	}

	public ArtDTO(String artTitle, String artContent, String artMainPic,
			semi.inpicture.model.dto.InpictureMemberDTO inpictureMemberDTO) {
		super();
		this.artTitle = artTitle;
		this.artContent = artContent;
		this.artMainPic = artMainPic;
		this.InpictureMemberDTO = inpictureMemberDTO;
	}

	public ArtDTO() {
		super();
	}

	public String getArtNo() {
		return artNo;
	}

	public void setArtNo(String artNo) {
		this.artNo = artNo;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	public String getArtMainPic() {
		return artMainPic;
	}

	public void setArtMainPic(String artMainPic) {
		this.artMainPic = artMainPic;
	}

	public InpictureMemberDTO getInpictureMemberDTO() {
		return InpictureMemberDTO;
	}

	public void setInpictureMemberDTO(InpictureMemberDTO inpictureMemberDTO) {
		InpictureMemberDTO = inpictureMemberDTO;
	}

	@Override
	public String toString() {
		return "ArtDTO [artNo=" + artNo + ", artTitle=" + artTitle + ", artContent=" + artContent + ", artMainPic="
				+ artMainPic + ", InpictureMemberDTO=" + InpictureMemberDTO + "]";
	}
	
	
	
	
}
