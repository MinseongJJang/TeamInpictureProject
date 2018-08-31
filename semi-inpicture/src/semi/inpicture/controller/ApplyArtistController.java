package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.inpicture.model.dao.ArtistApplyBoardDAO;
import semi.inpicture.model.dao.ArtistAttachmentPathDAO;
import semi.inpicture.model.dto.ArtistApplyBoardDTO;
import semi.inpicture.model.dto.ArtistAttachmentPathDTO;

public class ApplyArtistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multi = null;
		int fileMaxSize = 10*1024*1024; // 파일 최대 사이즈 10MB로 지정
		
		String workspacePath="C:\\Users\\15zd\\git\\TeamInpictureProject\\semi-inpicture\\WebContent\\artist_upload\\";
		//String savePath = request.getServletContext().getRealPath("artist_upload");	
		multi = new MultipartRequest(request, workspacePath, fileMaxSize, "utf-8", new DefaultFileRenamePolicy());
		
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");

		ArtistApplyBoardDTO dto = new ArtistApplyBoardDTO();
		dto.setArtistApplyTitle(title);
		dto.setArtistApplyContent(content);
		
		// 글번호 리턴
		int artist_post_no=0;
		try {
			artist_post_no = ArtistApplyBoardDAO.getInstance().applyArtist(dto);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String formName="";
		String fileName="";
		ArrayList<String> saveFiles = new ArrayList<String>();
		
		@SuppressWarnings("unchecked")
		// 다중 파일을 받아옵니다.
		Enumeration<String> files = multi.getFileNames();
		
		while(files.hasMoreElements()) {
			formName = (String)files.nextElement();
			// getFilesystemName()으로 파일 이름을 받아올 수 있다.
			fileName = multi.getFilesystemName(formName);
			saveFiles.add(fileName);
			ArtistAttachmentPathDTO attachDto = new ArtistAttachmentPathDTO(fileName, artist_post_no);
			try {
				ArtistAttachmentPathDAO.getInstance().upload(attachDto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i=0; i<saveFiles.size(); i++) {
			System.out.println("size : "+saveFiles.size());
			System.out.println(i+": "+saveFiles.get(i));
			ArtistAttachmentPathDTO attachDto = new ArtistAttachmentPathDTO(saveFiles.get(i), artist_post_no);
			try {
				ArtistAttachmentPathDAO.getInstance().upload(attachDto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return "redirect:artist/apply_artist_list.jsp";
	}
}
