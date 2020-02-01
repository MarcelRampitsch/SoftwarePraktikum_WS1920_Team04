package de.hdm.itprojekt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.itprojekt.shared.EditorAdministration;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Vote;
import de.hdm.itprojekt.shared.bo.Timeslot;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.server.db.GroupMapper;
import de.hdm.itprojekt.server.db.GroupmemberMapper;
import de.hdm.itprojekt.server.db.PresentationMapper;
import de.hdm.itprojekt.server.db.SurveyEntryMapper;
import de.hdm.itprojekt.server.db.SurveyMapper;
import de.hdm.itprojekt.server.db.UserMapper;
import de.hdm.itprojekt.server.db.VoteMapper;
import de.hdm.itprojekt.server.db.CinemaGroupMapper;
import de.hdm.itprojekt.server.db.CinemaMapper;
import de.hdm.itprojekt.server.db.MovieMapper;
import de.hdm.itprojekt.server.db.TimeslotMapper;


/*
 * @author MarcelRampitsch
 * EditorAdministrationImpl bildet die Aplikationslogik aller anwenderseitigen Funktionen ab.
 */
public class EditorAdministrationImpl extends RemoteServiceServlet implements EditorAdministration {
	
	private GroupMapper gMapper = null;
	private GroupmemberMapper gmMapper = null;
	private PresentationMapper pMapper = null;
	private SurveyEntryMapper seMapper = null;
	private SurveyMapper sMapper = null;
	private UserMapper uMapper = null;
	private VoteMapper vMapper = null;
	private CinemaGroupMapper cgMapper = null;
	private CinemaMapper cMapper = null;
	private MovieMapper mMapper = null;
	private TimeslotMapper tMapper = null;
	
//	Default-Konstruktor
	public EditorAdministrationImpl() throws IllegalArgumentException {
	}
	
	public void init() throws IllegalArgumentException{	

		this.gMapper = GroupMapper.GroupMapper();
		this.gmMapper = GroupmemberMapper.GroupmemberMapper();
		this.pMapper = PresentationMapper.PresentationMapper();
		this.seMapper = SurveyEntryMapper.SurveyEntryMapper();
		this.sMapper = SurveyMapper.SurveyMapper();
		this.uMapper = UserMapper.UserMapper();
		this.vMapper = VoteMapper.VoteMapper();
		this.cgMapper = CinemaGroupMapper.CinemaGroupMapper();
		this.cMapper = CinemaMapper.CinemaMapper();
		this.mMapper = MovieMapper.MovieMapper();
		this.tMapper = TimeslotMapper.TimeslotMapper();
	}
	
	
	// User
	
	// Methode zur Erstellung eines User Objektes.
	public User createUser(User u) {
		if (u!= null) {
			User tempUser = new User();
			tempUser = uMapper.insert(u);
			return tempUser;
		}
		return null;
	}
	// Methode um einen User anhand des Nicknamen zu finden.
	public User getUserByNickname(User u) throws IllegalArgumentException{
		User user = uMapper.findByNickname(u);
		return user;
	}
	
	public User getUserByUserID(User u) throws IllegalArgumentException {
		User user  =uMapper.findByUserID(u);
		return user;
	}
	
	// Suchen von User Objekten deren E-Mail Adresse bekannt ist.
	public User getUserByEmail(User u) throws IllegalArgumentException{
		User user = uMapper.findByEmail(u);
		return user;
	}
	// Methode zur Aktualisierung eines User Objektes.
	public User updateUser(User upUser) throws IllegalArgumentException{
		if (upUser != null) {
			User tempUser = uMapper.updateUser(upUser);
			return tempUser;
		}
		return null;
	}
	// Methode zum L�schen eines bestimmten Users und der damit verbundenen Objekte.
	public void deleteUser(User u) throws IllegalArgumentException{
		Vector <Group> temp= new Vector<Group>();
		vMapper.deleteAllVoteByUserID(u);
		temp = getAllGroupByUserID(u);
		for (int i = 0; i < temp.size(); i++) {
			deleteAllGroupmemberByGroupID(temp.elementAt(i));
			deleteGroupByGroupID(temp.elementAt(i));
		}
		pMapper.deleteAllByUserID(u);
		gmMapper.deleteAllGroupmemberByUserID(u);
		cMapper.deleteAllCinemaByUserID(u);
		cgMapper.deleteAllCinemaGroupByUserID(u);
		mMapper.deleteAllByUserID(u);
		tMapper.deleteAllTimeslotByUserID(u);
		uMapper.deleteUserByUserID(u);
	}
	// Methode um alle Kinogruppen eines Users zu finden
		public Vector<CinemaGroup> getAllCinemaGroupByUser(User u) throws IllegalArgumentException{
			
			Vector<CinemaGroup> rs = new Vector<CinemaGroup>();
			rs = cgMapper.findAllCinemaGroupByUserID(u);
			return rs;
		}
	// Methode um alle Cinema eines User zu finden
		public Vector<Cinema> getAllCinemaByUser(User u) throws IllegalArgumentException{
			
			Vector<Cinema> rs = new Vector<Cinema>();
			rs = cMapper.findallCinemabyUserID(u);
			return rs;
		}
		// Methode um alle Filme eines Users zu finden
		public Vector<Movie> getAllMovieByUser(User u) throws IllegalArgumentException{
			Vector<Movie> rs = new Vector<Movie>();
			rs = mMapper.findAllByUserID(u);
			return rs;
		}
		// Methode um alle Timeslots eines Users zu finden
		public Vector<Timeslot> getAllTimeslotByUser(User u) throws IllegalArgumentException{
			Vector<Timeslot> rs = new Vector<Timeslot>();
			rs = tMapper.findAllTimeslotByUserID(u);
			return rs;
		}
		// Methode um alle Vorstellungen anzuzeigen, die den eingegebenen Suchkriterien entsprechen
		public Vector<Presentation> getAllPresentationBySearchCriteria(Presentation p) throws IllegalArgumentException{
			Vector<Presentation> rs = new Vector<Presentation>();
			rs = pMapper.getAllPresentationBySearchCriteria(p);
			return rs;
		}
		// Methode um alle Pr�sentationen anhand des Umfrageeintrags zu finden
		public Cinema getCinemaBySurveyEntry(SurveyEntry se) throws IllegalArgumentException{
			Presentation rs = new Presentation();
			rs = pMapper.getAllPresentationByPresentationID(se);
			Cinema c = new Cinema(rs.getCinemaID(),null,"","",0,0);
			c = cMapper.findCinemaByCinemaID(c);
			return c;
		}
		
		// Methode um alle Pr�sentationen anhand des Umfrageeintrags zu finden
		public CinemaGroup getCinemaGroupBySurveyEntry(SurveyEntry se) throws IllegalArgumentException{
			Presentation rs = new Presentation();
			rs = pMapper.getAllPresentationByPresentationID(se);
			Cinema c = new Cinema(rs.getCinemaID(),null,"","",0,0);
			c = cMapper.findCinemaByCinemaID(c);
			CinemaGroup cg = new CinemaGroup("", c.getCinemaGroupID());
			return cg;
		}
		
		public Movie getMovieBySurveyEntry(SurveyEntry se) throws IllegalArgumentException{
			Presentation rs = new Presentation();
			rs = pMapper.getAllPresentationByPresentationID(se);
			Movie m = new Movie(rs.getMovieID(),null,"",0);
			m = mMapper.findByMovieID(m);
			return m;
		}
		
		public Timeslot getTimeslotBySurveyEntry(SurveyEntry se) throws IllegalArgumentException{
			Presentation rs = new Presentation();
			rs = pMapper.getAllPresentationByPresentationID(se);
			Timeslot t = new Timeslot("",0,rs.getTimeslotID(),null);
			t = tMapper.findByTimeslotID(t);
			return t;
		}
		
		public Presentation getPresentationBySurveyEntry(SurveyEntry se) throws IllegalArgumentException{
			Presentation rs = new Presentation();
			rs = pMapper.getAllPresentationByPresentationID(se);
			return rs;
		}

	
	// Group
	
	public Group createGroup (Group g) {
		if(g != null) {
			Group tempGroup = null;
			tempGroup = gMapper.insertGroup(g);
			return tempGroup;
		}
		return null;
	}

	public Group getGroupByGroupID(Group g) throws IllegalArgumentException {
		Group group = gMapper.findByGroupID(g);
		return group;
	}

	public Vector<Group> getAllGroupByUserID(User u) throws IllegalArgumentException {
		Vector<Group> rs = gMapper.findAllByUserID(u);
		return rs;	
	}

	public Group updateGroup(Group g) throws IllegalArgumentException {
		if (g != null) {
			Group group1 = gMapper.updateGroup(g);
		//	tempGroup = gMapper.updateGroup(tempGroup);
			return group1;
		}
		return null;
	}
	
	public void deleteGroupByGroupID(Group g) throws IllegalArgumentException {
		Vector <Survey> survey = new Vector<Survey>();
		survey = getAllSurveyByGroupID(g);
		for (int i = 0; i < survey.size() ; i++) {
			deleteSurveyBySurveyID(survey.elementAt(i));	
		}
		gMapper.deleteByGroupID(g);
	}
		
	
	// Groupmember
	// TODO Wie hat die createGroupMember Methode auszusehen?
	
	public Groupmember createGroupmember(Groupmember gm) throws IllegalArgumentException {
		if(gm != null) {
		gm = gmMapper.insertGroupmember(gm);
			return gm;
		}
		return null;
	}

	public Vector<Groupmember> getAllGroupmemberByGroupID(Group g) throws IllegalArgumentException {
		Vector<Groupmember> gm = gmMapper.findAllByGroupID(g);
		return gm;
	}

	public Vector<Groupmember> getAllGroupmemberByUserID(User u) throws IllegalArgumentException {
		Vector<Groupmember> gm = gmMapper.findAllByUserID(u);
		return gm;
	}
	
	public Vector<Group> getAllGroupsIamMemberFrom(User u) throws IllegalArgumentException {
	//	System.out.println(u.getId());
		Vector<Groupmember> gm = gmMapper.findAllByUserID(u);
		Vector <Group> groups = new Vector<Group>();
		for(Groupmember g: gm) {
			Group ng = new Group("",g.getGroupID());
		//	System.out.println(ng.getId());
			ng = gMapper.findByGroupID(ng);
		//	System.out.println(ng.getName());
		   groups.add(ng);
		}
		//System.out.println(groups.size());
		return groups;
		
	}
	
	public Groupmember updateGroupmember(Groupmember updateGm) throws IllegalArgumentException {
		if (updateGm != null) {
			Groupmember tempGroupmember = gmMapper.updateGroupmember(updateGm);
			return tempGroupmember;
		}
		return null;
	}

	public void deleteGroupmemberByGroupmemberID(Groupmember gm) throws IllegalArgumentException {
		gmMapper.deleteGroupmemberByGroupmemberID(gm);
		
	}

	public void deleteAllGroupmemberByGroupID(Group g) throws IllegalArgumentException {
		gmMapper.deleteAllGroupmemberByGroupID(g);
		
	}

	
	// Survey
	
	public Survey createSurvey(Survey s) throws IllegalArgumentException {
		if (s != null) {
			Survey tempSurvey = new Survey();
			tempSurvey = sMapper.insert(s);
			return tempSurvey;
		}
		return null;
	}
	
	public Vector<Survey> getAllSurveyByGroupID(Group g) throws IllegalArgumentException {
		Vector<Survey> survey = sMapper.findAllSurveyByGroup(g);
		return survey;
	}

	public Survey getSurveyBySurveyID(Survey s) throws IllegalArgumentException {
		Survey survey = sMapper.findBySurveyID(s);
		return survey;
	}

	public Survey getSurveyBySurveyName(Survey s) {
		Survey survey = sMapper.findByName(s);
		return survey;
	}

	public Survey updateSurvey(Survey updateS) throws IllegalArgumentException {
		if (updateS != null) {
			Survey tempSurvey = sMapper.updateSurvey(updateS);
			return tempSurvey;
		}
		return null;
	}

	public void deleteSurveyBySurveyID(Survey s) throws IllegalArgumentException {
		Vector<SurveyEntry> rs = seMapper.findAllSurveyEntryBySurveyID(s);
		for (int i = 0; i < rs.size(); i++) {
			vMapper.deleteAllVoteBySurveyEntryID(rs.elementAt(i));
		}
		seMapper.deleteAllBySurveyID(s);
		sMapper.deleteSurveyBySurveyID(s);
		
	}

	public void deleteAllSurveyByGroupID(Group g) throws IllegalArgumentException {
		sMapper.deleteAllSurveyByGroupID(g);
		
	}

	public void deleteAllSurveyByUserId(User u) throws IllegalArgumentException {
		sMapper.deleteAllByUserID(u);
		
	}


	// SurveyEntry
	
	public SurveyEntry createSurveyEntry(SurveyEntry se) throws IllegalArgumentException {
		if (se != null) {
			SurveyEntry tempSurveyentry = new SurveyEntry();
			tempSurveyentry = seMapper.insert(se);
			return tempSurveyentry;
		}
		return null;
	}

	public SurveyEntry getSurveyEntryBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException {
		SurveyEntry surveyentry = seMapper.findSurveyEntryBySurveyEntryID(se);
		return surveyentry;
	}

	public Vector <SurveyEntry> getAllSurveyEntryBySurveyID(Survey s) throws IllegalArgumentException {
		Vector <SurveyEntry> surveyentry = seMapper.findAllSurveyEntryBySurveyID(s);
		return surveyentry;
	}

	public SurveyEntry updateSurveyEntry(SurveyEntry upSurveyEntry) throws IllegalArgumentException {
		if (upSurveyEntry != null) {
			SurveyEntry tempSurveyEntry = new SurveyEntry();
			tempSurveyEntry = seMapper.update(upSurveyEntry);
			return tempSurveyEntry;
		}
		return null;	
	}

	public void deleteSurveyEntryBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException {
		seMapper.deleteSurveyEntryBySurveyEntryID(se);	
	}

	public void deleteAllSurveyEntryBySurveyID(Survey s) throws IllegalArgumentException {
		seMapper.deleteAllBySurveyID(s);	
	}

	public void deleteAllSurveyEntryByPresentationID(Presentation p) throws IllegalArgumentException {
		seMapper.deleteAllByPresentationID(p);	
	}

	// Vote
	
	public Vote createVote(Vote v) throws IllegalArgumentException {
		if (v != null) {
			Vote tempvote = new Vote();
			tempvote = vMapper.insert(v);
			return tempvote;
		}
		Vote v1 = getVoteResult(new SurveyEntry(v.getSurveyEntryID(),null,0,0));
		return v1;
	}

	public Vote getVoteByVoteID(Vote v) throws IllegalArgumentException {
		Vote vote = vMapper.findVoteByVoteID(v.getId());
		return vote;
	}

	public Vector<Vote> getAllVoteByUserID(User u) throws IllegalArgumentException {
		Vector<Vote> rs = vMapper.findVoteByUserID(u.getId());
		return rs;
	}

	public Vector<Vote> getAllVoteBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException {
		Vector<Vote> rs = vMapper.findAllVoteBySurveyEntryID(se.getId());
		return rs;
	}
	
	public Vote getVoteResult(SurveyEntry se)  throws IllegalArgumentException {
		Vector<Vote> rs = vMapper.findAllVoteBySurveyEntryID(se.getId());
		Vote v = new Vote();
		v.setSurveyEntryID(se.getId());
		for(int i=0; i>rs.size(); i++) {
			v.setVoteResult(v.getVoteResult()+rs.elementAt(i).getVoteResult());
		}
		return v;

	}

	public Vote updateVote(Vote upVote) throws IllegalArgumentException {
		if (upVote != null) {
			Vote tempVote = vMapper.update(upVote);
			return tempVote;
		}
		return null;
		
	}

	public void deleteByVoteID(Vote v) throws IllegalArgumentException {
		vMapper.deleteVoteByVoteID(v.getId());
		
	}

	public void deleteAllBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException {
		vMapper.deleteAllVoteBySurveyEntryID(se);
		
	}

	@Override
	public Vector<Cinema> findAllCinemaByUser1(User u) throws IllegalArgumentException {
		Vector<Cinema> rs = new Vector<Cinema>();
		rs = cMapper.findallCinemabyUserID(u);
		return rs;
	}
	
	public Vector<SurveyEntry> secondVoteRound(Vector <SurveyEntry> se) throws IllegalArgumentException{
		SurveyEntry temp = new SurveyEntry();
		Vector<SurveyEntry> newSe = new Vector<SurveyEntry>();
		Vector<SurveyEntry> topVotes = new Vector<SurveyEntry>();
		Vector<Vote> vote = new Vector<Vote>();
		int g;
		
		for (int i = 0; i < se.size(); i++) {
			g = 0;
			vote = vMapper.findAllVoteBySurveyEntryID(se.elementAt(i).getId());
			for (int j = 0; j < vote.size(); j++) {
				g+=vote.elementAt(j).getVoteResult();
			}
			SurveyEntry entry = new SurveyEntry(se.elementAt(i).getId(), se.elementAt(i).getSurveyID(),se.elementAt(i).getPresentationID(), g);
			newSe.add(entry);
		}
		
		for (int i = 0; i < 2; i++) {
			temp.setResult(-100);;
			for (int j = 0; j < newSe.size(); j++) {
				if(newSe.elementAt(j).getResult()>temp.getResult()) {
					temp = newSe.elementAt(j);
				}
			}
			newSe.remove(temp);
			topVotes.add(temp);
		}
		if(topVotes.elementAt(0).getId()==topVotes.elementAt(1).getId()) {
			topVotes.remove(1);
			}
		
		for (int i = 0; i < newSe.size(); i++) {
			vMapper.deleteAllVoteBySurveyEntryID(newSe.elementAt(i));
			seMapper.deleteSurveyEntryBySurveyEntryID(newSe.elementAt(i));
		}
		
		for (int i = 0; i < topVotes.size(); i++) {
			vMapper.deleteAllVoteBySurveyEntryID(topVotes.elementAt(i));
		}
		
		Survey survey = new Survey(se.elementAt(0).getSurveyID());
		Survey s = sMapper.findBySurveyID(survey);
		s.setRound(2);
		survey = sMapper.updateSurvey(s);
		
		return topVotes;
	}

	@Override
	public Vector<Cinema> findAllCinema() throws IllegalArgumentException {
		Vector<Cinema> rs = new Vector<Cinema>();
		rs = cMapper.findallCinema();
		return rs;
	}
	
	public Vector<Movie> findAllMovie() throws IllegalArgumentException {
		Vector<Movie> rs = new Vector<Movie>();
		rs = mMapper.findAllMovie();
		return rs;
	}
	
	public Vector<Timeslot> findAllTimeslot() throws IllegalArgumentException {
		Vector<Timeslot> rs = new Vector<Timeslot>();
		rs = tMapper.findAllTimeslot();
		return rs;
	}
	
}