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

	public Team (String teamName, String teamIds) {
		this.teamName = teamName;
		this.teamId = teamIds;
		this.channels = new ArrayList<Channel>();
		this.members = new ArrayList<User>();
		this.owners = new ArrayList<User>();
	}
	public Team(String teamName,String id,User author) {
		this.teamName = teamName;
		this.teamId = "CENG"+id;
		this.channels = new ArrayList<Channel>();
		this.channels.add(new DefaultChannel());
		this.members = new ArrayList<User>();
		members.add(author);
		this.owners = new ArrayList<User>();
		owners.add(author);
		
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
	
	public boolean addPrivateChannel(PrivateChannel privateChannel)
	{
		return channels.add(privateChannel);
	}
	
	public boolean addDefaultChannel(DefaultChannel defaultChannel)
	{
		return channels.add(defaultChannel);
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
