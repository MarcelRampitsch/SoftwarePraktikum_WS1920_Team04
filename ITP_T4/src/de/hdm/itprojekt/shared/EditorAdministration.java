package de.hdm.itprojekt.shared;

import java.sql.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Vote;
import de.hdm.itprojekt.shared.bo.Timeslot;

@RemoteServiceRelativePath("editor")
public interface EditorAdministration extends RemoteService {

	void init() throws IllegalArgumentException;
	
	// User Methoden
	
	// Einen User anlegen.
	public User createUser(User u)
			throws IllegalArgumentException;
	
	public User getUserByUserID(User u) throws IllegalArgumentException;
	
	// Suchen von User Objekten deren Nickname bekannt ist.
	public User getUserByNickname(User u) throws IllegalArgumentException;
	
	// Suchen von User Objekten deren E-Mail Adresse bekannt ist.
	public User getUserByEmail(User u) throws IllegalArgumentException;
	
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
	public Group updateGroup(Group g) throws IllegalArgumentException;
	
	// L�schen der Gruppe anhand der ID.
	public void deleteGroupByGroupID(Group g) throws  IllegalArgumentException;
	
	
	//Cinema
	Vector<Cinema> findAllCinemaByUser1(User u) throws IllegalArgumentException;
	
	Vector<CinemaGroup> getAllCinemaGroupByUser(User u);

	// Methode um alle Cinema eines User zu finden
	public Vector<Cinema> getAllCinemaByUser(User u) throws IllegalArgumentException;
	
	public Vector<Movie> getAllMovieByUser(User u) throws IllegalArgumentException;
	
	public Vector<Timeslot> getAllTimeslotByUser(User u) throws IllegalArgumentException;
	
	public Vector<Presentation> getAllPresentationBySearchCriteria(Presentation p) throws IllegalArgumentException;
	
	public Cinema getCinemaBySurveyEntry(SurveyEntry se) throws IllegalArgumentException;
	public CinemaGroup getCinemaGroupBySurveyEntry(SurveyEntry se) throws IllegalArgumentException;
	public Movie getMovieBySurveyEntry(SurveyEntry se) throws IllegalArgumentException;
	public Timeslot getTimeslotBySurveyEntry(SurveyEntry se) throws IllegalArgumentException;
	public Presentation getPresentationBySurveyEntry (SurveyEntry se) throws IllegalArgumentException; 

	// GroupMember Methoden
	
	public Vector<Group> getAllGroupsIamMemberFrom(User u )throws IllegalArgumentException;
	
	// Einen Groupmember anlegen.
	public Groupmember createGroupmember(Groupmember gm) throws IllegalArgumentException;

	// Alle Gruppenmitglieder anhand der GruppenID suchen.
	public Vector<Groupmember> getAllGroupmemberByGroupID(Group g) throws IllegalArgumentException;
	
	// Alle Gruppenmitglieder anhand der UserID suchen.
	public Vector<Groupmember> getAllGroupmemberByUserID(User u) throws IllegalArgumentException;
	
	// Aktualisieren des Gruppenmitglied Objekts.
	public Groupmember updateGroupmember(Groupmember updateGm) throws IllegalArgumentException;

	// L�schen des Gruppenmitglieds anhand der ID.
	public void deleteGroupmemberByGroupmemberID(Groupmember gm) throws IllegalArgumentException;
	
	// L�schen aller Gruppenmitglieder anhand der GruppenID.
	public void deleteAllGroupmemberByGroupID(Group g) throws IllegalArgumentException;
	

	// Survey Methoden
	
	// Eine Ummfage anlegen.
	public Survey createSurvey(Survey s) throws IllegalArgumentException;

	public Vector<Survey> getAllSurveyByGroupID(Group g) throws IllegalArgumentException;
	
	// Survey anhand der SurveyID suchen.
	public Survey getSurveyBySurveyID(Survey s) throws IllegalArgumentException;
	
	// Survey anhand des SurveyName suchen.
	public Survey getSurveyBySurveyName(Survey s) throws IllegalArgumentException;
	
	// Aktualisieren des Survey Objekts.
	public Survey updateSurvey(Survey updateS) throws IllegalArgumentException;
	
	// L�schen des Survey anhand der SurveyID.
	public void deleteSurveyBySurveyID(Survey s) throws IllegalArgumentException;
	
	// L�schen aller Surveys anhand der GruppenID.
	public void deleteAllSurveyByGroupID(Group g) throws IllegalArgumentException;
	
	

	// SurveyEntry Methoden
	
	// Einen Umfrageeintrag erstellen.
	public SurveyEntry createSurveyEntry(SurveyEntry se)throws IllegalArgumentException;
	
	// Einen Umfrageeintrag anhand der ID suchen.
	public SurveyEntry getSurveyEntryBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException;
	
	// Einen Umfrageeintrag anhand der SurveyID suchen.
	public Vector <SurveyEntry> getAllSurveyEntryBySurveyID(Survey s) throws IllegalArgumentException;
	
	// Aktualisieren des SurveyEntry Objekts.
	public SurveyEntry updateSurveyEntry(SurveyEntry upSurveyEntry) throws IllegalArgumentException;
	
	// L�schen des Umfrageeintrag anhand der SurveyEntryID.
	public void deleteSurveyEntryBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException;
	
	// L�schen aller Umfrageeintr�ge anhand der SurveyID.
	public void deleteAllSurveyEntryBySurveyID(Survey s) throws IllegalArgumentException;
	
	// L�schen aller Umfrageeintr�ge anhand der PresentationID.
	public void deleteAllSurveyEntryByPresentationID(Presentation p) throws IllegalArgumentException;
	

	// Vote Methoden
	
	// Einen Vote erstellen.
	public Vote createVote(Vote v)
		throws IllegalArgumentException;
	
	// Einen Vote anhand der VoteID suchen.
	public Vote getVoteByVoteID(Vote v) throws IllegalArgumentException;
	
	// Einen Vote anhand der UserID suchen.
	public Vector<Vote> getAllVoteByUserID(User u) throws IllegalArgumentException;
	
	// Alle Votes anhand einer UmfrageeintragID suchen.
	public Vector<Vote> getAllVoteBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException;
	
	// Aktualisieren des Vote Objekts.
	public Vote updateVote(Vote upVote) throws IllegalArgumentException;
	
	// L�schen des Vote anhand der VoteID.
	public void deleteByVoteID(Vote v) throws IllegalArgumentException;
	
	// L�schen aller Votes anhand der UmfrageeintragID.
	public void deleteAllBySurveyEntryID(Vote v) throws IllegalArgumentException;

	
	
	
	/*
	
	public Presentation getPresentation() throws IllegalArgumentException;
	
	public void setPresentation(Presentation p) throws IllegalArgumentException;
	public void delete(Presentation p) throws IllegalArgumentException;
	*/
}