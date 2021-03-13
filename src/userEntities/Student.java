package userEntities;

public class Student extends User {
	final String domain = "std.iyte.edu.tr";
	public Student(String userName)
	{
		super(userName);
		String[] nameSurname = userName.split(" "); //student usernamelerin baþýnda boþluk var, array length 3 geliyor.
		String name = nameSurname[1].toLowerCase();
		String surname = nameSurname[2].toLowerCase();
		super.setEmail(name+surname+"@"+domain);
	}
}
