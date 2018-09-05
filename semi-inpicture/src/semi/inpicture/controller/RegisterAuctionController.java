package semi.inpicture.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import semi.inpicture.model.dao.AuctionApplyDAO;
import semi.inpicture.model.dao.AuctionWorker;
import semi.inpicture.model.dto.AuctionApplyDTO;

public class RegisterAuctionController implements Controller {
	private ArrayList<AuctionWorker> list ;
	String beginTime ;
	String endTime;
	String auctionNo;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		auctionNo = request.getParameter("auctionNo");
		String url = "";
		AuctionApplyDAO applyDAO = AuctionApplyDAO.getInstance();
		
		try {
			AuctionApplyDTO applyDTO = applyDAO.getAuctionApplyDetailInfo(auctionNo);
			beginTime = applyDTO.getAuctionBeginTime();
			endTime = applyDTO.getAuctionEndTime();
			if(list==null || list.isEmpty()) {
				list = new ArrayList<AuctionWorker>();
			}
			//관리자가 승인을 했다. -> Thread 생성한다.
			AuctionWorker worker = new AuctionWorker(beginTime, endTime, applyDTO, auctionNo,list);
			Thread thread = new Thread(worker);
			thread.setDaemon(true);
			list.add(worker);
			thread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	/*class AuctionWorker implements Runnable {
		HttpServletRequest request ; 
		HttpServletResponse response ;
		AuctionApplyDTO applyDTO;
		AuctionWorker(HttpServletRequest request, HttpServletResponse response,AuctionApplyDTO applyDTO){
			this.request = request;
			this.response = response;
			this.applyDTO = applyDTO;
		}
		
	
		
		@Override
		public void run() {
	
			SimpleDateFormat s = new SimpleDateFormat("YYYY-MM-dd HH:mm");
			SimpleDateFormat ss = new SimpleDateFormat("HH:mm");
			AuctionDAO auctionDAO = AuctionDAO.getInstance();
			BidderDAO bidDAO = BidderDAO.getInstance();
			InpictureMemberDAO memberDAO = InpictureMemberDAO.getInstance();
			AuctionApplyDAO applyDAO = AuctionApplyDAO.getInstance();
			try {
				Date d1 = ss.parse(beginTime.substring(11));
				Date d2 = ss.parse(s.format(new Date(System.currentTimeMillis())).substring(11));
				if(d1.getTime()<d2.getTime()) {
					auctionDAO.registerAuction(applyDTO);
				}else {
					long diff = d1.getTime() - d2.getTime();
					long diffTime = diff/(1000*60);
					Thread.sleep(diffTime*60*1000);
					System.out.println("Thread 슬립종료");
					auctionDAO.registerAuction(applyDTO);
					applyDAO.changeApplyAuctionState(auctionNo);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				try {
					Date d1 = ss.parse(endTime.substring(11));
					System.out.println(d1);
					Date d2 = ss.parse(s.format(new Date(System.currentTimeMillis())).substring(11));
					System.out.println(d2);
					long diff = d1.getTime() - d2.getTime();
					long diffTime = diff/(1000*60);
					System.out.println(diffTime);
					Thread.sleep((diffTime*60*1000));
					BidderDTO bidder = bidDAO.getFinalBidder(auctionNo);
					System.out.println("마지막에 bidder 받아오자"+bidder);
					String name = memberDAO.getMemberName(bidder.getInpictureMemberDTO().getId());
					bidder.getInpictureMemberDTO().setName(name);
					auctionDAO.endAuction(bidder);
					int price = bidder.getAuctionBidPrice();
					memberDAO.updateMemberMinusPoint(bidder.getInpictureMemberDTO().getId(), price);
					System.out.println("낙찰자 포인트 차감");
					AuctionDTO auctionDTO = auctionDAO.getAuctionDetailInfo(auctionNo);
					memberDAO.updateMemberPlusPoint(auctionDTO.getAuctionApplyDTO().getInpictureMemberDTO().getId(),price);
					System.out.println("판매자 포인트 증가");
					list.remove(this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			

		}

	}*/
}