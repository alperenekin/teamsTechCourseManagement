package teamEntities;

import userEntities.User;

public class Team {
	private String teamName;
	private String teamId;
	private User[] owner;
	private User[] participiants; //hoca Participantlari id olarak tutmus, sanrm id olarak tutmamiz daha makul olur ve unique olmak zorunda.
	private Channel channels;//liste olarak tutumalý.
	
	public Team(String teamName, String teamId,Channel channel) {
		this.teamName = teamName;
		this.teamId = teamId;
		this.channels = channel;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getTeamId() {
		return teamId;
	}
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}
	public User[] getParticipiants() {
		return participiants;
	}
	public void setParticipiants(User[] participiants) {
		this.participiants = participiants;
	}
	public User[] getOwner() {
		return owner;
	}
	public void setOwner(User[] owner) {
		this.owner = owner;
	}
	public Channel getChannels() {
		return channels;
	}
	public void setChannels(Channel channels) {
		this.channels = channels;
	}
}
