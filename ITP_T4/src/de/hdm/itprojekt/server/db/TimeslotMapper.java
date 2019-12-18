package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;



public class TimeslotMapper {
	
	/**
	 * Die Klasse TimeslotMapper bildet <code>Timeslot</code> Objekte auf eine
	 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static TimeslotMapper timeslotMapper =null;
	

	/**
	 * Die Klasse TimeslotMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected TimeslotMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der TimeslotMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über TimeslotMapper.timeslotMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>TimeslotMapper<code/> Objekt.
	 */

	
	public static TimeslotMapper TimeslotMapper() {
		if (timeslotMapper == null) {
			timeslotMapper = new TimeslotMapper();
		}
		return timeslotMapper;
	}
	
	 public Timeslot findByTimeslotID(Timeslot t) {
			// DB-Verbindung holen
		  Timeslot te = null;
			Connection con = DBConnection.getConnection();
			
			try {
				// Prepared Statement erstellen um einen Timeslot zu finden
				PreparedStatement findByTimeslotID = con
						.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.timeslot WHERE timeslotid=?;");
				findByTimeslotID.setInt(1, t.getId());

				// Statement ausfüllen und als Query an die DB schicken
				ResultSet rs = findByTimeslotID.executeQuery();
				// Ergebnis-Tupel in Objekt umwandeln
				te = new Timeslot(rs.getString("time"), rs.getInt("userID"),rs.getInt("id"), rs.getTimestamp("creationDate"));
				// Fehlerbehandlung hinzufügen
			} catch (SQLException e2) {
			      e2.printStackTrace();
			      return null;

			} // Presentation zurückgeben
			return te;
		}
	 
	 public Timeslot findByTime(Timeslot t) {
			// DB-Verbindung holen
		  Timeslot te = null;
			Connection con = DBConnection.getConnection();
			
			try {
				// Prepared Statement erstellen um einen Timeslot zu finden
				PreparedStatement findByTime = con
						.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.timeslot WHERE time=?;");
				findByTime.setString(1, t.getTime());

				// Statement ausfüllen und als Query an die DB schicken
				ResultSet rs = findByTime.executeQuery();
				te = new Timeslot(rs.getString("time"), rs.getInt("userID"),rs.getInt("id"), rs.getTimestamp("creationDate"));
			} catch (SQLException e2) {
			      e2.printStackTrace();
			      return null;

			}
			return te;
		}
	 
	 public Timeslot insert(Timeslot t)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();

			try {
				// Prepared Statement erstellen um einen Timeslot in die Datenbank einzufügen
				PreparedStatement insert = con
						.prepareStatement("INSERT INTO softwarepraktikum_ws1920.timeslot(time, userID) VALUES (?,?);");
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				insert.setString(1, t.getTime());
				insert.setInt(2, t.getUserID());
				
				insert.executeUpdate();

				PreparedStatement getnewTimeslot = con
						.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.timeslot ORDER BY creationDate DESC LIMIT 1;");
				// Ergebnis-Tupel in Objekt umwandeln
				ResultSet rs = getnewTimeslot.executeQuery();
				if (rs.next()) {
					return new Timeslot(rs.getString("time"),rs.getInt("userID"),rs.getInt("id"), rs.getTimestamp("creationDate"));
				}
				 // Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;	
			}		
			return null;
		}
	 
	 public Timeslot update(Timeslot t) {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();

			try {
	              // Updaten eines bestimmten Timeslot  
				PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.timeslot SET time=? WHERE timeslotID=?;");
				update.setString(1, t.getTime());
				
				// PreparedStatement aufrufen und als Query an die DB schicken.
				update.executeUpdate();
				PreparedStatement stm = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.timeslot WHERE timeslotID=?;");
				stm.setInt(1, t.getId());
				ResultSet rs = stm.executeQuery();
				if (rs.next()) {
					// Ergebnis-Tupel in Objekt umwandeln
					return new Timeslot(rs.getString("time"),rs.getInt("userID"), rs.getInt("id"), rs.getTimestamp("creationDate"));
				}
				 // Fehlerbehandlung hinzufügen
			} catch (SQLException e2) {
			      e2.printStackTrace();
			      return null;
			      
			} 
			return null;
		}
	 
	 public void deleteByTimeslotID(Timeslot t)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();

			try {
				// Prepared Statement zum Löschen eines bestimmten Timeslot in der Datenbank 
				PreparedStatement deleteByTimeslotID = con
				        .prepareStatement("DELETE FROM softwarepraktikum_ws1920.timeslot WHERE timeslotID=?;");
				deleteByTimeslotID.setInt(1, t.getId());
				// Statement ausfüllen und als Query an die DB schicken
				deleteByTimeslotID.executeUpdate();
				
				 // Fehlerbehandlung hinzufügen
			} catch (SQLException e2) {
			      e2.printStackTrace();
		}
	  }
	 
	 public void deleteAllTimeslotByUserID(Timeslot t) {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();
			
			try {	
				// Prepared Statement zum Löschen aller Timeslots in der Datenbank 
				PreparedStatement deleteAllCinemaGroupByUserID =
				con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.timeslot WHERE userID=?;");
				deleteAllCinemaGroupByUserID.setInt(1, t.getUserID());
				
				// Statement ausfüllen und als Query an die DB schicken
				deleteAllCinemaGroupByUserID.executeUpdate();
				
				 // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
		}
		}
	 
	 public Vector<Timeslot> findAllTimeslotByUserID(User u)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();
			Timeslot t = null;
			// Ergebnisvektor vorbereiten
			Vector<Timeslot> result = new Vector<Timeslot>();
			try { // Prepared Statement erstellen um alle Präsentationen eines bestimmten Cinema zu finden
				PreparedStatement findAllByUSerID = con.prepareStatement(
						"SELECT * From softwarepraktikum_ws1920.timeslot "
						+ "WHERE UserID=? ");
				findAllByUSerID.setInt(1, u.getId());
				
				// Ergebnis-Tupel erstellen

				ResultSet rs = findAllByUSerID.executeQuery();

				while (rs.next()) {
					// Ergebnis-Tupel in Objekt umwandeln
					t = new Timeslot(rs.getString("time"), rs.getInt("userID"), rs.getInt("id"), rs.getTimestamp("creationDate"));

					result.addElement(t);
				} // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
			      return null;
			}
			// Ergebnisvektor zurückgeben
			return result;
		
		}
	 //TODO DeleteAllByMovieID SINN?	  
}