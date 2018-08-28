package semi.inpicture.model.dto;

public class ArtistAttachmentPathDTO {
	private String artAttachmentNo;
	private String artAttachmentPath;
	private ArtDTO artDTO;
	
	public ArtistAttachmentPathDTO(String artAttachmentPath, ArtDTO artDTO) {
		super();
		this.artAttachmentPath = artAttachmentPath;
		this.artDTO = artDTO;
	}

	public ArtistAttachmentPathDTO() {
		super();
	}

	public String getArtAttachmentNo() {
		return artAttachmentNo;
	}

	public void setArtAttachmentNo(String artAttachmentNo) {
		this.artAttachmentNo = artAttachmentNo;
	}

	public String getArtAttachmentPath() {
		return artAttachmentPath;
	}

	public void setArtAttachmentPath(String artAttachmentPath) {
		this.artAttachmentPath = artAttachmentPath;
	}

	public ArtDTO getArtDTO() {
		return artDTO;
	}

	public void setArtDTO(ArtDTO artDTO) {
		this.artDTO = artDTO;
	}

	@Override
	public String toString() {
		return "ArtistAttachmentPathDTO [artAttachmentNo=" + artAttachmentNo + ", artAttachmentPath="
				+ artAttachmentPath + ", artDTO=" + artDTO + "]";
	}
	
}
