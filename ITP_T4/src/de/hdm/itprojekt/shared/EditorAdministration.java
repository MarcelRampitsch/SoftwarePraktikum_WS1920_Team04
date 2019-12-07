package de.hdm.itprojekt.shared;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Vote;

public interface EditorAdministration extends RemoteService {
	
	void init() throws IllegalArgumentException;
	
	// User Methoden
	
	// Einen User anlegen.
	public User createUser(String nickname, String email)
		throws IllegalArgumentException;
	
	// Suchen von User Objekten deren Nickname bekannt ist.
	public User getUserByNickname() throws IllegalArgumentException;
	
	// Suchen von User Objekten deren E-Mail Adresse bekannt ist.
	public User getUserByEmail() throws IllegalArgumentException;
	
	// Aktualisieren eines User Objekts.
	public void updateUser(User upUser) throws IllegalArgumentException;
	
	// Löschen des übergebenen Users.
	public void delete(User u) throws IllegalArgumentException;
	
	
	// Group Methoden

	// Eine Gruppe anlegen.
	public Group createGroup(String name)
		throws IllegalArgumentException;
	
	// Suchen von Gruppen Objekten nach GroupID.
	public Group getGroupByGroupID() throws IllegalArgumentException;
	
	// Suchen aller Gruppen Objekte nach UserID.
	public void getAllGroupByUserID(Group g) throws IllegalArgumentException;
	
	// Aktualisieren eines Gruppen Objekts.
	public void updateGroup(Group upGroup) throws IllegalArgumentException;
	
	// Löschen der Gruppe anhand der ID.
	public void deleteByGroupID(Group g) throws  IllegalArgumentException;
	
	// Löschen aller Gruppen die einer bestimmten UserID zugeordnet sind.
	public void deleteAllByUserID(Group g) throws IllegalArgumentException;
	
	
	public Survey createSurvey(String name)
		throws IllegalArgumentException;
	
	public SurveyEntry createSurveyEntry()
		throws IllegalArgumentException;
	
	public Groupmember createGroupMember()
		throws IllegalArgumentException;
	
	public Vote createVote(int voteResult)
		throws IllegalArgumentException;
	
	public Survey findBySurveyID() throws IllegalArgumentException;
	
	public void setSurvey(Survey s) throws IllegalArgumentException;
	
	public SurveyEntry getSurveyEntry() throws IllegalArgumentException;
	
	public void setSurveyEntry(SurveyEntry se) throws IllegalArgumentException;

	public Groupmember getGroupmember() throws IllegalArgumentException;
	
	public void setGroupmember(Groupmember gm) throws IllegalArgumentException;
	
	public Presentation getPresentation() throws IllegalArgumentException;
	
	public void setPresentation(Presentation p) throws IllegalArgumentException;
	
	public Vote findVoteByVoteID() throws IllegalArgumentException;
	
	public void setVote(Vote v) throws IllegalArgumentException;
	
	
	public void delete(Survey s) throws IllegalArgumentException;
	
	public void delete(SurveyEntry se) throws IllegalArgumentException;

	public void delete(Groupmember gm) throws IllegalArgumentException;
	
	public void delete(Presentation p) throws IllegalArgumentException;
	
	public void delete(Vote v) throws IllegalArgumentException;
	
}
