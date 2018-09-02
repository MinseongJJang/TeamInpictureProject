package semi.inpicture.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.inpicture.model.dao.ArtDAO;
import semi.inpicture.model.dao.AuctionApplyDAO;
import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.AuctionApplyDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class UpdateController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 *  UpdateController는 파일이 업로드되는 요청을 모두 받아오는 Controller이다.
		 *  파일업로드 요청을 받았을 때 contextPath의 uploadImages로 파일을 업로드 시킨다.
		 *  그 후 MultiPartRequest의 getparameter command값으로 if , else if 조건문을 통해
		 *  요청에 따른 로직 수행을 하도록 한다.
		 */
		String url = "";
		int fileMaxSize = 10*1024*1024; // 파일 최대 사이즈 10MB로 지정
		String oriPath="C:\\Users\\kms\\git\\TeamInpictureProject\\semi-inpicture\\WebContent\\uploadImages\\";
		String afterPath ="C:\\Users\\kms\\git\\TeamInpictureProject\\semi-inpicture\\WebContent\\auction_apply_images\\";
		//String savePath = request.getServletContext().getRealPath("artist_upload");	
		MultipartRequest multi = new MultipartRequest(request, oriPath, fileMaxSize, "utf-8", new DefaultFileRenamePolicy());
		String command = multi.getParameter("command"); // hidden값으로 받아온 command값을 받아 조건문을 수행
		if(command.equals("ApplyArtist")) {
			
		}else if(command.equals("ApplyAuctionArt")) {
			
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
			
			/*
			 *  모든 파일을 uploadImages라는 folder에 저장시키기 때문에
			 *  command값별로 File을 이동시키는 부분
			 *  kms
			 */
			File file1 = new File(oriPath+auctionMainPic);
			File file2 = new File(afterPath+auctionMainPic);
			if(!file2.isDirectory()) {
				//해당 디렉토리가 존재하지 않는다면 생성
				new File(afterPath).mkdirs();
			}
			file1.renameTo(file2);
			
			
			//auction을 신청하기 위해서 session에 저장된 ID를 받아와 MEMBER를 반환받는다.
			HttpSession session = request.getSession(false);
			InpictureMemberDTO member = null;
			if(session != null) {
				//만약 session이 만료된 상태에서의 요청이라면 error.jsp로 이동하게 된다.
				
				member = (InpictureMemberDTO)session.getAttribute("mvo");
				AuctionApplyDTO dto = new AuctionApplyDTO();
				dto.setAuctionTitle(auctionTitle);
				dto.setAuctionContent(auctionContent);
				dto.setAuctionBeginTime(beginTime1+ " "+beginTime2);
				dto.setAuctionEndTime(endTime1 + " " + endTime2);
				dto.setAuctionMainPic(auctionMainPic);
				dto.setAuctionPromptlyPrice(auctionPromptlyPrice);
				dto.setAuctionBeginPrice(auctionBeginPrice);
				dto.setInpictureMemberDTO(member);
				AuctionApplyDAO dao = AuctionApplyDAO.getInstance();
				try {
					dao.appyAuction(dto);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				url = "/error.jsp";
			}
			url = "redirect:index.jsp";
		}else if(command.equals("RegisterMyArt")) {

			try {
				
				HttpSession session = request.getSession(false);
				// 로그인 회원 아이디 뽑아오려고 세션씀
				ArtDTO aDTO = new ArtDTO();
				InpictureMemberDTO imDTO = new InpictureMemberDTO();
				if(session != null) {
					InpictureMemberDTO mvo = (InpictureMemberDTO)session.getAttribute("mvo");
					// 작품등록할때 등록한 작품명,소개 등등
					aDTO.setArtTitle(multi.getParameter("artName"));
					aDTO.setArtContent(multi.getParameter("content"));
					String fileName = multi.getFilesystemName("picture");
					aDTO.setArtMainPic(fileName);
					imDTO.setId(mvo.getId());
					aDTO.setInpictureMemberDTO(imDTO);
					ArtDAO.getInstance().artRegister(aDTO);
				}else {
					url="error.jsp";
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			url = "redirect:index.jsp";
		}
		
		return url;
	}

}
