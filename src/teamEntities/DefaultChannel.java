package teamEntities;

public class DefaultChannel extends Channel {

	public DefaultChannel(String name, Meeting meeting) {
		super(name, meeting);
	}
	public DefaultChannel() {
		super("Default",new Meeting());
	}

	@Override
	boolean isAuserParticipant(String userId) { //bunu direk herkes g�r�yor mu yoksa o ders in ��rencileri mi sadece?
		// TODO Auto-generated method stub
		return false;
	}


}
