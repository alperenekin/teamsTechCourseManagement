package teamEntities;

public class Meeting implements IUpdateable{
	private String name;
	private String meetingTime;
	public Meeting()
	{
		this.name = "meeting";
		this.meetingTime="Monday 9:45";
	}
	public Meeting(String meetingTime)
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
