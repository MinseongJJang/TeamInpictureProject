package semi.inpicture.model.dto;

public class ArtAtachmentPathDTO {
	private String artAttachmentNo;
	private String artAttachmentPath;
	private ArtDTO artDTO;
	
	public ArtAtachmentPathDTO(String artAttachmentPath, ArtDTO artDTO) {
		super();
		this.artAttachmentPath = artAttachmentPath;
		this.artDTO = artDTO;
	}

	public ArtAtachmentPathDTO() {
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
		return "ArtAtachmentPathDTO [artAttachmentNo=" + artAttachmentNo + ", artAttachmentPath=" + artAttachmentPath
				+ ", artDTO=" + artDTO + "]";
	}

}
