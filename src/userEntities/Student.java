package userEntities;

public class Student extends User {
	final String domain = "std.iyte.edu.tr";
	public Student(String userName, int userId)
	{
		super(userName,userId);
		String[] nameSurname = userName.split(" "); //student usernamelerin baþýnda boþluk var, array length 3 geliyor.
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
}
