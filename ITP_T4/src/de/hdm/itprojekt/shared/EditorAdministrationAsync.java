package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.bo.Cinema;
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
	
	void createUser(User u, AsyncCallback<User> callback);
	
	void getUserByNickname(User u, AsyncCallback<User> callback);
	
	void getUserByEmail(User u, AsyncCallback<User> callback);
	
	void getAllGroupnameByUserID(User u, AsyncCallback<Vector<Group>> callback);
	
	void updateUser(User upUser, AsyncCallback<User> callback);
	
	void deleteUser(User u, AsyncCallback<Void> callback);

	
	// Group
	
	void createGroup(Group g, AsyncCallback<Group> callback);

	void getGroupByGroupID(Group g, AsyncCallback<Group> callback);
	
	void getAllGroupByUserID(User u, AsyncCallback<Vector<Group>> callback);
	
	//void updateGroup(Group g, AsyncCallback<Group> callback);

	void deleteByGroupID(Group g, AsyncCallback<Void> callback);
	
	void deleteAllByUserID(User u, AsyncCallback<Void> callback);
	
	// Cinema
	
	void findAllCinemaByUser1(User u, AsyncCallback<Vector<Cinema>> callback);


	// GroupMember
	//void createGroupMember(Groupmember gm, AsyncCallback<Groupmember> callback);

	void getAllByGroupID(Group g, AsyncCallback <Vector<Groupmember>> callback);
	
	void getAllByUserID(User u, AsyncCallback <Vector<Groupmember>> callback);

	//void updateGroupmember(Groupmember upGroupmember, AsyncCallback<Groupmember> callback);

	void deleteByID(Groupmember gm, AsyncCallback<Void> callback);
	
	void deleteAllByGroupID(Groupmember gm, AsyncCallback<Void> callback);
	
	void deleteAllByUserID(Groupmember gm, AsyncCallback<Void> callback);
	

	// Survey
	void createSurvey(Survey s, AsyncCallback<Survey> callback);

	void getSurveyBySurveyID(Survey s, AsyncCallback<Survey> callback);
	
	void getSurveyBySurveyName(Survey s, AsyncCallback<Survey> callback);

	void updateSurvey(Survey upSurvey, AsyncCallback<Void> callback);

	void deleteBySurveyID(Survey s, AsyncCallback<Void> callback);
	
	void deleteAllByGroupID(Survey s, AsyncCallback<Void> callback);
	
	void deleteAllByUserID(Survey s, AsyncCallback<Void> callback);
	

	// SurveyEntry
	void createSurveyEntry(SurveyEntry se, AsyncCallback<SurveyEntry> callback);

	void getSurveyEntryBySurveyEntryID(SurveyEntry se, AsyncCallback<SurveyEntry> callback);

	void getSurveyEntryBySurveyID(SurveyEntry se, AsyncCallback<SurveyEntry> callback);

	void updateSurveyEntry(SurveyEntry upSurveyEntry, AsyncCallback<Void> callback);
	
	void deleteBySurveyEntryID(SurveyEntry se, AsyncCallback<Void> callback);
	
	void deleteAllBySurveyID(SurveyEntry se, AsyncCallback<Void> callback);
	
	void deleteAllByPresentationID(SurveyEntry se, AsyncCallback<Void> callback);
	
	
	/*
	// Vote
	void createVote(AsyncCallback<Vote> callback);

	void getVoteByVoteID(AsyncCallback<Vote> callback);
	
	void getAllVoteByUserID(Vote v, AsyncCallback<Vote> callback);
	
	void getAllVoteBySurveyEntryID(Vote v, AsyncCallback<Vote> callback);

	void updateVote(Vote upVote, AsyncCallback<Void> callback);
	
	void deleteByVoteID(Vote v, AsyncCallback<Void> callback);
	
	void deleteAllBySurveyEntryID(Vote v, AsyncCallback<Void> callback);
	
	void deleteAllByUserID(Vote v, AsyncCallback<Void> callback);
	
	
	
	
	void getPresentation(AsyncCallback<Presentation> callback);


	void setPresentation(Presentation p, AsyncCallback<Void> callback);


	void delete(Presentation p, AsyncCallback<Void> callback);
*/
}
