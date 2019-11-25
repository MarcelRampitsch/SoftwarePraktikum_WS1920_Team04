package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Movie;

public class MovieMapper {
	
	/**
	 * Die Klasse MovieMapper bildet <code>Movie</code> Objekte auf eine
	 * relationale Datenbank ab. Ebenfalls ist es m�glich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static MovieMapper movieMapper =null;
	

	/**
	 * Die Klasse MovieMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erf�llt werden kann, wird
	 * zun�chst eine Variable mit dem Schl�sselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected MovieMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt daf�r, dass
	 * nur eine einzige Instanz der MovieMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit �ber MovieMapper.movieMapper() und nicht �ber den
	 * New-Operator.
	 * 
	 * @return Das <code/>MovieMapper<code/> Objekt.
	 */

	
	public static MovieMapper MovieMapper() {
		if (movieMapper == null) {
			movieMapper = new MovieMapper();
		}
		return movieMapper;
	}
	
	public Movie findByMovieID(int id){
		Movie m = null;
		
		// Aufbau der DB-Verbindung
		Connection con = DBConnection.getConnection();

		try {
		// Erstellung des Prepared Statement um alle Filme anhand der MovieID zufinden

			PreparedStatement findByMovieID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie WHERE movieID=? ;");
		findByMovieID.setInt(1,  id);
		
		// ausf�hren des Queries
		ResultSet rs = findByMovieID.executeQuery();
		
		m = new Movie(rs.getInt("movieID"), rs.getString("name"), rs.getTimestamp("creationDate"), rs.getInt("presentationID"), rs.getInt("userID"));
		//int id, Timestamp creationDate, String name, int userID Korrektur
		
		//Fehlerbehandlung (Fangen der SQLException und Ausgabe des Fehlers)
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		//R�ckgabe des Movie
		return m;
	}
	//wird findByName ben�tigt?
	public Movie findByName(String name) {
		Movie m = null;
		//Aufbau der DB-Verbindung
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement findByName = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie WHERE name=?;");
			findByName.setString(2, name);
			
			ResultSet rs = findByName.executeQuery();
			//anpassen - siehe BusinessObject?
			m = new Movie(rs.getInt("movieID"), rs.getString("name"), rs.getTimestamp("creationDate"), rs.getInt("presentationID"), rs.getInt("userID"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return m;
	}
	public Movie insert(Movie m) {
		
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement insert = con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.movie(name) VALUES(?);");

			insert.setString(1, m.getName());

			insert.executeUpdate();
			
			// getNewMovie erweitern
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	public void deleteMovieByMovieID (int id) {
		
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement deleteByMovieID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.movie WHERE `MovieID`=?;");
		deleteByMovieID.setInt(1, id);
		deleteByMovieID.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//ToDo public void deleteAllMovieByUserID und deleteAllMovieByCinemaID
		//Erg�nzung cinemaID als fremdschl�ssel im BO
	}
}

