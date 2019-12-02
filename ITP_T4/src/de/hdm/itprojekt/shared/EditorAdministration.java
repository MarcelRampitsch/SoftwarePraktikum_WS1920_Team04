package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;


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
	
	public Groupmember createGroupMember()
		throws IllegalArgumentException;
	
	public Survey getSurvey() throws IllegalArgumentException;
	
	public void setSurvey(Survey s) throws IllegalArgumentException;
	
	public SurveyEntry getSurveyEntry() throws IllegalArgumentException;
	
	public void setSurveyEntry(SurveyEntry se) throws IllegalArgumentException;
	
	public User getUser() throws IllegalArgumentException;
	
	public void setUser(User u) throws IllegalArgumentException;
	
	public Group getGroup() throws IllegalArgumentException;
	
	public void setGroup(Group g) throws IllegalArgumentException;
	
	public Groupmember getGroupmember() throws IllegalArgumentException;
	
	public void setGroupmember(Groupmember gm) throws IllegalArgumentException;
	
	
	public void delete(Survey s) throws IllegalArgumentException;
	
	public void delete(SurveyEntry se) throws IllegalArgumentException;
	
	public void delete(User u) throws IllegalArgumentException;
	
	public void delete(Group g) throws  IllegalArgumentException;
	
	public void delete(Groupmember gm) throws IllegalArgumentException;
	
}
