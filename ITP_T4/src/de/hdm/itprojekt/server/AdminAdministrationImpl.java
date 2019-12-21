package de.hdm.itprojekt.server;

import de.hdm.itprojekt.shared.AdminAdministration;
import de.hdm.itprojekt.shared.bo.*;

import java.sql.SQLException;
import java.util.Vector;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.server.db.CinemaGroupMapper;
import de.hdm.itprojekt.server.db.CinemaMapper;
import de.hdm.itprojekt.server.db.MovieMapper;
import de.hdm.itprojekt.server.db.PresentationMapper;
import de.hdm.itprojekt.server.db.TimeslotMapper;
import de.hdm.itprojekt.server.db.UserMapper;


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
	


	public AdminAdministrationImpl() throws IllegalArgumentException{
	}
	
	public void init() throws IllegalArgumentException {
		
		this.cgMapper = CinemaGroupMapper.CinemaGroupMapper();
		this.cMapper = CinemaMapper.CinemaMapper();
		this.mMapper = MovieMapper.MovieMapper();
		this.pMapper = PresentationMapper.PresentationMapper();
		this.tMapper = TimeslotMapper.TimeslotMapper();
		this.uMapper = UserMapper.UserMapper();
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

