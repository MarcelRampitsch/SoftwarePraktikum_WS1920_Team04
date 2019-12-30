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
	
	void updateUser(User upUser, AsyncCallback<User> callback);
	
	void deleteUser(User u, AsyncCallback<Void> callback);

	
	// Group
	
	void createGroup(Group g, AsyncCallback<Group> callback);

	void getGroupByGroupID(Group g, AsyncCallback<Group> callback);
	
	void getAllGroupByUserID(User u, AsyncCallback<Vector<Group>> callback);
	
	void updateGroup(Group updateg, AsyncCallback<Group> callback);

	void deleteGroupByGroupID(Group g, AsyncCallback<Void> callback);
	
	
	// Cinema
	
	void findAllCinemaByUser1(User u, AsyncCallback<Vector<Cinema>> callback);


	// GroupMember
	//void createGroupMember(Groupmember gm, AsyncCallback<Groupmember> callback);

	void getAllGroupmemberByGroupID(Group g, AsyncCallback <Vector<Groupmember>> callback);
	
	void getAllGroupmemberByUserID(User u, AsyncCallback <Vector<Groupmember>> callback);

	void updateGroupmember(Groupmember updateGm, AsyncCallback<Groupmember> callback);

	void deleteGroupmemberByGroupmemberID(Groupmember gm, AsyncCallback<Void> callback);
	
	void deleteAllGroupmemberByGroupID(Group g, AsyncCallback<Void> callback);
	
	

	// Survey
	void createSurvey(Survey s, AsyncCallback<Survey> callback);

	void getSurveyBySurveyID(Survey s, AsyncCallback<Survey> callback);
	
	void getSurveyBySurveyName(Survey s, AsyncCallback<Survey> callback);

	void updateSurvey(Survey updateS, AsyncCallback<Survey> callback);

	void deleteSurveyBySurveyID(Survey s, AsyncCallback<Void> callback);
	
	void deleteAllSurveyByGroupID(Group g, AsyncCallback<Void> callback);
	
	

	// SurveyEntry
	void createSurveyEntry(SurveyEntry se, AsyncCallback<SurveyEntry> callback);

	void getSurveyEntryBySurveyEntryID(SurveyEntry se, AsyncCallback<SurveyEntry> callback);

	void getSurveyEntryBySurveyID(SurveyEntry se, AsyncCallback<SurveyEntry> callback);

	void updateSurveyEntry(SurveyEntry upSurveyEntry, AsyncCallback<Void> callback);
	
	void deleteBySurveyEntryID(SurveyEntry se, AsyncCallback<Void> callback);
	
	void deleteAllBySurveyID(SurveyEntry se, AsyncCallback<Void> callback);
	
	void deleteAllByPresentationID(SurveyEntry se, AsyncCallback<Void> callback);
	

	// Vote
	void createVote(Vote v, AsyncCallback<Vote> callback);

	void getVoteByVoteID(Vote v, AsyncCallback<Vote> callback);
	
	void getAllVoteByUserID(User u, AsyncCallback<Vector<Vote>> callback);
	
	void getAllVoteBySurveyEntryID(SurveyEntry se, AsyncCallback<Vector<Vote>> callback);

	void updateVote(Vote upVote, AsyncCallback<Vote> callback);
	
	void deleteByVoteID(Vote v, AsyncCallback<Void> callback);
	
	void deleteAllBySurveyEntryID(Vote v, AsyncCallback<Void> callback);
	
	
	
	/*
	
	void getPresentation(AsyncCallback<Presentation> callback);


	void setPresentation(Presentation p, AsyncCallback<Void> callback);


	void delete(Presentation p, AsyncCallback<Void> callback);
*/
}
