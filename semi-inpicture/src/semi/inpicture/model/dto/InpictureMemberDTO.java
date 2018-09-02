package semi.inpicture.model.dto;

public class InpictureMemberDTO {
	private String id;
	private String password;
	private String name;
	private String address;
	private String ssn;
	private String email;
	private int point;
	private String memberType;
	
	public InpictureMemberDTO(String id, String password, String name, String address, String ssn, String email,
			int point, String memberType) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.address = address;
		this.ssn = ssn;
		this.email = email;
		this.point = point;
		this.memberType = memberType;
	}

	public InpictureMemberDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	@Override
	public String toString() {
		return "InpictureMemberDTO [id=" + id + ", password=" + password + ", name=" + name + ", address=" + address
				+ ", ssn=" + ssn + ", email=" + email + ", point=" + point + ", memberType=" + memberType + "]";
	}
	
}
