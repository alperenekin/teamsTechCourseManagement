package teamEntities;

import mediator.IIdentifiable;
import mediator.Mediator;
import userEntities.User;

public class Team implements IIdentifiable {
	
	private Mediator mediator;
	private String teamName;
	private int teamId;
	private User[] owner;
	private User[] participiants; //hoca Participantlari id olarak tutmus, sanrm id olarak tutmamiz daha makul olur ve unique olmak zorunda.
	private Channel[] channels;
	
	
	public int getId() {
		return teamId;
	}
	public boolean addChannel(String publicity,String name)
	{
		
		return false;
		
	}
	
	
}
