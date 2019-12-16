package de.hdm.itprojekt.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.gwt.user.client.Window;

import de.hdm.itprojekt.shared.EditorAdministration;
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
	
	
	public User createUser(User u) {
		if (u!= null) {
			User tempUser = new User();
			tempUser = uMapper.insert(u);
			return tempUser;
		}
		return null;
	}
	public User getUserByNickname(User u) throws IllegalArgumentException{
		User user = uMapper.findByNickname(u);
		return user;
	}
	// Suchen von User Objekten deren E-Mail Adresse bekannt ist.
	public User getUserByEmail(User u) throws IllegalArgumentException{
		User user = uMapper.findByEmail(u);
		return user;
	}
	
	public Vector<Group> getAllGroupnameByUserID (User u) throws IllegalArgumentException{
		Vector<Group> gr = null;
		gr = gMapper.findAllGroupnameByUserID(u);
		return gr;
	}
	
	public User updateUser(User upUser) throws IllegalArgumentException{
		if (upUser != null) {
			User tempUser = uMapper.updateUser(upUser);
			return tempUser;
		}
		return null;
		}
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
/*
	@Override
	public Survey createSurvey(String name) throws IllegalArgumentException {
		if (name != null) {
			Survey s = new Survey();
			s.setName();
			s.setId(1);
			return this.sMapper.insert(s);
		}
		return null;
	}
	*/
}
