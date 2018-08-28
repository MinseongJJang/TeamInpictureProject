package semi.inpicture.model.dto;

public class ArtistApplyBoardDTO {
	private String artistPostNo;
	private InpictureMemberDTO inpictureMemberDTO;
	private String artistApplyTitle;
	private String artistApplyContent;
	private String regdate;
	private String artistMainPic;
	
	public ArtistApplyBoardDTO(InpictureMemberDTO inpictureMemberDTO, String artistApplyTitle,
			String artistApplyContent, String regdate, String artistMainPic) {
		super();
		this.inpictureMemberDTO = inpictureMemberDTO;
		this.artistApplyTitle = artistApplyTitle;
		this.artistApplyContent = artistApplyContent;
		this.regdate = regdate;
		this.artistMainPic = artistMainPic;
	}

	public ArtistApplyBoardDTO() {
		super();
	}

	public String getArtistPostNo() {
		return artistPostNo;
	}

	public void setArtistPostNo(String artistPostNo) {
		this.artistPostNo = artistPostNo;
	}

	public InpictureMemberDTO getInpictureMemberDTO() {
		return inpictureMemberDTO;
	}

	public void setInpictureMemberDTO(InpictureMemberDTO inpictureMemberDTO) {
		this.inpictureMemberDTO = inpictureMemberDTO;
	}

	public String getArtistApplyTitle() {
		return artistApplyTitle;
	}

	public void setArtistApplyTitle(String artistApplyTitle) {
		this.artistApplyTitle = artistApplyTitle;
	}

	public String getArtistApplyContent() {
		return artistApplyContent;
	}

	public void setArtistApplyContent(String artistApplyContent) {
		this.artistApplyContent = artistApplyContent;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getArtistMainPic() {
		return artistMainPic;
	}

	public void setArtistMainPic(String artistMainPic) {
		this.artistMainPic = artistMainPic;
	}

	@Override
	public String toString() {
		return "ArtistApplyBoardDTO [artistPostNo=" + artistPostNo + ", inpictureMemberDTO=" + inpictureMemberDTO
				+ ", artistApplyTitle=" + artistApplyTitle + ", artistApplyContent=" + artistApplyContent + ", regdate="
				+ regdate + ", artistMainPic=" + artistMainPic + "]";
	}
	
}
