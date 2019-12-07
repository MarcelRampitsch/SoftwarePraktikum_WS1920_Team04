package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Vote;

public interface EditorAdministrationAsync {
	
	void init(AsyncCallback<Void> callback);
	
	// User
	void createUser(String nickname, String email, AsyncCallback<User> callback);
	
	void getUserByNickname(AsyncCallback<User> callback);

	void getUserByEmail(AsyncCallback<User> callback);
	
	void updateUser(User upUser, AsyncCallback<Void> callback);
	
	void delete(User u, AsyncCallback<Void> callback);
	
	// Group

	void createGroup(String name, AsyncCallback<Group> callback);

	void getGroupByGroupID(AsyncCallback<Group> callback);
	
	void getAllGroupByUserID(Group g, AsyncCallback<Void> callback);
	
	void updateGroup(Group upGroup, AsyncCallback<Void> callback);

	void deleteByGroupID(Group g, AsyncCallback<Void> callback);
	
	void deleteAllByUserID(Group g, AsyncCallback<Void> callback);
	
	// GroupMember

	void createGroupMember(AsyncCallback<Groupmember> callback);

	void getAllByGroupID(AsyncCallback<Groupmember> callback);
	
	void getAllByUserID(AsyncCallback<Groupmember> callback);

	void updateGroupmember(Groupmember upGroupmember, AsyncCallback<Void> callback);

	void deleteByID(Groupmember gm, AsyncCallback<Void> callback);
	
	void deleteAllByGroupID(Groupmember gm, AsyncCallback<Void> callback);
	
	void deleteAllByUserID(Groupmember gm, AsyncCallback<Void> callback);
	
	
	
	
	void createSurvey(String name, AsyncCallback<Survey> callback);

	void createSurveyEntry(AsyncCallback<SurveyEntry> callback);
	
	void createVote(int voteResult, AsyncCallback<Vote> callback);



	void findBySurveyID(AsyncCallback<Survey> callback);

	void getSurveyEntry(AsyncCallback<SurveyEntry> callback);
	
	void getPresentation(AsyncCallback<Presentation> callback);

	void findVoteByVoteID(AsyncCallback<Vote> callback);
	


	void setSurvey(Survey s, AsyncCallback<Void> callback);

	void setSurveyEntry(SurveyEntry se, AsyncCallback<Void> callback);

	void setPresentation(Presentation p, AsyncCallback<Void> callback);
	
	void setVote(Vote v, AsyncCallback<Void> callback);


	void delete(Survey s, AsyncCallback<Void> callback);
	
	void delete(SurveyEntry se, AsyncCallback<Void> callback);
	
	void delete(Presentation p, AsyncCallback<Void> callback);
	
	void delete(Vote v, AsyncCallback<Void> callback);

}
