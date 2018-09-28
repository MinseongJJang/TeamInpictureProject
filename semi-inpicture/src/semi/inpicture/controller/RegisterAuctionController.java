package semi.inpicture.controller;
import java.io.IOException;
import java.util.ArrayList;
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
}