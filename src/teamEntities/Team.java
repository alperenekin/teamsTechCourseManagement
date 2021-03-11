package teamEntities;

import userEntities.User;

public class Team {
	private String teamName;
	private int teamId;
	private User[] owner;
	private User[] participiants; //hoca Participantlari id olarak tutmus, sanrm id olarak tutmamiz daha makul olur ve unique olmak zorunda.
	private Channel channels;
}
