package teamEntities;

import java.util.ArrayList;

import userEntities.User;

public abstract class Channel implements IUpdateable{
	private String name;
	private Meeting meeting;


	public Channel(String name, Meeting meeting) {
		this.setName(name);
		this.meeting = meeting;
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
	
	abstract boolean isAuserParticipant(String userId);
	
	
}
