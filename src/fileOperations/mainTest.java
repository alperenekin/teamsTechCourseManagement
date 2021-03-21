package fileOperations;

import java.util.ArrayList;

import teamTechApp.TeamsTechApp;
import userEntities.Student;
import userEntities.User;

public class mainTest {

	public static void main(String[] args) {
		FileIO fileIO = new FileIO();
		ArrayList<ArrayList<String>> lines = fileIO.readCsv("userList");
		System.out.println(lines.get(3).get(3).isEmpty());
		//Student student = new Student(lines.get(12).get(1));
		//System.out.println(student.getEmail());
		//fileIO.replaceLines("Building Software Systems", "A new line", "teamList"); 

		TeamsTechApp teamsTechApp = new TeamsTechApp();
		teamsTechApp.init();
	}

}
