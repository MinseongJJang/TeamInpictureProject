package semi.inpicture.model.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.AuctionDTO;
import semi.inpicture.model.dto.BidderDTO;

/**
 * 경매 건당 생성되는 AuctionWorker이다. AuctionWorker는 관리자의 승인 직후 바로 생성되며 현재시간과 사용자의
 * 요청시간을 비교하여 Thread를 동작시킨다.
 * 
 * @author kms
 */
public class AuctionWorker implements Runnable {
	private String beginTime;
	private String endTime;
	private String auctionNo;
	private AuctionApplyDTO applyDTO;
	private ArrayList<AuctionWorker> list;
	private boolean flag;

	public AuctionWorker() {
		super();
	}

	/*
	 * Controller에서 사용자의 beginTime,endTime,AuctionApplyDTO,auctionNo,List를 받아온다.
	 */
	public AuctionWorker(String beginTime, String endTime, AuctionApplyDTO applyDTO, String auctionNo,
			ArrayList<AuctionWorker> list) {
		this.beginTime = beginTime;
		this.endTime = endTime;
		this.applyDTO = applyDTO;
		this.auctionNo = auctionNo;
		this.list = list;
	}

	@Override
	public void run() {
		AuctionDAO auctionDAO = AuctionDAO.getInstance();
		BidderDAO bidDAO = BidderDAO.getInstance();
		InpictureMemberDAO memberDAO = InpictureMemberDAO.getInstance();
		AuctionApplyDAO applyDAO = AuctionApplyDAO.getInstance();
		SimpleDateFormat s = new SimpleDateFormat("YYYY-MM-dd HH:mm");
		SimpleDateFormat ss = new SimpleDateFormat("HH:mm");
		try {
			applyDAO.changeApplyAuctionState(auctionNo);
			// Date 타입의 연산을 위해 우선 현재시간과 사용자 요청 시간의 Format을 맞춰준다.
			

			// 초단위의 세밀한 계산까지는 필요하지 않으므로 시,분을 계산하기위해 시간과 초만 끊어낸다.
			Date d1 = ss.parse(beginTime.substring(11));
			Date d2 = ss.parse(s.format(new Date(System.currentTimeMillis())).substring(11));
			Date d3 = ss.parse(endTime.substring(11));
			if (d2.getTime() > d3.getTime()) {
				System.out.println("시간 지났어요~");
				flag = false;
			} else if (d1.getTime() < d2.getTime()) {
				// 만약 관리자의 경매승인시간이 사용자의 요청시간보다 지났다면 승인을 하는순간
				// 경매가 생성되며 바로 경매가 진행되게 된다.
				auctionDAO.registerAuction(applyDTO);
				flag = true;
			} else {
				// 요청시간보다 현재시간이 적은경우에는 요청시간 - 현재시간을 한 뒤
				// 남은시간만큼 Thread는 sleep하며 대기한다.
				long diff = d1.getTime() - d2.getTime();
				long diffTime = diff / (1000 * 60);
				Thread.sleep(diffTime * 60 * 1000);
				System.out.println("경매생성 후 슬립");
				auctionDAO.registerAuction(applyDTO);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (flag) {
			try {
				// 위와 마찬가지로 사용자로부터 종료시간을 받아 현재시간과 format을 맞춰준다
				// 이 finally block을 들어왔다는 이야기는 이미 Thread가 Sleep에서 깨어나 동작한다는 뜻이다.
				// 하지만 사용자가 종료시간을 요청하였기 때문에 해당시간까지는 Thread가 종료되어서는 안된다.
				Date d1 = ss.parse(endTime.substring(11));
				Date d2 = ss.parse(s.format(new Date(System.currentTimeMillis())).substring(11));
				long diff = d1.getTime() - d2.getTime();
				long diffTime = diff / (1000 * 60);
				// 사용자가 요청한 종료시간 - 현재시간을 하여 남은시간만큼 Thread는 sleep하며 대기한다.
				// ex) 사용자가 요청한 종료시간 : 오전10시
				// 현재시간 : 오전 9시
				// 약 1시간을 Thread는 sleep하며 대기하게된다.
				Thread.sleep((diffTime * 60 * 1000));
				System.out.println("슬립 빠져나온 후 경매종료 로직");
				// Thread가 sleep상태에서 빠져나오면 경매종료시간이 되었다는 의미이다.
				// Thread가 sleep상태에서 빠져나오고 해당Auction의 최고 입찰자를 낙찰자로 저장하고
				// 그 낙찰자의 입찰금액을 낙찰금액으로 선정한다.
				// 또 낙찰자의 point는 차감되며 경매를 등록한 판매자는 point가 증가된다.
				BidderDTO bidder = bidDAO.getFinalBidder(auctionNo);
				System.out.println("쓰레드 종료전 bidder : " + bidder);
				if (bidder.getInpictureMemberDTO() == null) {
					// bidder가 null이라는 의미는 해당경매의 입찰자가 단 한명도 존재하지 않는다는 의미이다.
					// 따라서 bidder의 값이 null이들어온다면 아무런 작업도 수행하지 않고 Thread를 종료시킨다.
					System.out.println("입찰자없어서 종료");
					// 경매 종료 후 경매가 종료되었다고 알려주는 page로 이동하기 위함
					auctionDAO.endAuction(auctionNo);
					list.remove(this);
				} else {
					String name = memberDAO.getMemberName(bidder.getInpictureMemberDTO().getId());
					bidder.getInpictureMemberDTO().setName(name);
					int price = bidder.getAuctionBidPrice();
					auctionDAO.endAuction(bidder);
					memberDAO.updateMemberMinusPoint(bidder.getInpictureMemberDTO().getId(), price);
					AuctionDTO auctionDTO = auctionDAO.getAuctionDetailInfo(auctionNo);
					memberDAO.updateMemberPlusPoint(auctionDTO.getAuctionApplyDTO().getInpictureMemberDTO().getId(),
							price);
					// 경매 종료전 모든작업을 맞췄다면 Thread는 list에서 제거하며 그 동작을 멈추게 된다.
					System.out.println("종료");
					// 경매 종료 후 경매가 종료되었다고 알려주는 page로 이동하기 위함
					list.remove(this);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("시간지난거 승인해서 아무것도 안하고 list에서 쓰레드만 지울거에요");
			list.remove(this);
		}
	}

}
