package userInterface;

import mediator.Mediator;
import userEntities.User;

public class teamTechApp {
	public static void main(String[] args) {
		Mediator mediator = new Mediator();
		initializeApp();
		User currentUser  = UIUtil.loginScreen(mediator);
		while(true) {
			UIUtil.performCurrentUserTasks(mediator,currentUser);
		}
			
	}


	private static void initializeApp() {
		// TODO Auto-generated method stub
		
	}
}
