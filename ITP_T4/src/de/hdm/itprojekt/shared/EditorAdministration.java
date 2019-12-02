package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

public interface EditorAdministration extends RemoteService {
	
	void init() throws IllegalArgumentException;
	
	public Survey createSurvey(String name)
			throws IllegalArgumentException;
	
	public SurveyEntry createSurveyEntry()
		throws IllegalArgumentException;
	
	public User createUser(String nickname, String email)
		throws IllegalArgumentException;
	
	public Group createGroup(String name)
		throws IllegalArgumentException;
	
	public GroupMember createGroupMember()
		throws IllegalArgumentException;
	
	public Survey getSurvey() throws IllegalArgumentException;
	
	public void setSurvey(Survey s) throws IllegalArgumentException;
	
	public SurveyEntry getSurveyEntry() throws IllegalArgumentException;
	
	public void setSurveyEntry(SurveyEntry se) throws IllegalArgumentException;
	
	public User getUser() throws IllegalArgumentException;
	
	public void setUser(User u) throws IllegalArgumentException;
	
	public Group getGroup() throws IllegalArgumentException;
	
	public void setGroup(Group g) throws IllegalArgumentException;
	
	public GroupMember getGroupMember() throws IllegalArgumentException;
	
	public void setGroupMember(GroupMember gm) throws IllegalArgumentException;
	
	}
}
