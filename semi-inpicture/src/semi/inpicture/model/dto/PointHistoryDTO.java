package semi.inpicture.model.dto;

public class PointHistoryDTO {
	private String pointHistoryNo;
	private String regDate;
	private InpictureMemberDTO memberDTO;
	private String paymentMethod;
	private int point;
	
	public PointHistoryDTO() {
		super();
	}

	public PointHistoryDTO(String regDate, InpictureMemberDTO memberDTO, String paymentMethod,int point) {
		super();
		this.regDate = regDate;
		this.memberDTO = memberDTO;
		this.paymentMethod = paymentMethod;
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPointHistoryNo() {
		return pointHistoryNo;
	}

	public void setPointHistoryNo(String pointHistoryNo) {
		this.pointHistoryNo = pointHistoryNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public InpictureMemberDTO getMemberDTO() {
		return memberDTO;
	}

	public void setMemberDTO(InpictureMemberDTO memberDTO) {
		this.memberDTO = memberDTO;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public String toString() {
		return "PointHistoryDTO [pointHistoryNo=" + pointHistoryNo + ", regDate=" + regDate + ", memberDTO=" + memberDTO
				+ ", paymentMethod=" + paymentMethod + "]";
	}

}
