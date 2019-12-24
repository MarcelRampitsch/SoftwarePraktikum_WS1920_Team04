package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;

@RemoteServiceRelativePath("admin")
public interface AdminAdministration extends RemoteService {

	void init();
	
	// Cinema
	Cinema addCinema(Cinema c) throws IllegalArgumentException;
	Cinema updateCinema(Cinema upCinema) throws IllegalArgumentException;
	void deleteCinema(Cinema c) throws IllegalArgumentException;
	Vector<Cinema> findAllCinemaByCinemaGroupID(Cinema c) throws IllegalArgumentException;
	Vector<Cinema> findAllCinemaByUser(User u) throws IllegalArgumentException;
	
	// User
	User getUserbyNickname(User u) throws IllegalArgumentException;
	User getUserbyEmail(User u) throws IllegalArgumentException;
	
	// Cinemagroup
	CinemaGroup addCinemaGroup(CinemaGroup cg) throws IllegalArgumentException;
	CinemaGroup updateCinemaGroup(CinemaGroup cg) throws IllegalArgumentException;
	void deleteCinemaGroup(CinemaGroup cg) throws IllegalArgumentException;
	Vector<CinemaGroup> getAllCinemaGroupByUserID(User u) throws IllegalArgumentException;
	
	// Movie
	Movie addMovie(Movie m) throws IllegalArgumentException;
	Movie updateMovie(Movie m) throws IllegalArgumentException;
	void deleteMovie(Movie m) throws IllegalArgumentException;
	Vector<Movie> getAllMovieByUserID(User u) throws IllegalArgumentException;
	
	Timeslot addTimeslot(Timeslot t) throws IllegalArgumentException;
	Timeslot updateTimeslot(Timeslot t) throws IllegalArgumentException;
	void deleteTimeslot (Timeslot t) throws IllegalArgumentException;
	Vector<Timeslot> getAllTimeslotByUserID(User u) throws IllegalArgumentException;

	Vector<Group> getAllGroupByUserID(User user);
	
//	Timeslot findByTimeslotID (Timeslot t)throws IllegalArgumentException;
//	Timeslot findByTime(Timeslot t) throws IllegalArgumentException;
//	void deleteByTimeslotID(Timeslot t) throws IllegalArgumentException;
//	void deleteAllTimeslotByUserID(User u)  throws IllegalArgumentException;
//	Vector<Timeslot> findAllTimeslotByUserID(User u) throws IllegalArgumentException;
//	void deleteAllTimeslotByMovieID(Movie m) throws IllegalArgumentException;
//
//	Timeslot addTimeslot(Timeslot t);
//
//	Timeslot updateTimeslot(Timeslot t);
	
	
//	Cinema greetServer(String input) throws IllegalArgumentException;

}
