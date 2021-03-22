package teamEntities;

public class Meeting implements IUpdateable{
	private String name;
	//private Time meetingTime; time implement etmek istiyor muyuz , gün saat ve am/pm enumu olarak üç attribute dan oluşan, yani bi seçenek ama gereklilik değil. 
	private String meetingTime; //şimdilik string kolay olur gibi
	public Meeting()
	{
		this.name = "meeting";
		this.meetingTime="Monday 9:45";
	}
	public Meeting(String meetingTime) //meetinglerin adı yok gibi, default olma gibi birşey tutulabilir.
	{
		this.setMeetingTime(meetingTime);
	}
	public String getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(String meetingTime) {
		this.meetingTime = meetingTime;
	}
	@Override
	public void update(String meetingTime) {
		this.meetingTime = meetingTime;
		
	}
}
