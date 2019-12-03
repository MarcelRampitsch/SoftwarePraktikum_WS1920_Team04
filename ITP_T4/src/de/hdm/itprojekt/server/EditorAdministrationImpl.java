package de.hdm.itprojekt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.shared.EditorAdministration;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;

public class EditorAdministrationImpl extends RemoteServiceServlet implements EditorAdministration {
	
	public void init() {	
	}

	@Override
	public Survey createSurvey(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SurveyEntry createSurveyEntry() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User createUser(String nickname, String email) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Group createGroup(String name) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Groupmember createGroupMember() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Survey getSurvey() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSurvey(Survey s) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SurveyEntry getSurveyEntry() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSurveyEntry(SurveyEntry se) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User getUser() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUser(User u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Group getGroup() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroup(Group g) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Groupmember getGroupmember() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGroupmember(Groupmember gm) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Presentation getPresentation() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPresentation(Presentation p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Survey s) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(SurveyEntry se) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User u) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Group g) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Groupmember gm) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Presentation p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

}
