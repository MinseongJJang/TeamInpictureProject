package semi.inpicture.model.dao;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator{

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		
		return new PasswordAuthentication("shs2810@gmail.com", "Gustn28211");
	}
	
}
