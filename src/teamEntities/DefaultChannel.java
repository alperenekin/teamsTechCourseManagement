package teamEntities;

public class DefaultChannel extends Channel {

	public DefaultChannel(String name, Meeting meeting) {
		super(name, meeting);
		// TODO Auto-generated constructor stub
	}

	@Override
	boolean isAuserParticipant(String userId) { //bunu direk herkes g�r�yor mu yoksa o ders in ��rencileri mi sadece?
		// TODO Auto-generated method stub
		return false;
	}

}
