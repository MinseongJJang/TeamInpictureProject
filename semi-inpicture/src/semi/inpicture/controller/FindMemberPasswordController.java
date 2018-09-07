package semi.inpicture.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import semi.inpicture.model.dao.Gmail;
import semi.inpicture.model.dao.InpictureMemberDAO;

public class FindMemberPasswordController extends Authenticator implements Controller  {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String email = request.getParameter("email");
		
		char[] pass = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9'};
		
		StringBuffer password = new StringBuffer();
		Random rnd = new Random();
		for(int i=0; i<10; i++) {
			password.append(pass[rnd.nextInt(pass.length)]);
		}
		try {
			InpictureMemberDAO.getInstance().tempSetPassword(id, password.toString());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String host = "http://localhost:8888/semi-inpicture2-logic/";
		String from = "shs2810@gmail.com";
		String to = email;
		String subject = "임시 비밀번호 발급 이메일 입니다.";
		String content = "아래의 비밀번호로 로그인 하세요"+"\r"+password;
		System.out.println("이메일 보냈음");
		Properties p = new Properties();
		p.put("mail.smtp.user", from);
		p.put("mail.smtp.host", "smtp.gmail.com");
		p.put("mail.smtp.port", "465");
		p.put("mail.smtp.starttls.enable", true);
		p.put("mail.smtp.auth", true);
		p.put("mail.smtp.debug", true);
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		
		try {
			Authenticator auth = new Gmail(); 
			Session ses = Session.getInstance(p, auth);
			ses.setDebug(true);
			MimeMessage msg = new MimeMessage(ses);
			msg.setSubject(subject);
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr);
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr);
			msg.setContent(content, "text/html;charset=utf-8");
			Transport.send(msg);
			System.out.println("이메일 보냈음");
		} catch (Exception e) {
		}
		//String url = "/template/layout.jsp";
		//request.setAttribute("url","/member/find_member_password.jsp");
		return "redirect:front?command=LoginForm";
	}
}
