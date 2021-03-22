package teamEntities;

import java.util.ArrayList;

import mediator.Mediator;
import userEntities.User;

public class Team {
	
	private Mediator mediator;
	private String teamName;
	private String teamId;
	private ArrayList<User> owners;
	private ArrayList<User> members; //hoca Participantlari id olarak tutmus, sanrm id olarak tutmamiz daha makul olur ve unique olmak zorunda.
	private ArrayList<Channel> channels;

	public Team (String teamName, String teamId, ArrayList<Channel> channels) {
		this.teamName = teamName;
		this.teamId = teamId;
		this.channels = channels;
		this.members = new ArrayList<User>();
		this.owners = new ArrayList<User>();
	}
	public String getId() {
		return teamId;
	}
	
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public boolean addMember(User user) {
		return members.add(user);
	}
	
	public boolean addChannel(Channel channel)
	{
		return channels.add(channel);
	}
	
	public boolean removeChannel(Channel newChannel) {
		ArrayList<Channel> foundChannels = new ArrayList<Channel>();
		for(Channel channel : channels) {
			if(newChannel.getName().equals(channel.getName())) {
				foundChannels.add(channel);
			}
		}
		channels.removeAll(foundChannels);
		return true;
	}
	public ArrayList<Channel> getChannels() {
		return channels;
	}
	public void setChannels(ArrayList<Channel> channels) {
		this.channels = channels;
	}
	
	public boolean addOwner(User teamOwner)
	{
		owners.add(teamOwner);
		return false;
	}
	
	public String toString() {		
		DefaultChannel defaultChannel = findDefaultChannel();
		String channelName = defaultChannel.getName();
		String defaultMeeting = defaultChannel.getMeeting().getMeetingTime();
		String privateChannels = "";
		for(Channel channel : channels) {
			if(channel instanceof PrivateChannel) {
				privateChannels += channel.toString() + ",";
			}
		}
		if(privateChannels.length()>1) {
			privateChannels = privateChannels.substring(0,privateChannels.length()-1);
			return this.teamName + "," + this.teamId + "," + channelName + "," + defaultMeeting + "," + privateChannels;
		}
		return this.teamName + "," + this.teamId + "," + channelName + "," + defaultMeeting;

	}
	
	public DefaultChannel findDefaultChannel() {
		for(Channel channel : channels) {
			if(channel instanceof DefaultChannel){
				return (DefaultChannel) channel;
			}
		}
		return null;
	}

	
}
