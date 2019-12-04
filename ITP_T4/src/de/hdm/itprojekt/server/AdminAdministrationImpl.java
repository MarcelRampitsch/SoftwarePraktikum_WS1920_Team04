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
	}
	
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
	public void deleteCinema (int cinemaID) throws IllegalArgumentException {
		cMapper.deleteCinemaByCinemaID(cinemaID);
	}
	// Methode um alle Cinema eines User zu finden
	public Vector<Cinema> findAllCinemaByUser(int userID) throws IllegalArgumentException{
		Window.alert("Wir sind in der IMPL");
		Vector<Cinema> rs = new Vector<Cinema>();
		rs = cMapper.findallCinemabyUserID(userID);
		return rs;
	}
	public Vector<Cinema> findAllCinemaByCinemaGroupID(int cinemaGroupID) throws IllegalArgumentException{
		Vector<Cinema> rs = cMapper.findAllCinemaByCinemaGroupID(cinemaGroupID);
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

