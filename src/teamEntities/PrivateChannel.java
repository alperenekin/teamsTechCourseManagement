package teamEntities;

import java.util.ArrayList;

import userEntities.User;

public class PrivateChannel extends Channel {
	ArrayList<String> participants; //user ids are kept in participants.
	
	public PrivateChannel(String name, Meeting meeting) {
		super(name, meeting);
		this.participants = new ArrayList<String>();
	}

	@Override
	boolean isAuserParticipant(String userId) {
		if(participants.contains(userId)) {
			return true;
		}
		return false;
	}

	public boolean addParticipant(String participant) {
		participants.add(participant);
		return true;
	}
	public String toString() {
		String participants = "\"";
		for(String participant : this.participants) {
			participants +=  participant + ",";
		}
		participants = participants.substring(0,participants.length()-1);
		participants += "\"";
		if(super.getMeeting() == null) {
			return super.getName() + "," + "," + participants;
		}else {
			return super.getName() +"," + super.getMeeting().getMeetingTime() + "," + participants;
		}
	}
}
