package userEntities;

public abstract class User {

	private String name;
	private int id;
	private String email;
	private String passwd;
	private String department;
	
	public User() {
		
	}
	public User(String userName,String email)
	{
		this.name = userName;
		this.id = UserUtil.generateId();
		this.email= email;
		
		
	}
	
}
