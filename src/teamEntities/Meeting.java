package teamEntities;

public class Meeting {
	private String name;
	//private Time meetingTime; time implement etmek istiyor muyuz , gün saat ve am/pm enumu olarak üç attribute dan oluşan, yani bi seçenek ama gereklilik değil. 
	private int[] partipiciants;
	public Meeting()
	{
		this.name = "meeting";
	}
	public Meeting(String name)
	{
		this.name = name;
		//this.meetingTime = time;
	}
}
