package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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
	
	public Movie findByMovieID(int id){
		Movie m = null;
		
		// Aufbau der DB-Verbindung
		Connection con = DBConnection.getConnection();

		try {
		// Erstellung des Prepared Statement um alle Filme anhand der MovieID zufinden

			PreparedStatement findByMovieID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920 WHERE movieID=? ;");
		findByMovieID.setInt(1,  id);
		
		// ausführen des Queries
		ResultSet rs = findByMovieID.executeQuery();
		
		m = new Movie(rs.getInt("presentationID"), rs.getInt("id"), rs.getString("name"), rs.getTimestamp("creationDate"));
		
		//Fehlerbehandlung (Fangen der SQLException und Ausgabe des Fehlers)
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		//Rückgabe des Movie
		return m;
	}
}

