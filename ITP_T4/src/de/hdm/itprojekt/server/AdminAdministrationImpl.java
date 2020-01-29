package de.hdm.itprojekt.server;

import de.hdm.itprojekt.shared.AdminAdministration;
import de.hdm.itprojekt.shared.bo.*;

import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


import de.hdm.itprojekt.server.db.GroupMapper;
import de.hdm.itprojekt.client.Admin;
import de.hdm.itprojekt.server.db.CinemaGroupMapper;
import de.hdm.itprojekt.server.db.CinemaMapper;
import de.hdm.itprojekt.server.db.MovieMapper;
import de.hdm.itprojekt.server.db.PresentationMapper;
import de.hdm.itprojekt.server.db.SurveyEntryMapper;
import de.hdm.itprojekt.server.db.TimeslotMapper;
import de.hdm.itprojekt.server.db.UserMapper;
import de.hdm.itprojekt.server.db.VoteMapper;


/**
 * The server-side implementation of the RPC service.
 */

public class AdminAdministrationImpl extends RemoteServiceServlet implements AdminAdministration {
	
	private CinemaGroupMapper cgMapper = null;
	private CinemaMapper cMapper = null;
	private MovieMapper mMapper = null;
	private PresentationMapper pMapper = null;
	private TimeslotMapper tMapper = null;
	private UserMapper uMapper = null; 
	private GroupMapper gMapper = null;
	private SurveyEntryMapper seMapper = null;
	private VoteMapper vMapper = null;

    //	Default-Konstruktor
	public AdminAdministrationImpl() throws IllegalArgumentException{
	}
	
	public void init() throws IllegalArgumentException {
		
		this.cgMapper = CinemaGroupMapper.CinemaGroupMapper();
		this.cMapper = CinemaMapper.CinemaMapper();
		this.mMapper = MovieMapper.MovieMapper();
		this.pMapper = PresentationMapper.PresentationMapper();
		this.tMapper = TimeslotMapper.TimeslotMapper();
		this.uMapper = UserMapper.UserMapper();
		this.gMapper = GroupMapper.GroupMapper();
		this.seMapper = SurveyEntryMapper.SurveyEntryMapper();
		this.vMapper = VoteMapper.VoteMapper();
	}
	
	// Cinema
	// Methode zur Erstellung eines Cinema Objektes
	public Cinema addCinema(Cinema c) {
		if (c != null) {
			Cinema tempCinema = new Cinema();
			tempCinema = cMapper.insertCinema(c);
			return tempCinema;
		}
		return null;
	}
	// Methode zur Aktualisierung eines Cinema Objektes
	public Cinema updateCinema(Cinema upCinema) throws IllegalArgumentException {
		if (upCinema != null) {
			Cinema tempCinema = cMapper.updateCinema(upCinema);
			return tempCinema;
		}
		return null;
	}	
	// Methode zum Löschen eines bestimmten Kinos
	public void deleteCinema (Cinema c) throws IllegalArgumentException {
		Vector <Presentation> rs = pMapper.findAllByCinemaID(c);
		for (int i = 0; i < rs.size(); i++) {
			seMapper.deleteAllByPresentationID(rs.elementAt(i));
		}
		pMapper.deleteAllByCinemaID(c);
		cMapper.deleteCinemaByCinemaID(c.getId());
	}
	
	// Methode um alle Cinema eines User zu finden
	public Vector<Cinema> findAllCinemaByUser(User u) throws IllegalArgumentException{
		
		Vector<Cinema> rs = new Vector<Cinema>();
		rs = cMapper.findallCinemabyUserID(u);
		return rs;
	}
	public Vector<Cinema> findAllCinemaByCinemaGroupID(Cinema c) throws IllegalArgumentException{
		Vector<Cinema> rs = cMapper.findAllCinemaByCinemaGroupID(c.getCinemaGroupID());
		return rs;
	}
		
	// User
	public User getUserbyNickname (User u) throws IllegalArgumentException{
		User user = uMapper.findByNickname(u);
		return user;
	}
	
	public User getUserbyEmail (User u) throws IllegalArgumentException{
		User currentUser = uMapper.findByEmail(u);
		return currentUser;
	}
	
	// CinemaGroup
	
	public CinemaGroup addCinemaGroup (CinemaGroup cg) throws IllegalArgumentException{
		if(cg != null) {
			CinemaGroup cineg = null;
			cineg = cgMapper.insert(cg);
			return cineg;
		}
		return null;
	}
	
	public CinemaGroup updateCinemaGroup (CinemaGroup cg) throws IllegalArgumentException{
		if(cg != null) {
			CinemaGroup cineg = cgMapper.updateCinemaGroup(cg);
			return cineg;
		}
		return null;
	}
	
	public void deleteCinemaGroup (CinemaGroup cg) throws IllegalArgumentException{
		Vector<Cinema> rs = cMapper.findAllCinemaByCinemaGroupID(cg.getId());
		for (int i = 0; i < rs.size(); i++) {
			rs.elementAt(i);
			Cinema c = new Cinema(rs.elementAt(i).getId(), rs.elementAt(i).getCreationDate(),rs.elementAt(i).getLocation(),rs.elementAt(i).getName(),0, rs.elementAt(i).getUserID());
			cMapper.updateCinema(c);
		}
		cgMapper.deleteCinemaGroupByID(cg.getId());
	}
	
	public Vector<CinemaGroup> getAllCinemaGroupByUserID(User u) throws IllegalArgumentException{
		Vector<CinemaGroup> rs = cgMapper.findcinemagroupbyuserID(u);
		return rs;
	}
	
	// Movie
	public Movie addMovie(Movie m) throws IllegalArgumentException{
		if(m != null) {
			Movie mo = null;
			mo = mMapper.insert(m);
			return mo;
		}
		return null;
	}
	
	public Movie updateMovie (Movie updateM) throws IllegalArgumentException{
		if(updateM != null) {
			Movie m = null;
			m = mMapper.updateMovie(updateM);
			return m;
		}
		return null;
	}		
	
	public void deleteMovie(Movie m) throws IllegalArgumentException{
		Vector<Presentation> rs = pMapper.findAllByMovieID(m);
		for (int i = 0; i < rs.size(); i++) {
			seMapper.deleteAllByPresentationID(rs.elementAt(i));
		}
		pMapper.deleteAllByMovieID(m);
		mMapper.deleteByMovieID(m);
	}
	
	public Vector<Movie> getAllMovieByUserID(User u) throws IllegalArgumentException{
		Vector<Movie> re = mMapper.findAllByUserID(u);
		return re;
	}
	
	public Vector<Timeslot> getAllTimeslotByUserID(User u) throws IllegalArgumentException {
		Vector<Timeslot> te = tMapper.findAllTimeslotByUserID(u);
		return te;
	}

	public void deleteTimeslot(Timeslot t) throws IllegalArgumentException {
		Vector <Presentation> rs = pMapper.findAllByTimeslotID(t);
		for (int i = 0; i < rs.size(); i++) {
			seMapper.deleteAllByPresentationID(rs.elementAt(i));
		}
		pMapper.deleteAllByTimeslotID(t);
		tMapper.deleteByTimeslotID(t);
	}

	public Timeslot addTimeslot(Timeslot t) throws IllegalArgumentException {
		if(t != null) {
			Timeslot te = null;
			te = tMapper.insert(t);
			return te;
		}
		return null;
	}

	public Timeslot updateTimeslot(Timeslot t) throws IllegalArgumentException {
		if(t != null) {
			Timeslot te = null;
			te = tMapper.update(t);
			return te;
		}
		return null;
	}

	


	public void deletePresentation(Presentation p) throws IllegalArgumentException {
		Vector <SurveyEntry> rs = new Vector<SurveyEntry>();
		rs = seMapper.findAllSurveyEntryByPresentationID(p);
		for (int i = 0; i < rs.size(); i++) {
			vMapper.deleteAllVoteBySurveyEntryID(rs.elementAt(i));
		}
		seMapper.deleteAllByPresentationID(p);
		pMapper.deleteByPresentationID(p);	
	}

	
	public Vector<Presentation> getAllPresentationsByUser(User u) throws IllegalArgumentException {
		Vector<Presentation> pres = pMapper.findAllByUserID(u);
		return pres;
	}
	
	public Presentation addPresentation(Presentation p) throws IllegalArgumentException {
		if(p != null) {
			Presentation pr = null;
			pr = pMapper.insert(p);
			return pr;
		}
		return null;
	}
	
	public Presentation updatePresentation(Presentation p) throws IllegalArgumentException{
		if(p != null) {
			Presentation pr = null;
			pr = pMapper.update(p);
			return pr;
		}
		return null;
	}
	
	public Vector<Group> getAllGroupByUserID(User u) throws IllegalArgumentException {
		Vector<Group> rs = gMapper.findAllByUserID(u);
		return rs;	
	}


	/*@Override
	public Cinema greetServer(String input) throws IllegalArgumentException {

		try {
			CinemaMapper.CinemaMapper().insert(input);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	} */
}

