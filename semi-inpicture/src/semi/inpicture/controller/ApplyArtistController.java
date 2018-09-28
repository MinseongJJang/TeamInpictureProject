package semi.inpicture.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class ApplyArtistController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "";
		MultipartRequest multi = null;
		String workspacePath=System.getProperty("user.home")+"\\git\\TeamInpictureProject\\semi-inpicture\\WebContent\\uploadImages\\";
		int fileMaxSize = 10*1024*1024; // 파일 최대 사이즈 10MB로 지정
		//String savePath = request.getServletContext().getRealPath("artist_upload");	
		
		//DefaultFileRenamePolicy : 사용자의 업로드 파일 중 이름이 같다면 자동으로 바꾸어 주고, 오류가 발생하지않도록 파일 이름과 관련된 처리를 도와주는 클래스
		multi = new MultipartRequest(request, workspacePath, fileMaxSize, "utf-8", new DefaultFileRenamePolicy());
		String tempImageName = multi.getFilesystemName("aritst_main_pic");
		System.out.println(tempImageName + " 업로드된 이름");
		request.setAttribute("responsebody", tempImageName);
		url = "AjaxView";
		return url;
	}
}
