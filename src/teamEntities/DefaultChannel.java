package teamEntities;

public class DefaultChannel extends Channel {

	public DefaultChannel(String name, Meeting meeting) {
		super(name, meeting);
	}
	public DefaultChannel() {
		super("Default",new Meeting());
	}

	@Override
	boolean isAuserParticipant(String userId) { //bunu direk herkes görüyor mu yoksa o ders in öðrencileri mi sadece?
		// TODO Auto-generated method stub
		return false;
	}


}
