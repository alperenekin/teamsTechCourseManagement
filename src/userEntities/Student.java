package userEntities;

import teamEntities.Team;

public class Student extends User {
	final String domain = "std.iyte.edu.tr";
	public Student(String userName, int userId, String password)
	{
		super(userName,userId,password);
		String[] nameSurname = userName.split(" "); //student usernamelerin başında boşluk var, array length 3 geliyor.
		String name;
		String secondName;
		String surname;
		if(nameSurname.length == 4) { // Some students have 2 names
			 name = nameSurname[1].toLowerCase();
			 secondName = nameSurname[2].toLowerCase();
			 surname = nameSurname[3].toLowerCase();
			 super.setEmail(name+secondName+surname+"@"+domain);
		}
		else {
			 name = nameSurname[1].toLowerCase();
			 surname = nameSurname[2].toLowerCase();
			 super.setEmail(name+surname+"@"+domain);
		}
	}
	public String toString() { //this method needed to convert an object to file line
		String type = "Student";
		String username = super.getName();
		String userid = String.valueOf(super.getId());
		String email = super.getEmail();
		String password = super.getPasswd();
		String teams = "";
		if(super.getTeams().size() > 0) { // if has teams
			for(Team t: super.getTeams()) {
				teams += t.getId()+",";
			}
			teams = teams.substring(0,teams.length()-1);
			return type + "," + username + "," + userid +"," + email + "," + password +"," + teams;
		}
		return type + "," + username + "," + userid +"," + email + "," + password;

	}
}
