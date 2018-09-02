package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.inpicture.model.dao.ArtistApplyBoardDAO;
import semi.inpicture.model.dao.ArtistAttachmentPathDAO;
import semi.inpicture.model.dto.ArtistApplyBoardDTO;
import semi.inpicture.model.dto.ArtistAttachmentPathDTO;
import semi.inpicture.model.dto.InpictureMemberDTO;

public class ApplyArtistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MultipartRequest multi = null;
		String workspacePath="C:\\java-kosta\\InPictureProject\\semi-inpicture-test\\WebContent\\artist_upload\\";
		int fileMaxSize = 10*1024*1024; // 파일 최대 사이즈 10MB로 지정
		//String savePath = request.getServletContext().getRealPath("artist_upload");	
		
		//DefaultFileRenamePolicy : 사용자의 업로드 파일 중 이름이 같다면 자동으로 바꾸어 주고, 오류가 발생하지않도록 파일 이름과 관련된 처리를 도와주는 클래스
		multi = new MultipartRequest(request, workspacePath, fileMaxSize, "utf-8", new DefaultFileRenamePolicy());
		
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
			String fileName = multi.getOriginalFileName(formName);
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
		//PostDetailNoHits&no=\"+pvo.getNo()
		return "redirect:index.jsp";
	}
}
