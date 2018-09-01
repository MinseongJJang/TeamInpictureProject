package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class ApplyAuctionArtController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//file upload를 하기 위해 필요한 multipartrequest
		String url = "";
		MultipartRequest multi = null;
		int fileMaxSize = 10*1024*1024; // 파일 최대 사이즈 10MB로 지정
		String workspacePath="C:\\Users\\kms\\git\\TeamInpictureProject\\semi-inpicture\\WebContent\\uploadImages\\";
		//String savePath = request.getServletContext().getRealPath("artist_upload");	
		multi = new MultipartRequest(request, workspacePath, fileMaxSize, "utf-8", new DefaultFileRenamePolicy());
		
		//form에서 받아온 값들
		String auctionTitle = multi.getParameter("auctionTitle");
		String auctionContent = multi.getParameter("auctionContent");
		String beginTime1 = multi.getParameter("beginTime1");
		String beginTime2 = multi.getParameter("beginTime2");
		String endTime1 = multi.getParameter("endTime1");
		String endTime2 = multi.getParameter("endTime2");
		String auctionMainPic = multi.getFilesystemName("auctionMainPic");
		int auctionPromptlyPrice = Integer.parseInt(multi.getParameter("promptlyPrice"));
		int auctionBeginPrice = Integer.parseInt(multi.getParameter("beginPrice"));
		
		//auction을 신청하기 위해서 session에 저장된 ID를 받아와 MEMBER를 반환받는다.
		HttpSession session = request.getSession(false);
		InpictureMemberDTO member = null;
		if(session != null) {
			member = (InpictureMemberDTO)session.getAttribute("mvo");
		}
		AuctionApplyDTO dto = new AuctionApplyDTO();
		dto.setAuctionTitle(auctionTitle);
		dto.setAuctionContent(auctionContent);
		dto.setAuctionBeginTime(beginTime1+ " "+beginTime2);
		dto.setAuctionEndTime(endTime1 + " " + endTime2);
		dto.setAuctionMainPic(auctionMainPic);
		dto.setAuctionPromptlyPrice(auctionPromptlyPrice);
		dto.setAuctionBeginPrice(auctionBeginPrice);
		dto.setInpictureMemberDTO(member);
		url = "/template/auction_list.jsp";
		request.setAttribute("url", "/front?command=AuctionArtList");
		return url;
	}

}
