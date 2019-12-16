package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.CinemaGroup;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.User;

public class CinemaGroupMapper {
	
	/**
	 * Die Klasse CinemaGroupMapper bildet <code>CinemaGroup</code> Objekte auf eine
	 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static CinemaGroupMapper cinemaGroupMapper =null;
	
//
	/**
	 * Die Klasse CinemaGroupMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected CinemaGroupMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der CinemaGroupMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über CinemaGroupMapper.cinemaGroupMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>CinemaGroupMapper<code/> Objekt.
	 */

	
	public static CinemaGroupMapper CinemaGroupMapper() {
		if (cinemaGroupMapper == null) {
			cinemaGroupMapper = new CinemaGroupMapper();
		}
		return cinemaGroupMapper;
	}
	
	
	
	public CinemaGroup insert(CinemaGroup c) {
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		
		try {
			
			// PreparedStatement erstellen um eine CinemaGroup in die Datenbank einzufügen
			PreparedStatement insert  = 
					con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.cinemaGroup(name, userID) VALUES (?,?);");
			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
					insert.setString(1, c.getName());
					insert.setInt(2, c.getUserID());
					
					insert.executeUpdate();
					
					
				PreparedStatement getnewCinemaGroup= con
							.prepareStatement("SELECT *FROM softwarepraktikum_ws1920.cinemaGroup ORDER BY creationDate DESC LIMIT 1;");
					// Ergebnis-Tupel in Objekt umwandeln
					ResultSet rs = getnewCinemaGroup.executeQuery();
					if (rs.next()) {

						return new CinemaGroup(rs.getInt("id"), rs.getTimestamp("creationDate"),rs.getString("name"), rs.getInt("userID"));
					}
					 // Fehlerbehandlung hinzufügen
				} catch (SQLException e) {
				      e.printStackTrace();
				      return null;
					}
					return null;
					
	}
	
	public CinemaGroup findCinemaGroupByID(CinemaGroup cg) {
		CinemaGroup cineg = null;
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		
		
		try {
			// PreparedStatement erstellen um eine CinemaGroup in die Datenbank einzufügen
			PreparedStatement findCinemaGroupByID = 
					con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinemaGroup WHERE cinemaGroupID=?;");
			
			
			findCinemaGroupByID.setInt(1, cg.getId());
			ResultSet rs = findCinemaGroupByID.executeQuery();
			
			// Ergebnis-Tupel in Objekt umwandeln
			cineg = new CinemaGroup(rs.getInt("id"), rs.getTimestamp("creationDate"),rs.getString("name"), rs.getInt("userID"));
			
			// Fehlerbehandlung hinzufügen
		} catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		}
		return cineg;
}
	
	
	
	public CinemaGroup updateCinemaGroup(CinemaGroup cinemaGroup) {
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
	
		try {
			// PreparedStatement erstellen um eine CinemaGroup in die Datenbank einzufügen
			PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.cinemaGroup SET name=? WHERE cinemaGroupID=?;");
			
			// PreparedStatement aufrufen und als Query an die DB schicken.
			update.setString(1, cinemaGroup.getName());
			update.setInt(2, cinemaGroup.getId());

			update.executeUpdate();
			
			PreparedStatement stm = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinemaGroup WHERE cinemaGroupID=?;");
			stm.setInt(1, cinemaGroup.getId());
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				
				// Ergebnis-Tupel in Objekt umwandeln
				return new CinemaGroup(rs.getInt("id"), rs.getTimestamp("creationDate"),rs.getString("name"), rs.getInt("userID"));
			}
			 // Fehlerbehandlung hinzufügen
		} catch (SQLException e) {
		      e.printStackTrace();
		      return null;

		} 
		return null;
	}
	
	
	
	public void deleteCinemaGroupByID(int id) {
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		
		try {
			
			// PreparedStatement erstellen um eine CinemaGroup in die Datenbank einzufügen
			PreparedStatement deleteCinemaGroupById =
					con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.cinemaGroup" + "WHERE cinemaGroupID=? ");
			deleteCinemaGroupById.setInt(1, id);		
			 // Löschen der CinemaGroups die einen bestimmten UserID enthalten
			deleteCinemaGroupById.executeUpdate();
			
			 // Fehlerbehandlung hinzufügen
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
	}
		

	public Vector<CinemaGroup> findcinemagroupbyuserID (User u){
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		CinemaGroup cinemagroup = null;  
		
		// Ergebnisvektor vorbereiten
		Vector<CinemaGroup> result = new Vector<CinemaGroup>();
		
		try {
			
			// PreparedStatement erstellen um eine CinemaGroup in die Datenbank einzufügen
			PreparedStatement findCinemaGroupByUserID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinemagroup " + "WHERE UserID=? ");
			findCinemaGroupByUserID.setInt(1, u.getId());
			
			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = findCinemaGroupByUserID.executeQuery();
					
					
					while(rs.next()) {
						
						// Ergebnis-Tupel in Objekt umwandeln
						cinemagroup = new CinemaGroup(rs.getInt("cinemaGroupID"),rs.getTimestamp("creationDate"),rs.getString("name"),rs.getInt("userID"));
					
						result.addElement(cinemagroup);
					}
					
					// Fehlerbehandlung hinzufügen
				} catch (SQLException e) {
				      e.printStackTrace();
				      return null;
				}
				// Ergebnisvektor zurückgeben
				return result;
			}
	
	
	
	
	public void deleteAllCinemaGroupByUserID(CinemaGroup cine) {
		
		
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		
		
		try {
			// PreparedStatement erstellen um eine CinemaGroup in die Datenbank einzufügen
			PreparedStatement deleteAllCinemaGroupByUserID =
					con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.cinemagroup" + " WHERE userID=?");
			
			// Löschen der CinemaGroups die einen bestimmten UserID enthalten
			deleteAllCinemaGroupByUserID.setInt(1, cine.getUserID());
			
			deleteAllCinemaGroupByUserID.executeUpdate();
			
			// Fehlerbehandlung hinzufügen
		} catch (SQLException e) {
		      e.printStackTrace();
	}
		
					
	}
			
		
	}

	
