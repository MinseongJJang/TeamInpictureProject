package semi.inpicture.model.dto;

public class ArtistDTO {
	private InpictureMemberDTO inpictureMemberDTO;
	private String artistIntro;
	
	public ArtistDTO(InpictureMemberDTO inpictureMemberDTO, String artistIntro) {
		super();
		this.inpictureMemberDTO = inpictureMemberDTO;
		this.artistIntro = artistIntro;
	}

	public ArtistDTO() {
		super();
	}

	public InpictureMemberDTO getInpictureMemberDTO() {
		return inpictureMemberDTO;
	}

	public void setInpictureMemberDTO(InpictureMemberDTO inpictureMemberDTO) {
		this.inpictureMemberDTO = inpictureMemberDTO;
	}

	public String getArtistIntro() {
		return artistIntro;
	}

	public void setArtistIntro(String artistIntro) {
		this.artistIntro = artistIntro;
	}

	@Override
	public String toString() {
		return "ArtistDTO [inpictureMemberDTO=" + inpictureMemberDTO + ", artistIntro=" + artistIntro + "]";
	}
	
}
