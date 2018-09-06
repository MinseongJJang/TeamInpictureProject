package semi.inpicture.controller;

public class HandlerMapping {
	private static HandlerMapping instance;
	
	private HandlerMapping() {
		
	}
	
	public static HandlerMapping getInstance() {
		if(instance==null)
			instance = new HandlerMapping();
		return instance;
	}
	
	public Controller create(String command) {
		Controller controller = null;
		if(command.equals("Login")) {
			controller=new LoginController();
		}else if(command.equals("Logout")){
			controller=new LogoutController();
		}else if(command.equals("LoginForm")){
			controller=new LoginFormController();
		}else if(command.equals("RegisterMemberForm")) {
			controller=new RegisterMemberFormController();
			//회원 가입 폼으로 이동하는 controller
		}else if(command.equals("DuplicateMemberId")) {
			controller=new DuplicateMemberIdController();
			//회원 가입 시 ID중복확인하는 controller
		}else if(command.equals("RegisterMember")) {
			controller=new RegisterMemberController();
			//회원 가입 controller
		}else if(command.equals("ApplyArtistForm")) {
			controller=new ApplyArtistFormController();
			//작가 신청 폼으로 이동하는 controller
		}else if(command.equals("ApplyArtist")){
			controller = new ApplyArtistController();
		}else if(command.equals("ApplyArtistList")) {
			controller=new ApplyArtistListController();
			//작가 신청 목록을 보여주는 controller
		}else if(command.equals("ApplyArtistDetail")) {
			controller=new ApplyArtistDetailController();
			// 작가 신청글 상세보기
		}else if(command.equals("ChangeMemberType")) {
			controller=new ChangeMemberTypeController();
			//작가 신청 후 관리자가 멤버 타입을 변경시켜주는 controller
		}else if(command.equals("ArtistList")) {
			controller=new ArtistListController();
			//작가 목록을 불러오는 controller
		}else if(command.equals("DetailArtist")) {
			controller=new DetailArtistController();
			//작가 상세보기 controller
		}else if(command.equals("ArtList")) {
			controller=new ArtListController();
			//작가 목록을 불러오는 controller
		}else if(command.equals("DetailArt")) {
			controller=new DetailArtController();
			//작품의 상세정보를 불러오는 controller
		}else if(command.equals("RegisterMyArtForm")) {
			controller=new RegisterMyArtFormController();
			//작품을 등록하는 폼으로 이동하는 controller
		}else if(command.equals("RegisterMyArt")) {
			controller=new RegisterMyArtController();
			//작품을 등록하는 controller
		}else if(command.equals("ApplyAuctionArtForm")) {
			controller=new ApplyAuctionArtFormController();
			//경매 신청하는 폼 controller
		}else if(command.equals("ApplyAuctionArt")) {
			controller=new ApplyAuctionArtController();
			//경매 신청하는 controller
		}else if(command.equals("AuctionArtList")) {
			controller=new AuctionArtListController();
			//경매 목록을 불러오는 controller();
		}else if(command.equals("AuctionArtDetail")) {
			controller=new AuctionArtDetailController();
			//경매 상세정보를 불러오는 controller
		}else if(command.equals("BidList")) {
			controller=new BidListController();
			//입찰자 목록을 불러오는 controller
		}else if(command.equals("BidForm")) {
			controller=new BidFormController();
			//입찰을 하기위한 폼으로 이동하는 controller
		}else if(command.equals("Bid")) {
			controller=new BidController();
			//입찰 controller

		}else if(command.equals("GetAllInfoList")) {
			controller=new GetAllInfoListController();
			//server 시작시에 경매목록 + 작가목록 + 작품목록을 불러오는 controller
		}else if(command.equals("IdCheck")) {
			controller = new IdCheckController();
		}else if(command.equals("Update")) {
			controller = new UpdateController();
		}else if(command.equals("MemberUpdateForm")){
			controller = new MemberUpdateFormController();
		}else if(command.equals("MemberUpdate")){
			controller = new MemberUpdateController();
		}else if(command.equals("ApplyAuctionArtList")) {
			controller = new ApplyAuctionArtListController();
			//Auction을 신청한 list를 받아오는 controller
		}else if(command.equals("ApplyAuctionDetail")) {
			controller = new ApplyAuctionDetailController();
			//경매신청 상세페이지
		}else if(command.equals("DeleteApplyAuction")) {
			controller = new DeleteApplyAuctionController();
			//경매신청 취소
		}else if(command.equals("RegisterAuction")) {
			controller = new RegisterAuctionController();
			//경매승인
		}else if(command.equals("DeleteMyArt")) {
			controller = new DeleteMyArtController();
		}else if(command.equals("UpdateMyArtForm")) {
			controller = new UpdateMyArtFormController();
		}else if(command.equals("LoginCheck")) {
			controller = new LoginCheckController();
		}else if(command.equals("EndAuction")) {
			controller = new EndAuctionController();
		}else if(command.equals("FindIdForm")) {
			controller = new FindIdFormController();
		}else if(command.equals("FindId")) {
			controller = new FindIdController();
		}
		return controller;
	}
}
