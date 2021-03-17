package mediator;

import teamEntities.Team;
import userEntities.User;

public interface IMediator {
	public boolean findTeam();
	public boolean removeTeam(Team team);
	public boolean removeMeetingChannel();
	public boolean updateMeetingChannelTime();
	public boolean updateMeetingChannelParticipants();
	public boolean addMememberToTeam();
	public boolean removeMemberToTeam();
	public boolean numberOfStudents();
	public boolean numberOfInstructors();
	public boolean numberOfTeachingAssistants();
	public boolean showMeetingChanels();
	public boolean showMeetingTime();
	public boolean showParticipants();
	boolean addTeam(User author, String teamName);
	boolean addMeetingChannel(Team team, String publicity, String name);
	
}
