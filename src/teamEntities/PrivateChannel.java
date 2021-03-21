package teamEntities;

import java.util.ArrayList;

import userEntities.User;

public class PrivateChannel extends Channel {
	ArrayList<String> participants; //user ids are kept in participants.
	
	public PrivateChannel(String name, Meeting meeting, ArrayList<String> participants) {
		super(name, meeting);
		this.participants = participants;
	}

	@Override
	boolean isAuserParticipant(String userId) {
		if(participants.contains(userId)) {
			return true;
		}
		return false;
	}

}
