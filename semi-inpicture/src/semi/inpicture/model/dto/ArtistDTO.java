package semi.inpicture.model.dto;

public class ArtistDTO {
	private InpictureMemberDTO inpictureMemberDTO;
	private String artistIntro;
	private String artist_main_pic;
	public ArtistDTO() {
		super();
	}
	public ArtistDTO(InpictureMemberDTO inpictureMemberDTO, String artistIntro, String artist_main_pic) {
		super();
		this.inpictureMemberDTO = inpictureMemberDTO;
		this.artistIntro = artistIntro;
		this.artist_main_pic = artist_main_pic;
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
	public String getArtist_main_pic() {
		return artist_main_pic;
	}
	public void setArtist_main_pic(String artist_main_pic) {
		this.artist_main_pic = artist_main_pic;
	}
	@Override
	public String toString() {
		return "ArtistDTO [inpictureMemberDTO=" + inpictureMemberDTO + ", artistIntro=" + artistIntro
				+ ", artist_main_pic=" + artist_main_pic + "]";
	}

	
}
