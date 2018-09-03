package semi.inpicture.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.inpicture.model.dao.ArtDAO;
import semi.inpicture.model.dao.ArtistApplyBoardDAO;
import semi.inpicture.model.dao.ArtistAttachmentPathDAO;
import semi.inpicture.model.dao.AuctionApplyDAO;
import semi.inpicture.model.dto.ArtDTO;
import semi.inpicture.model.dto.ArtistApplyBoardDTO;
import semi.inpicture.model.dto.ArtistAttachmentPathDTO;
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
		//String afterPath = "";
		String fileName = "";
		//String savePath = request.getServletContext().getRealPath(afterPath);	
		File oriFile = new File(oriPath) ;
		if(!oriFile.isDirectory()) {
			oriFile.mkdirs();
		}
		MultipartRequest multi = new MultipartRequest(request, oriPath, fileMaxSize, "utf-8", new DefaultFileRenamePolicy());
		String command = multi.getParameter("command"); // hidden값으로 받아온 command값을 받아 조건문을 수행
		
		if(command.equals("ApplyArtist")) {

			HttpSession session = request.getSession(false);
			InpictureMemberDTO idto = (InpictureMemberDTO) session.getAttribute("mvo");
			
			ArtistApplyBoardDTO dto = new ArtistApplyBoardDTO();
			dto.setArtistApplyTitle(multi.getParameter("title"));
			dto.setArtistApplyContent(multi.getParameter("content"));
			dto.setInpictureMemberDTO(idto);
			// 글번호 리턴
			int artist_post_no=0;
			try {
				artist_post_no = ArtistApplyBoardDAO.getInstance().applyArtist(dto);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			//사용자가 업로드한 파일 이름
			//실제로 서버에 업로드된 파일 시스템 이름
			//String fileRealName = multi.getFilesystemName("artist_attachment_file_0");
			//ArrayList<String> saveFiles = new ArrayList<String>();
			ArtistAttachmentPathDTO attachDto = null;
			@SuppressWarnings("unchecked")
			// 다중 파일을 받아옵니다.
			// 여러 개의 파일 데이터가 있을 때 파일을 하나씩 분석하기 위함
			
			Enumeration<String> fileNames = multi.getFileNames();
			// while문을 이용해 파일을 한개씩 반복적으로 실행
			while(fileNames.hasMoreElements()) {
				String formName = (String)fileNames.nextElement();
				//실제로 서버에 업로드된 파일 시스템 이름
				fileName = multi.getOriginalFileName(formName);
				System.out.println("fileName : "+fileName);
				if(fileName == null) {
					//fileName이 null일때 continue
					continue;
				}
				attachDto = new ArtistAttachmentPathDTO(fileName, artist_post_no);
				try {
					ArtistAttachmentPathDAO.getInstance().upload(attachDto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//saveFiles.add(fileName);
	/*			if(!fileOriName.endsWith(".doc") && !fileOriName.endsWith(".hwp") && !fileOriName.endsWith(".png") && !fileOriName.endsWith(".jpg") && !fileOriName.endsWith(".xls")) {
					String error = "파일을 업로드할 수 없습니다.";
					//request.setAttribute("error", error);
					File file = new File(workspacePath + fileName);
					file.delete();
				} else {
					saveFiles.add(fileName);
				}*/
			}
			/*for(int i=0; i<saveFiles.size(); i++) {
				System.out.println("size : "+saveFiles.size());
				System.out.println(i+": "+saveFiles.get(i));
				ArtistAttachmentPathDTO attachDto = new ArtistAttachmentPathDTO(saveFiles.get(i), artist_post_no);
				try {
					ArtistAttachmentPathDAO.getInstance().upload(attachDto);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}*/
			url = "redirect:index.jsp";
		}else if(command.equals("ApplyAuctionArt")) {
			
			//form에서 받아온 값들
			String auctionTitle = multi.getParameter("auctionTitle");
			String auctionContent = multi.getParameter("auctionContent");
			String beginTime1 = multi.getParameter("beginTime1");
			String beginTime2 = multi.getParameter("beginTime2");
			String endTime1 = multi.getParameter("endTime1");
			String endTime2 = multi.getParameter("endTime2");
			fileName = multi.getFilesystemName("auctionMainPic");
			int auctionPromptlyPrice = Integer.parseInt(multi.getParameter("promptlyPrice"));
			int auctionBeginPrice = Integer.parseInt(multi.getParameter("beginPrice"));

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
				dto.setAuctionMainPic(fileName);
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
					fileName = multi.getFilesystemName("picture");
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
		}else if(command.equals("UpdateMyArt")) {
			HttpSession session = request.getSession(false);
			if(session != null) {
				ArtDTO aDTO = new ArtDTO();
				aDTO.setArtTitle(multi.getParameter("artName"));
				aDTO.setArtContent(multi.getParameter("content"));
				aDTO.setArtNo(multi.getParameter("artNo"));
				fileName = multi.getFilesystemName("picture");
				aDTO.setArtMainPic(fileName);
				try {
					ArtDAO.getInstance().updateMyArt(aDTO);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				url="redirect:index.jsp";
			}else {
				url="error.jsp";
			}
		}
		
		
		/*
		 *  모든 파일을 uploadImages라는 folder에 저장시키기 때문에
		 *  command값별로 File을 이동시키는 부분
		 *  kms
		 */
		File file1 = new File(oriPath+fileName);
		File file2 = new File(afterPath+fileName);
		if(!file2.isDirectory()) {
			//해당 디렉토리가 존재하지 않는다면 생성
			new File(afterPath).mkdirs();
		}
		file1.renameTo(file2);
		
		
		
		
		return url;
	}

}
