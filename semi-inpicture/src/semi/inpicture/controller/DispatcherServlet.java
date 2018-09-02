package semi.inpicture.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/front")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doDispatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
		String command = null;
		if(request.getContentType() != null && request.getContentType().toLowerCase().indexOf("multipart/form-data") > -1) {				
			//공통 부분을 어떻게 처리할까?
			/*
			 *  공통 부분을 처리하기 위하여 ApplyArtist , ApplyAuction , RegisterMyArt
			 *  로 command값이 요청된다면 해당 요청으로 넘어오는 form은 enc-type이 multipart이다.
			 *  따라서 보통의 요청인 request로 요청이 들어오지 않고 multipartrequest로 들어오게 된다.
			 *  현재의 mvc(model2) 패턴에서는 FrontController+Factory 패턴이 적용되어 각각의 요청을 
			 *  따로 처리할 수 없다. 그렇기에 enc-type이 multipart이면 모두 UpdateController로 이동시킨다.
			 *  -kms
			 */
			//String savePath = request.getServletContext().getRealPath("uploadImages");	
			command="Update";
		}else {//일반요청이면 
			command=request.getParameter("command");
	    }
		HandlerMapping handle = HandlerMapping.getInstance();
		Controller controller = handle.create(command);
		String url = controller.execute(request, response);
		if(url.startsWith("redirect:")) {
			response.sendRedirect(url.substring(9).trim());
		}else {
			request.getRequestDispatcher(url).forward(request, response);
		}
		}catch (Exception e) {
			e.printStackTrace();
			response.sendRedirect("/error.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doDispatch(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doDispatch(request, response);
	}

}
