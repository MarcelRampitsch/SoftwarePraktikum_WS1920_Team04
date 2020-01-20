package de.hdm.itprojekt.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
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

	// Timeslot
	Timeslot addTimeslot(Timeslot t) throws IllegalArgumentException;

	Timeslot updateTimeslot(Timeslot t) throws IllegalArgumentException;

	void deleteTimeslot(Timeslot t) throws IllegalArgumentException;

	Vector<Timeslot> getAllTimeslotByUserID(User u) throws IllegalArgumentException;

	// Presentation
	void deletePresentation(Presentation p) throws IllegalArgumentException;

	Vector<Presentation> getAllPresentationsByUser(User u) throws IllegalArgumentException;

	Presentation addPresentation(Presentation p) throws IllegalArgumentException;

	Presentation updatePresentation(Presentation p) throws IllegalArgumentException;
}
