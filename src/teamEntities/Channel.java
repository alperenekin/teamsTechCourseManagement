package teamEntities;

import userEntities.User;

public class Channel implements IUpdateable{
	private String name;
	private String publicty;
	private User[] members;
	private Meeting meeting;

	
	public Channel() {
		this.name = "General";
		this.publicty = "Public";
	}
	public Channel(String name, Meeting meeting) {//publicity falan eklenmeli
		this.name = name;
		this.meeting = meeting;
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
}
