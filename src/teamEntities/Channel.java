package teamEntities;

import userEntities.User;

public class Channel implements IUpdateable{
	private String name;
	private String publicty;
	private User[] members;
	public Channel() {
		this.name = "General";
		this.publicty = "Public";
	}
	public Channel(String name) {
		this.name = name;
		
	}
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
}
