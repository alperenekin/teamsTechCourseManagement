package teamEntities;

import java.util.ArrayList;

import mediator.Mediator;
import userEntities.User;

public class Team {
	
	private Mediator mediator;
	private String teamName;
	private String teamId;
	private ArrayList<User> owner;
	private ArrayList<String> participitans; //hoca Participantlari id olarak tutmus, sanrm id olarak tutmamiz daha makul olur ve unique olmak zorunda.
	private ArrayList<Channel> channels;
	
	public Team (String teamName, String teamId, ArrayList<Channel> channels) {
		this.teamName = teamName;
		this.teamId = teamId;
		this.channels = channels;
	}
	public String getId() {
		return teamId;
	}
	public boolean addChannel(Channel channel)
	{
		channels.add(channel);
		return false;
		
	}
	
	
}
