package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.AuctionApplyDAO;
import semi.inpicture.model.dao.AuctionDAO;
import semi.inpicture.model.dto.AuctionApplyDTO;

public class RegisterAuctionController implements Controller {
	private List<AuctionWorker> list ;
	String beginTime ;
	String endTime;
	String auctionNo;
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String auctionNo = request.getParameter("auctionNo");
		String url = "";
		AuctionApplyDAO applyDAO = AuctionApplyDAO.getInstance();
		
		try {
			AuctionApplyDTO applyDTO = applyDAO.getAuctionApplyDetailInfo(auctionNo);
			beginTime = applyDTO.getAuctionBeginTime();
			endTime = applyDTO.getAuctionEndTime();
			
			if(list==null || list.isEmpty()) {
				System.out.println("첫요청 ");
				list = Collections.synchronizedList(new ArrayList<AuctionWorker>());
			}
			//관리자가 승인을 했다. -> Thread 생성한다.
			AuctionWorker worker = new AuctionWorker(request,response,applyDTO);
			Thread thread = new Thread(worker);
			thread.setDaemon(true);
			list.add(worker);
			thread.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

	class AuctionWorker implements Runnable {
		HttpServletRequest request ; 
		HttpServletResponse response ;
		AuctionApplyDTO applyDTO;
		int result ;
		AuctionWorker(HttpServletRequest request, HttpServletResponse response,AuctionApplyDTO applyDTO){
			this.request = request;
			this.response = response;
			this.applyDTO = applyDTO;
		}
		
		public void work() {
			AuctionDAO auctionDAO = AuctionDAO.getInstance();
			AuctionApplyDAO applyDAO = AuctionApplyDAO.getInstance();
			try {
				if(result == 0) {
					System.out.println("처음 들어왔을 때");
					result = auctionDAO.registerAuction(applyDTO);
					System.out.println(result);
					applyDAO.changeApplyAuctionState(auctionNo);
				}else {
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			boolean flag = true;
			while (flag) {
				try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				long now = System.currentTimeMillis();
				SimpleDateFormat dayTime = new SimpleDateFormat("YYYY-MM-dd HH:mm");
				String nowTime = String.valueOf(dayTime.format(now));
				System.out.println("계속해서 받아오는 현재시간 : " +  nowTime);
				if(beginTime.equals(nowTime)) {
					work();
				}else if(endTime.equals(nowTime)) {
					System.out.println("thread끝난당");
					flag = false;
				}else {
					work();
				}
			}

		}

	}
}
