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
	
	// Survey
	void createSurvey(String name, AsyncCallback<Survey> callback);

	void getSurveyBySurveyID(AsyncCallback<Survey> callback);
	
	void getSurveyBySurveyName(Survey s, AsyncCallback<Survey> callback);

	void updateSurvey(Survey upSurvey, AsyncCallback<Void> callback);

	void deleteBySurveyID(Survey s, AsyncCallback<Void> callback);
	
	void deleteAllByGroupID(Survey s, AsyncCallback<Void> callback);
	
	void deleteAllByUserID(Survey s, AsyncCallback<Void> callback);
	
	// SurveyEntry
	void createSurveyEntry(AsyncCallback<SurveyEntry> callback);

	void getSurveyEntryBySurveyEntryID(AsyncCallback<SurveyEntry> callback);

	void getSurveyEntryBySurveyID(AsyncCallback<SurveyEntry> callback);

	void updateSurveyEntry(SurveyEntry upSurvey, AsyncCallback<Void> callback);
	
	void deleteBySurveyEntryID(SurveyEntry se, AsyncCallback<Void> callback);
	
	void deleteAllBySurveyID(SurveyEntry se, AsyncCallback<Void> callback);
	
	void deleteAllByPresentationID(SurveyEntry se, AsyncCallback<Void> callback);
	
	// Vote
	void createVote(int voteResult, AsyncCallback<Vote> callback);

	void getVoteByVoteID(AsyncCallback<Vote> callback);
	
	void getAllVoteByUserID(AsyncCallback<Vote> callback);
	
	void getAllVoteBySurveyEntryID(AsyncCallback<Vote> callback);

	void updateVote(Vote upVote, AsyncCallback<Void> callback);
	
	void deleteByVoteID(Vote v, AsyncCallback<Void> callback);
	
	void deleteAllBySurveyEntryID(Vote v, AsyncCallback<Void> callback);
	
	void deleteAllByUserID(Vote v, AsyncCallback<Void> callback);
	
	
	
	
	void getPresentation(AsyncCallback<Presentation> callback);


	void setPresentation(Presentation p, AsyncCallback<Void> callback);


	void delete(Presentation p, AsyncCallback<Void> callback);

}
