package teamTechApp;

import java.util.ArrayList;
import java.util.List;

import fileOperations.FileIO;
import userEntities.Instructor;
import userEntities.Student;
import userEntities.TeachingAsistant;
import userEntities.User;

public class TeamsTechApp {
	List<User> userList = new ArrayList<User>();
	
	public void init() {
		FileIO fileIO = new FileIO();
		ArrayList<ArrayList<String>> userStrings = fileIO.readCsv("userList");
		ArrayList<ArrayList<String>> teamStrings = fileIO.readCsv("teamList");
		for(int i=1; i<userStrings.size() ; i++) {
			String userType = userStrings.get(i).get(0);
			String userName = userStrings.get(i).get(1);
			List<String> teamIDs;
			if(userStrings.get(i).size() > 5) { // it means this student has a team/teams
				teamIDs = userStrings.get(i).subList(5, userStrings.get(i).size());
			}
			//BURADA USER YARATTIK ANCAK TEAMIDs LERI KULLANMADIK
			User newUser = createUser(userType,userName);
			userList.add(newUser);
		}
	}
	
	private User createUser(String userType, String username) {
		if(userType.toUpperCase().equals("INSTRUCTOR")) { //instructor vs enum yapýlabilri
			Instructor instructor = new Instructor(username);
			return instructor;
		}
		if(userType.toUpperCase().equals("TEACHÝNG ASSÝSTANT")) { // türkçe karakter olarak upper case yaptý?
			TeachingAsistant teachingAsistant = new TeachingAsistant(username);
			return teachingAsistant;
		}
		else{
			Student student = new Student(username);
			//System.out.print(student.getEmail());
			return student;
		}
	}
}
