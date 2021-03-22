package mediator;

import teamEntities.Channel;
import teamEntities.Team;
import userEntities.User;

public interface IMediator {
	public boolean findTeam();
	public boolean removeTeam(Team team);
	public boolean removeMeetingChannel(Team team, Channel channel);
	public boolean updateMeetingChannelTime(String meetingTime,int channelPlace,int teamPlace);
	public boolean updateMeetingChannelParticipants();
	public boolean addMememberToTeam();
	public boolean removeMemberToTeam();
	public int numberOfStudents();
	public int numberOfInstructors();
	public int numberOfTeachingAssistants();
	public boolean showMeetingChanels();
	public boolean showMeetingTime();
	public boolean showParticipants();
	boolean addTeam(User author, String teamName);
	boolean addMeetingChannel(Team team,String channelName,String meetingTime, boolean isPrivate, String creator);
	
}
