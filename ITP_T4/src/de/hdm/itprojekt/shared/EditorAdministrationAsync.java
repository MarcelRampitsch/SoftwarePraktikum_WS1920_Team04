package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;

public interface EditorAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);
	

	void createSurvey(String name, AsyncCallback<Survey> callback);

	void createGroup(String name, AsyncCallback<Group> callback);

	void createGroupMember(AsyncCallback<Groupmember> callback);

	void createSurveyEntry(AsyncCallback<SurveyEntry> callback);

	void createUser(String nickname, String email, AsyncCallback<User> callback);

	void getGroup(AsyncCallback<Group> callback);

	void getGroupmember(AsyncCallback<Groupmember> callback);

	void getSurvey(AsyncCallback<Survey> callback);

	void getSurveyEntry(AsyncCallback<SurveyEntry> callback);

	void getUser(AsyncCallback<User> callback);

	void setGroup(Group g, AsyncCallback<Void> callback);

	void setGroupmember(Groupmember gm, AsyncCallback<Void> callback);

	void setSurvey(Survey s, AsyncCallback<Void> callback);

	void setSurveyEntry(SurveyEntry se, AsyncCallback<Void> callback);

	void setUser(User u, AsyncCallback<Void> callback);


	void delete(Survey s, AsyncCallback<Void> callback);
	
	void delete(SurveyEntry se, AsyncCallback<Void> callback);
	
	void delete(User u, AsyncCallback<Void> callback);

	void delete(Group g, AsyncCallback<Void> callback);

	void delete(Groupmember gm, AsyncCallback<Void> callback);

}
