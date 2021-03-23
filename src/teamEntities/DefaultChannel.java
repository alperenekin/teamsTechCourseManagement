package teamEntities;

public class DefaultChannel extends Channel {

	public DefaultChannel(String name, Meeting meeting) {
		super(name, meeting);
	}
	public DefaultChannel() {
		super("Default",new Meeting());
	}

	@Override
	boolean isAuserParticipant(String userId) {

		return true;
	}


}
