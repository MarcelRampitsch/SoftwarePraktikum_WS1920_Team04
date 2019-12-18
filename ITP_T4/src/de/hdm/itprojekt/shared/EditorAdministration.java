package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Vote;

@RemoteServiceRelativePath("editor")
public interface EditorAdministration extends RemoteService {
	
	void init() throws IllegalArgumentException;
	
	// User Methoden
	
	// Einen User anlegen.
	public User createUser(User u)
			throws IllegalArgumentException;
	
	// Suchen von User Objekten deren Nickname bekannt ist.
	public User getUserByNickname(User u) throws IllegalArgumentException;
	
	// Suchen von User Objekten deren E-Mail Adresse bekannt ist.
	public User getUserByEmail(User u) throws IllegalArgumentException;
	
	public Vector<Group> getAllGroupnameByUserID(User u) throws IllegalArgumentException;
	
	// Aktualisieren eines User Objekts.
	public User updateUser(User upUser) throws IllegalArgumentException;
	
	// L�schen des �bergebenen Users.
	public void deleteUser(User u) throws IllegalArgumentException;

	
	// Group Methoden

	// Eine Gruppe anlegen.
	public Group createGroup(Group g)
		throws IllegalArgumentException;
	
	// Suchen von Gruppen Objekten nach GroupID.
	public Group getGroupByGroupID(Group g) throws IllegalArgumentException;
	
	// Suchen aller Gruppen Objekte nach UserID.
	public Vector<Group> getAllGroupByUserID(User u) throws IllegalArgumentException;
	
	// Aktualisieren eines Gruppen Objekts.
	//public Group updateGroup(Group g) throws IllegalArgumentException;
	
	// L�schen der Gruppe anhand der ID.
	public void deleteByGroupID(Group g) throws  IllegalArgumentException;
	
	// L�schen aller Gruppen die einer bestimmten UserID zugeordnet sind.
	public void deleteAllByUserID(User u) throws IllegalArgumentException;
	
	//Cinema
	Vector<Cinema> findAllCinemaByUser1(User u) throws IllegalArgumentException;


	// GroupMember Methoden
	
	// Einen Groupmember anlegen.
	/*public Groupmember createGroupMember(Groupmember gm)
		throws IllegalArgumentException;
*/
	// Alle Gruppenmitglieder anhand der GruppenID suchen.
	public Vector<Groupmember> getAllByGroupID(Group g) throws IllegalArgumentException;
	
	// Alle Gruppenmitglieder anhand der UserID suchen.
	public Vector<Groupmember> getAllByUserID(User u) throws IllegalArgumentException;
	
	// Aktualisieren des Gruppenmitglied Objekts.
	//public Groupmember updateGroupmember(Groupmember upGroupMember) throws IllegalArgumentException;

	// L�schen des Gruppenmitglieds anhand der ID.
	public void deleteByID(Groupmember gm) throws IllegalArgumentException;
	
	// L�schen aller Gruppenmitglieder anhand der GruppenID.
	public void deleteAllByGroupID(Groupmember gm) throws IllegalArgumentException;
	
	// L�schen aller Gruppenmitglieder anhand der UserID.
	public void deleteAllByUserID(Groupmember gm) throws IllegalArgumentException;

	// Survey Methoden
	
	// Eine Ummfage anlegen.
	public Survey createSurvey(Survey s)
			throws IllegalArgumentException;

	// Survey anhand der SurveyID suchen.
	public Survey getSurveyBySurveyID(Survey s) throws IllegalArgumentException;
	
	// Survey anhand des SurveyName suchen.
	public Survey getSurveyBySurveyName(Survey s) throws IllegalArgumentException;
	// Aktualisieren des Survey Objekts.
	public void updateSurvey(Survey upSurvey) throws IllegalArgumentException;
	
	// L�schen des Survey anhand der SurveyID.
	public void deleteBySurveyID(Survey s) throws IllegalArgumentException;
	
	// L�schen aller Surveys anhand der GruppenID.
	public void deleteAllByGroupID(Survey s) throws IllegalArgumentException;
	
	// L�schen aller Surveys anhand der UserID.
	public void deleteAllByUserID(Survey s) throws IllegalArgumentException;
	
	
	/*
	// SurveyEntry Methoden
	
	// Einen Umfrageeintrag erstellen.
	public SurveyEntry createSurveyEntry()throws IllegalArgumentException;
	
	// Einen Umfrageeintrag anhand der ID suchen.
	public SurveyEntry getSurveyEntryBySurveyEntryID() throws IllegalArgumentException;
	
	// Einen Umfrageeintrag anhand der SurveyID suchen.
	public SurveyEntry getSurveyEntryBySurveyID() throws IllegalArgumentException;
	
	// Aktualisieren des SurveyEntry Objekts.
	public void updateSurveyEntry(SurveyEntry upSurveyEntry) throws IllegalArgumentException;
	
	// L�schen des Umfrageeintrag anhand der SurveyEntryID.
	public void deleteBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException;
	
	// L�schen aller Umfrageeintr�ge anhand der SurveyID.
	public void deleteAllBySurveyID(SurveyEntry se) throws IllegalArgumentException;
	
	// L�schen aller Umfrageeintr�ge anhand der PresentationID.
	public void deleteAllByPresentationID(SurveyEntry se) throws IllegalArgumentException;
	
	
	// Vote Methoden
	
	// Einen Vote erstellen.
	public Vote createVote()
		throws IllegalArgumentException;
	
	// Einen Vote anhand der VoteID suchen.
	public Vote getVoteByVoteID() throws IllegalArgumentException;
	
	// Einen Vote anhand der UserID suchen.
	public Vote getAllVoteByUserID(Vote v) throws IllegalArgumentException;
	
	// Alle Votes anhand einer UmfrageeintragID suchen.
	public Vote getAllVoteBySurveyEntryID(Vote v) throws IllegalArgumentException;
	
	// Aktualisieren des Vote Objekts.
	public void updateVote(Vote upVote) throws IllegalArgumentException;
	
	// L�schen des Vote anhand der VoteID.
	public void deleteByVoteID(Vote v) throws IllegalArgumentException;
	
	// L�schen aller Votes anhand der UmfrageeintragID.
	public void deleteAllBySurveyEntryID(Vote v) throws IllegalArgumentException;
	
	// L�schen aller Votes anhand der UserID.
	public void deleteAllByUserID(Vote v) throws IllegalArgumentException;
	

	
	public Presentation getPresentation() throws IllegalArgumentException;
	
	public void setPresentation(Presentation p) throws IllegalArgumentException;

	public void delete(Presentation p) throws IllegalArgumentException;
	*/
}
