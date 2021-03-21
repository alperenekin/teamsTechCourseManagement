package userInterface;

import java.util.Scanner;

import mediator.Mediator;
import userEntities.User;

public class UIUtil {

	public static User loginScreen(Mediator mediator) {
		printLoginScreen();
		return login(mediator);
	}

	private static User login(Mediator mediator) {
		Scanner scanner= new Scanner(System.in);    //System.in is a standard input stream  
		System.out.print("Username(email):  ");  
		String userName = scanner.nextLine();  
		System.out.print("Passwd:  ");
		String passwd = scanner.nextLine();
		
		User returnUser = mediator.findUser(userName,passwd);
		if(returnUser.equals(null))
		{
			System.out.println("Incorrect user name or password. Try Again.");
			returnUser = login(mediator);
		}
		//if not found call login. 
		return returnUser;
		
	}

	private static void printLoginScreen() {
		System.out.println("Welcome to the teamsTechApp\n");
	}

	public static void performCurrentUserTasks(Mediator mediator,User currentUser) {
		System.out.println("What would you like to do? 	\n");
		printAuthorizedTasks();
		
		
	}

	private static void printAuthorizedTasks() {
		// TODO Auto-generated method stub
		
	}

}
