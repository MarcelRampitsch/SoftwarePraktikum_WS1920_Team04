package de.hdm.itprojekt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.client.Window;

import de.hdm.itprojekt.shared.EditorAdministration;
import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Vote;

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
	// Suchen von User Objekten deren E-Mail Adresse bekannt ist.
	public User getUserByEmail(User u) throws IllegalArgumentException{
		User user = uMapper.findByEmail(u);
		return user;
	}
	// Methode um alle Gruppen eines User zu finden.
	public Vector<Group> getAllGroupnameByUserID (User u) throws IllegalArgumentException{
		Vector<Group> gr = null;
		gr = gMapper.findAllGroupnameByUserID(u);
		return gr;
	}
	// Methode zur Aktualisierung eines User Objektes.
	public User updateUser(User upUser) throws IllegalArgumentException{
		if (upUser != null) {
			User tempUser = uMapper.updateUser(upUser);
			return tempUser;
		}
		return null;
		}
	// Methode zum L�schen eines bestimmten Users
	// TODO: Besprechen der deleteAllBy-Methoden.
	public void deleteUser(User u) throws IllegalArgumentException{
		uMapper.deleteUserByUserID(u.getId());
		//gMapper.deleteAllByUserID(u.getId());
		gmMapper.deleteAllByUserID(u.getId());
		pMapper.deleteAllByUserID(u.getId());
		sMapper.DeleteAllByGroupID(u.getId());
		//seMapper.
		vMapper.deleteAllVoteByUserID(u.getId());
		//cgMapper.deleteAllCinemaGroupByUserID(u.getId());
		//cMapper.deleteAllCinemaByUserID(u.getId());
		//mMapper.deleteAllByUserID(u.getId());
	}
	
	// TODO : User 1?
	// Methode um alle Cinema eines User zu finden
	public Vector<Cinema> findAllCinemaByUser1(User u) throws IllegalArgumentException{
		Vector<Cinema> rs = new Vector<Cinema>();
		rs = cMapper.findallCinemabyUserID(u);
		return rs;
	}

	// Group
	
	public Group createGroup (Group group) {
		Group g = new Group();
		g= gMapper.insertGroup(group);
		return g;
	}

	public Group getGroupByGroupID(Group g) throws IllegalArgumentException {
		Group group = gMapper.findByGroupID(g);
		return group;
	}

	public Vector<Group> getAllGroupByUserID(User u) throws IllegalArgumentException {
		Vector<Group> rs = gMapper.findAllByUserID(u);
		return rs;	
	}

	public Group updateGroup(Group updateg) throws IllegalArgumentException {
		if (updateg != null) {
			Group tempGroup = null;
			tempGroup = gMapper.updateGroup(tempGroup);
			return tempGroup;
		}
		return null;
	}
	
	public void deleteByGroupID(Group g) throws IllegalArgumentException {
		gMapper.deleteByGroupID(g);
		
	}

	public void deleteAllByUserID(User u) throws IllegalArgumentException {
		gMapper.deleteAllByUserID(u);
		
	}
	
	// Groupmember
	// TODO Wie hat die createGroupMember Methode auszusehen?
	
	/*public Groupmember createGroupMember(Groupmember gm) throws IllegalArgumentException {
		if(gm != null) {
		gm = gmMapper.insertGroupmember(gm);
			return gm;
		}
		return null;
	}
*/
	public Vector<Groupmember> getAllByGroupID(Group g) throws IllegalArgumentException {
		Vector<Groupmember> gm = gmMapper.findAllByGroupID(g.getId());
		return gm;
	}

	public Vector<Groupmember> getAllByUserID(User u) throws IllegalArgumentException {
		Vector<Groupmember> gm = gmMapper.findAllByUserID(u.getId());
		return gm;
	}
	public Groupmember updateGroupmember(Groupmember updateGm) throws IllegalArgumentException {
		if (updateGm != null) {
			Groupmember tempGroupmember = gmMapper.updateGroupmember(updateGm);
			return tempGroupmember;
		}
		return null;
	}
	// Methode um ein Gruppenmitglied anhand seiner ID zu l�schen.
	public void deleteByID(Groupmember gm) throws IllegalArgumentException {
		gmMapper.deleteByID(gm.getId());
		
	}

	public void deleteAllByGroupID(Groupmember gm) throws IllegalArgumentException {
		gmMapper.deleteAllByGroupID(gm.getGroupID());
		
	}

	public void deleteAllByUserID(Groupmember gm) throws IllegalArgumentException {
		gmMapper.deleteAllByUserID(gm.getUserID());
		
	}

	public Survey createSurvey(Survey s) throws IllegalArgumentException {
		if (s != null) {
			Survey tempSurvey = new Survey();
			tempSurvey = sMapper.insert(s);
			return tempSurvey;
		}
		return null;
	}

	public Survey getSurveyBySurveyID(Survey s) throws IllegalArgumentException {
		Survey survey = sMapper.findBySurveyID(s.getId());
		return survey;
	}

	public Survey getSurveyBySurveyName(Survey s) {
		Survey survey = sMapper.findByName(s.getName());
		return survey;
	}

	@Override
	public Survey updateSurvey(Survey updateS) throws IllegalArgumentException {
		if (updateS != null) {
			Survey tempSurvey = sMapper.updateSurvey(updateS);
			return tempSurvey;
		}
		return null;
	}

	@Override
	public void deleteBySurveyID(Survey s) throws IllegalArgumentException {
		sMapper.deleteSurveyBySurveyID(s.getId());
		
	}

	@Override
	public void deleteAllByGroupID(Survey s) throws IllegalArgumentException {
		sMapper.DeleteAllByGroupID(s.getGroupID());
		
	}

	@Override
	public void deleteAllByUserID(Survey s) throws IllegalArgumentException {
		sMapper.deleteAllByUserID(s.getUserID());
		
	}

	@Override
	public SurveyEntry createSurveyEntry(SurveyEntry se) throws IllegalArgumentException {
		if (se != null) {
			SurveyEntry tempSurveyentry = new SurveyEntry();
			tempSurveyentry = seMapper.insert(se);
			return tempSurveyentry;
		}
		return null;
	}

	@Override
	public SurveyEntry getSurveyEntryBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException {
		SurveyEntry surveyentry = seMapper.findSurveyEntryBySurveyEntryID(se.getId());
		return surveyentry;
	}

	@Override
	public SurveyEntry getSurveyEntryBySurveyID(SurveyEntry se) throws IllegalArgumentException {
		SurveyEntry surveyentry = seMapper.findSurveyEntryBySurveyID(se.getId());
		return surveyentry;
	}

	@Override
	public void updateSurveyEntry(SurveyEntry upSurveyEntry) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBySurveyEntryID(SurveyEntry se) throws IllegalArgumentException {
		seMapper.deleteSurveyEntryBySurveyEntryID(se.getId());
		
	}

	@Override
	public void deleteAllBySurveyID(SurveyEntry se) throws IllegalArgumentException {
		seMapper.deleteAllBySurveyID(se.getId());
		
	}

	@Override
	public void deleteAllByPresentationID(SurveyEntry se) throws IllegalArgumentException {
		seMapper.deleteAllByPresentationID(se.getId());
		
	}
}
