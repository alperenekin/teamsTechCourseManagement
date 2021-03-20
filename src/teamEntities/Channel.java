package teamEntities;

import java.util.ArrayList;

import userEntities.User;

public class Channel implements IUpdateable{
	private String name;
	private String publicity;
	private ArrayList<User> participants;
	private Meeting meeting;

	
	public Channel() {
		this.setName("General");
		this.publicity = "Public";
	}
	public Channel(String name,String publicity, Meeting meeting) {//publicity falan eklenmeli
		this.setName(name);
		this.meeting = meeting;
		this.publicity = publicity;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
