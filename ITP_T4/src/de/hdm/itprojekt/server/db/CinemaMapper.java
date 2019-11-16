package de.hdm.itprojekt.server.db;

public class CinemaMapper {
	
	
	private static CinemaMapper cinemaMapper =null;
	
	
	
	
	
	
	protected CinemaMapper() {
		
	}
	
	
	public static CinemaMapper CinemaMapper() {
		if (cinemaMapper == null) {
			cinemaMapper = new CinemaMapper();
		}
		return cinemaMapper;
	}
	
	
	
	
}
