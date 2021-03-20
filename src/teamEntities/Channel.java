package teamEntities;

import java.util.ArrayList;

import userEntities.User;

public class Channel implements IUpdateable{
	private String name;
	private String publicity;
	private ArrayList<User> participants;
	private Meeting meeting;

	
	public Channel() {
		this.name = "General";
		this.publicity = "Public";
	}
	public Channel(String name,String publicity, Meeting meeting) {//publicity falan eklenmeli
		this.name = name;
		this.meeting = meeting;
		this.publicity = publicity;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
}
