package semi.inpicture.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import semi.inpicture.model.dto.ArtistApplyBoardDTO;

public class ApplyArtistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MultipartRequest multi = null;
		int fileMaxSize = 10*1024*1024; // 파일 최대 사이즈 10MB로 지정
		String workspacePath="C:\\Users\\15zd\\git\\TeamInpictureProject\\semi-inpicture\\WebContent\\artist_upload\\";
		//String savePath = request.getServletContext().getRealPath("artist_upload");	
		multi = new MultipartRequest(request, workspacePath, fileMaxSize, "utf-8", new DefaultFileRenamePolicy());
		System.out.println(multi.getParameter("command"));
		
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		File file = multi.getFile("artist_attachment_file_0");

		ArtistApplyBoardDTO dto = new ArtistApplyBoardDTO();
		dto.setArtistApplyTitle(title);
		dto.setArtistApplyContent(content);
		
		
		return null;
	}
}
