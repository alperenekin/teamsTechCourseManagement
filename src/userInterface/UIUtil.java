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
		System.out.print("Username:  ");  
		String userName = scanner.nextLine();  
		System.out.print("Passwd:  ");  
		int passwd= scanner.nextInt();
		//findUser();
		//if not found call login. 
		return null;
		
	}

	private static void printLoginScreen() {
		System.out.println("Welcome to the teamsTechApp\n");
		System.out.println("(First time users password is always '1234') \n");
	}

	public static void performCurrentUserTasks(Mediator mediator,User currentUser) {
		System.out.println("What would you like to do? 	\n");
		printAuthorizedTasks();
		
		
	}

	private static void printAuthorizedTasks() {
		// TODO Auto-generated method stub
		
	}

}
