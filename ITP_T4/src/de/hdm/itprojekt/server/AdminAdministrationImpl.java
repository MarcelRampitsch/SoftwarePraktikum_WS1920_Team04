package de.hdm.itprojekt.server;

import de.hdm.itprojekt.shared.AdminAdministration;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.itprojekt.server.db.DBConnection;
import de.hdm.itprojekt.server.db.CinemaGroupMapper;
import de.hdm.itprojekt.server.db.CinemaMapper;
import de.hdm.itprojekt.server.db.GroupMapper;
import de.hdm.itprojekt.server.db.GroupmemberMapper;
import de.hdm.itprojekt.server.db.MovieMapper;
import de.hdm.itprojekt.server.db.PresentationMapper;
import de.hdm.itprojekt.server.db.SurveyEntryMapper;
import de.hdm.itprojekt.server.db.SurveyMapper;
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


	public AdminAdministrationImpl() throws IllegalArgumentException{
		
	}
	public void init() throws IllegalArgumentException {
		
		this.cgMapper = CinemaGroupMapper.CinemaGroupMapper();
		this.cMapper = CinemaMapper.CinemaMapper();
		this.mMapper = MovieMapper.MovieMapper();
		this.pMapper = PresentationMapper.PresentationMapper();
		this.tMapper = TimeslotMapper.TimeslotMapper();
	}
}
