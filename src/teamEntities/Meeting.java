package teamEntities;

public class Meeting {
	private String name;
	//private Time meetingTime; time implement etmek istiyor muyuz , gün saat ve am/pm enumu olarak üç attribute dan oluşan, yani bi seçenek ama gereklilik değil. 
	private String meetingTime; //şimdilik string kolay olur gibi
	private int[] partipiciants;
	public Meeting()
	{
		this.name = "meeting";
	}
	public Meeting(String meetingTime) //meetinglerin adı yok gibi, default olma gibi birşey tutulabilir.
	{
		this.meetingTime = meetingTime;
	}
}
