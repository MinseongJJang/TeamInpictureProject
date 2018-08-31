package semi.inpicture.model.dto;

public class ArtistAttachmentPathDTO {
	private String artAttachmentNo;
	private String artAttachmentPath;
	private int artist_post_no;
	public ArtistAttachmentPathDTO() {
		super();
	}
	public ArtistAttachmentPathDTO(String artAttachmentPath, int artist_post_no) {
		super();
		this.artAttachmentPath = artAttachmentPath;
		this.artist_post_no = artist_post_no;
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
	public int getArtist_post_no() {
		return artist_post_no;
	}
	public void setArtist_post_no(int artist_post_no) {
		this.artist_post_no = artist_post_no;
	}
	@Override
	public String toString() {
		return "ArtistAttachmentPathDTO [artAttachmentNo=" + artAttachmentNo + ", artAttachmentPath="
				+ artAttachmentPath + ", artist_post_no=" + artist_post_no + "]";
	}
	
}
