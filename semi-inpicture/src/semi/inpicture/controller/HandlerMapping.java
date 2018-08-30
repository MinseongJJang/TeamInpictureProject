package semi.inpicture.controller;

public class HandlerMapping {
	private static HandlerMapping instance;
	
	private HandlerMapping() {
		
	}
	
	public static HandlerMapping getInstance() {
		if(instance==null)
			instance = new HandlerMapping();
		return instance;
	}
	
	public Controller create(String command) {
		Controller controller = null;
		if(command.equals("Login")) {
			controller=new LoginController();
		}else if(command.equals("Logout")){
			controller=new LogoutController();
		}else if(command.equals("LoginForm")){
			controller=new LoginFormController();
		}
		return controller;
	}
}
