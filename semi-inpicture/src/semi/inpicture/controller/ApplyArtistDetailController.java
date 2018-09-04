package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.ArtistApplyBoardDAO;
import semi.inpicture.model.dto.ArtistApplyBoardDTO;
import semi.inpicture.model.dto.ArtistAttachmentPathDTO;

public class ApplyArtistDetailController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		String artistApplyNo=request.getParameter("artistApplyNo");
		System.out.println(artistApplyNo);
		// 개별 게시물 조회  
		ArtistApplyBoardDTO dto=null;
		ArrayList<ArtistAttachmentPathDTO> list = new ArrayList<ArtistAttachmentPathDTO>();
		try {
			dto = ArtistApplyBoardDAO.getInstance().getPostingByNo(artistApplyNo);
			list = ArtistApplyBoardDAO.getInstance().applyArtisAttachmentList(artistApplyNo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		request.setAttribute("dto", dto);
		request.setAttribute("list", list);
		request.setAttribute("url", "/artist/apply_artist_detail.jsp");
		return "/template/layout.jsp";	
	}
}
