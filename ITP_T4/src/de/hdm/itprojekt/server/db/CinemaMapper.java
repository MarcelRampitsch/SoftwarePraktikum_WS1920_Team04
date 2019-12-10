package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Cinema;

public class CinemaMapper {
	
	
	/**
	 * Die Klasse CinemaMapper bildet <code>Cinema</code> Objekte auf eine
	 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static CinemaMapper cinemaMapper =null;
	

	/**
	 * Die Klasse CinemaMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected CinemaMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der CinemaMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über CinemaMapper.cinemaMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>CinemaMapper<code/> Objekt.
	 */

	
	public static CinemaMapper CinemaMapper() {
		if (cinemaMapper == null) {
			cinemaMapper = new CinemaMapper();
		}
		return cinemaMapper;
	}

	
	
	
	public Cinema insertCinema(Cinema c) {
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try { 
			// Prepared Statement erstellen um ein Cinema zu finden
			PreparedStatement  insert = 
					con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.cinema(location,name,cinemaGroupID,userID) VALUES (?,?,?,?);");
				
			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				insert.setString(1, c.getLocation());
				insert.setString(2, c.getName());
				insert.setInt(3, c.getCinemaGroupID());
				insert.setInt(4, c.getUserID());
					
				
				insert.executeUpdate();
				
				PreparedStatement getnewCinema = 
						con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinema ORDER BY creationDate DESC LIMIT 1;");
						
				// Ergebnis-Tupel in Objekt umwandeln
				ResultSet rs = getnewCinema.executeQuery();
						
						if(rs.next()) {
							
							return new Cinema(rs.getInt("cinemaID"), rs.getTimestamp("creationDate"), rs.getString("location"), rs.getString("name"), rs.getInt("cinemaGroupID"), rs.getInt("userID"));
							
		} 
		// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;
		      
			}
			return null;
		}
		
	public void deleteCinemaByCinemaID(int id) {
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		
		try {
			// Prepared Statement erstellen um ein Cinema zu finden
			PreparedStatement deleteCinemaByCinemaID =
					con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.cinema " + "WHERE cinemaID=? ");
			
			// PreparedStatement zum Löschen einer bestimmten Cinema in der Datenbank
			deleteCinemaByCinemaID.setInt(1, id);
			
			deleteCinemaByCinemaID.executeUpdate();
			
			// Fehlerbehandlung hinzufügen
		}catch(SQLException e) {
			e.printStackTrace();		
		}
		
		
		
	}
	
	
	public Cinema updateCinema(Cinema cinema) {
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		
		
		try {
            // Updaten einer bestimmten Cinema  
			PreparedStatement update = 
					
					con.prepareStatement("UPDATE softwarepraktikum_ws1920.cinema SET location=?, name=?, cinemaGroupID=?, WHERE cinemaID=?;");
					
		
					update.setString(1, cinema.getLocation());
					update.setString(2, cinema.getName());
					update.setInt(3, cinema.getCinemaGroupID());
					update.setInt(4, cinema.getId());

					// PreparedStatement aufrufen und als Query an die DB schicken.
					update.executeUpdate();
					
					PreparedStatement stm  = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinema WHERE 'cinemaID'=?;");
	
					update.setInt(1, cinema.getId());
					
					ResultSet rs  = stm.executeQuery();
					
					if(rs.next()) {
						// Ergebnis-Tupel in Objekt umwandeln
						return new Cinema(rs.getInt("cinemaID"), rs.getTimestamp("creationDate"), rs.getString("location"), rs.getString("name"), rs.getInt("cinemaGroupID"), rs.getInt("userID"));          
					}
					 // Fehlerbehandlung hinzufügen
		} catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		} 
		return null;
	}
	

	public void deleteAllCinemaByUserID(Cinema cine) {
		
		// DB-Verbindung holen
		Connection con  = DBConnection.getConnection();
		
		try {
			// Prepared Statement zum Löschen eines Cinemas.
			PreparedStatement deleteAllCinemaByUserID = 
							con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.cinema " + "WHERE userID=? ");
			
			deleteAllCinemaByUserID.setInt(1, cine.getUserID());
			
			deleteAllCinemaByUserID.executeUpdate();
			
			 // Fehlerbehandlung hinzufügen	
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public Cinema findCinemaByCinemaID(Cinema cine) {
		Cinema c= null;
		
		// DB-Verbindung holen
		Connection con  = DBConnection.getConnection();
		
		
		try {
			// PreparedStatement erstellen um ein Cinema  anhand der cinemaID zu finden
			PreparedStatement findCinemaByCinemaID = 
						con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinema WHERE cinemaID=?;");
			
			findCinemaByCinemaID.setInt(1, cine.getId());
			
			// Ergebnis-Tupel erstellen
			ResultSet rs = findCinemaByCinemaID.executeQuery();
			
			// Ergebnis-Tupel in Objekt umwandeln
			c = new Cinema(rs.getInt("cinemaID"), rs.getTimestamp("creationDate"), rs.getString("location"), rs.getString("name"), rs.getInt("cinemaGroupID"), rs.getInt("userID"));
		} catch (SQLException e) {
		      e.printStackTrace();
		      return null;

		} // Cinema zurückgeben
		return c;
	}
	
	
	public Vector<Cinema> findCinemaByLocation(Cinema cine) {
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		
		Cinema c = null;
		
		// Ergebnisvektor vorbereiten
		Vector<Cinema> result  = new Vector <Cinema>();
		
		try {
			// PreparedStatement erstellen um ein Cinema  anhand der location zu finden
			PreparedStatement findCinemaByLocation = 
						con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinema WHERE location=?;");
			
			findCinemaByLocation.setString(1, cine.getLocation());
			
			// Ergebnis-Tupel erstellen
			ResultSet rs = findCinemaByLocation.executeQuery();
			
			while (rs.next()) {
				
				// Ergebnis-Tupel in Objekt umwandeln
				c = new Cinema(rs.getInt("cinemaID"), rs.getTimestamp("creationDate"), rs.getString("location"), rs.getString("name"), rs.getInt("cinemaGroupID"), rs.getInt("userID"));
			
				result.addElement(c);
			}
			// Fehlerbehandlung hinzufügen
		} catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		}
		return result;

	} 
	
	
	
	
	public Vector<Cinema> findallCinemabyUserID(int cine){
		
		Connection con = DBConnection.getConnection();
		
		Cinema cm = null;
		
		Vector<Cinema> result = new Vector<Cinema>();
		
		try {
			PreparedStatement findallCinemabyUserID  =
						con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinema " + "WHERE userID=? ");
			
			findallCinemabyUserID.setInt(1, cine);
			
			ResultSet rs = findallCinemabyUserID.executeQuery();
			
			while(rs.next()) {
				
				cm = new Cinema(rs.getInt("cinemaID"), rs.getTimestamp("creationDate"), rs.getString("location"), rs.getString("name"), rs.getInt("cinemaGroupID"), rs.getInt("userID"));
						
			
			result.addElement(cm);
		}// Fehlerbehandlung hinzufügen
	} catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	}
	// Ergebnisvektor zurückgeben
	return result;


	}
public Vector<Cinema> findAllCinemaByCinemaGroupID(int id){
		
		Connection con = DBConnection.getConnection();
		Cinema cm = null;
		Vector<Cinema> result = new Vector<Cinema>();
		
		try {
			PreparedStatement findallCinemabyCinemaGroupID  =
						con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinema " + "WHERE cinemaGroupID=? ");
			findallCinemabyCinemaGroupID.setInt(1, id);
			ResultSet rs = findallCinemabyCinemaGroupID.executeQuery();
			
			while(rs.next()) {
				cm = new Cinema(rs.getInt("cinemaID"), rs.getTimestamp("creationDate"), rs.getString("location"), rs.getString("name"), rs.getInt("cinemaGroupID"), rs.getInt("userID"));
				result.addElement(cm);
		}// Fehlerbehandlung hinzufügen
	} catch (SQLException e) {
	      e.printStackTrace();
	      return null;
	}
	// Ergebnisvektor zurückgeben
	return result;
	}
}
	
