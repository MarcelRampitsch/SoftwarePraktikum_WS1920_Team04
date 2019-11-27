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
	 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static MovieMapper movieMapper =null;
	

	/**
	 * Die Klasse MovieMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected MovieMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der MovieMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über MovieMapper.movieMapper() und nicht über den
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
	
	// Methode zum Finden von Filmen anhand der MovieID
	public Movie findByMovieID(int id){
		Movie m = null;
		
		// Aufbau der DB-Verbindung
		Connection con = DBConnection.getConnection();

		try {
		// Erstellung des Prepared Statement findByMovieID
		PreparedStatement findByMovieID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie WHERE movieID=? ;");
		findByMovieID.setInt(1,  id);
		
		// Ausführen des Queries
		ResultSet rs = findByMovieID.executeQuery();
		
		// Hinzufügen der Attribute zum Movieobjekt
		m = new Movie(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("cinemaID"));
		
		// Fehlerbehandlung (Fangen der SQLException und Ausgabe des Fehlers)
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// Rückgabe des Movieobjekt
		return m;
	}
	//TODO findAllMovieByUserID
	//TODO wird findByName benötigt?
	public Movie findByName(String name) {
		Movie m = null;
		
		Connection con = DBConnection.getConnection();
		
		try {
			// Erstellung des PreparedStatement findByName
			PreparedStatement findByName = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie WHERE name=?;");
			findByName.setString(2, name);
			
			ResultSet rs = findByName.executeQuery();
			
			m = new Movie(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("cinemaID"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return m;
	}
	// Methode um der Datenbank Filme hinzuzufügen
	public Movie insert(Movie m) {
		
		Connection con = DBConnection.getConnection();
		
		try {
			// Erstellung des Prepared Statement insert
			PreparedStatement insert = con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.movie(name, userID, cinemaID) VALUES(?,?,?);");

			insert.setString(1, m.getName());
			insert.setInt(2, m.getUserID());
			insert.setInt(3, m.getCinemaID());

			insert.executeUpdate();
			
			// Erstellung des PreparedStatement getnewMovie Wählt das zuletzt erstellte absteigend.
			PreparedStatement getnewMovie = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie ORDER BY creationDate DESC LIMIT 1;");
			
			ResultSet rs = getnewMovie.executeQuery();
			if (rs.next()) {
				return new Movie(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("cinemaID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	// Methode zum löschen von Filmen aus der Datenbank anhand der MovieID
	public void deleteMovieByMovieID (int id) {
		
		Connection con = DBConnection.getConnection();
		
		try {
			// Erstellung des PreparedStatement deleteMovieID
			PreparedStatement deleteByMovieID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.movie WHERE movieID=?;");
		deleteByMovieID.setInt(1, id);
		deleteByMovieID.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// Methode zum löschen aller von einem Anwender erstellten Filme aus der Datenbank
	public void DeleteAllByUserID(int id) {
			//Aufbau der DB-Verbindung
			Connection con = DBConnection.getConnection();
			
			try {
				PreparedStatement deleteAllByUserID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.movie WHERE userID =?;");
				deleteAllByUserID.setInt(1, id);
				deleteAllByUserID.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	// Methode zum löschen aller Filme aus der Datenbank, die mit einem bestimmten Kino verknüpft sind
	public void DeleteAllByCinemaID(int id) {
			Connection con = DBConnection.getConnection();
			
			try {
				// Erstellung des PreparedStatement deleteAllByCinemaID
				PreparedStatement deleteAllByCinemaID = con.prepareStatement("DELETE FROM softwareprakikum_ws1920.movie WHERE cinemaID =?;");
				deleteAllByCinemaID.setInt(1, id);
				deleteAllByCinemaID.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}

