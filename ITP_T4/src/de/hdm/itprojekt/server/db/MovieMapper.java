package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.User;

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
	
	// Methode zum Finden von Filmen anhand der MovieID
	public Movie findByMovieID(Movie m){
		Movie mo = null;
		
		// Aufbau der DB-Verbindung
		Connection con = DBConnection.getConnection();

		try {
		// Erstellung des Prepared Statement findByMovieID
		PreparedStatement findByMovieID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie WHERE movieID=? ;");
		
		// Hinzuf�gen der ID
		findByMovieID.setInt(1,  m.getId());
		
		// Ausf�hren des Queries
		ResultSet rs = findByMovieID.executeQuery();
		
		// Hinzuf�gen der Attribute zum Movieobjekt
		mo = new Movie(rs.getInt("movieID"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("cinemaID"));
		
		// Fehlerbehandlung (Fangen der SQLException und Ausgabe des Fehlers)
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		// R�ckgabe des Movieobjekt
		return mo;
	}
	
	// Methode zum Finden von Filmen anhand des Filmtitels
	public Movie findByName(Movie m) {
		Movie mo = null;
		
		Connection con = DBConnection.getConnection();
		
		try {
			// Erstellung des PreparedStatement findByName
			PreparedStatement findByName = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie WHERE name=?;");
			findByName.setString(1, m.getName());
			
			ResultSet rs = findByName.executeQuery();
			
			mo = new Movie(rs.getInt("movieID"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("cinemaID"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return mo;
	}
	
	// Methode zum Finden aller von einem User erstellten Filme
	public Vector<Movie> findAllByUserID(User u) {
		
		Connection con = DBConnection.getConnection();
		Movie m = null;
		// Ergebnisvektor vorbereiten
		Vector<Movie> result = new Vector<Movie>();
		
		try {
			PreparedStatement findAllByUserID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie" + "WHERE userID=?");
			findAllByUserID.setInt(1, u.getId());
			
			ResultSet rs = findAllByUserID.executeQuery();
			
			while (rs.next()) {
				m = new Movie(rs.getInt("movieID"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("cinemaID"));
				result.addElement(m);
			}
			
		} catch (SQLException e) {
			      e.printStackTrace();
			      return null;
			}
			// Ergebnisvektor zur�ckgeben
			return result;
	}
	
	// Methode um der Datenbank Filme hinzuzuf�gen und den hinzugef�gten anzuzeigen
	public Movie insert(Movie m) {
		
		Connection con = DBConnection.getConnection();
		
		try {
			// Erstellung des Prepared Statement insert
			PreparedStatement insert = con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.movie(name, userID, cinemaID) VALUES(?,?,?);");

			insert.setString(1, m.getName());
			insert.setInt(2, m.getUserID());
			insert.setInt(3, m.getCinemaID());

			insert.executeUpdate();
			
			// Erstellung des PreparedStatement getnewMovie (W�hlt den zuletzt erstellten Film absteigend).
			PreparedStatement getnewMovie = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.movie ORDER BY creationDate DESC LIMIT 1;");
			
			ResultSet rs = getnewMovie.executeQuery();
			
			if (rs.next()) {
				return new Movie(rs.getInt("movieID"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("cinemaID"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public void updateMovie (Movie m) {
		Connection con = DBConnection.getConnection();
		
		try {
			PreparedStatement updateMovie = con.prepareStatement("UPDATE softwarepraktikum_ws1920.movie SET name=? WHERE movieID=?;");
			updateMovie.setString(1, m.getName());
			updateMovie.setInt(2, m.getId());
			updateMovie.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Methode zum l�schen von Filmen aus der Datenbank anhand der MovieID
	public void deleteByMovieID (Movie m) {
		
		Connection con = DBConnection.getConnection();
		
		try {
			// Erstellung des PreparedStatement deleteByMovieID
			PreparedStatement deleteByMovieID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.movie WHERE movieID=?;");
		deleteByMovieID.setInt(1, m.getId());
		deleteByMovieID.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Methode zum l�schen aller von einem Anwender erstellten Filme aus der Datenbank
	public void deleteAllByUserID(User u) {

		Connection con = DBConnection.getConnection();
			
		try {
			PreparedStatement deleteAllByUserID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.movie WHERE userID =?;");
			deleteAllByUserID.setInt(1, u.getId());
			deleteAllByUserID.executeUpdate();
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

