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
		
		return controller;
	}
}
