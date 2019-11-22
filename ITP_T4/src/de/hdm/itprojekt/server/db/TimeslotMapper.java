package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.hdm.itprojekt.shared.bo.Timeslot;



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
	
	 public Timeslot findByTimeslotID(int id) {
			// DB-Verbindung holen
		  Timeslot t = null;
			Connection con = DBConnection.getConnection();
			
			try {
				// Prepared Statement erstellen um einen Timeslot zu finden
				PreparedStatement findByTimeslotID = con
						.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.timeslot WHERE timeslotid=?;");
				findByTimeslotID.setInt(1, id);

				// Statement ausfüllen und als Query an die DB schicken
				ResultSet rs = findByTimeslotID.executeQuery();
				// Ergebnis-Tupel in Objekt umwandeln
				t = new Timeslot(rs.getTimestamp("time"), rs.getInt("userID"),rs.getInt("id"), rs.getTimestamp("creationDate"));
				// Fehlerbehandlung hinzufügen
			} catch (SQLException e2) {
			      e2.printStackTrace();
			      return null;

			} // Presentation zurückgeben
			return t;
		}
	 
	 public Timeslot findByTime(int id) {
			// DB-Verbindung holen
		  Timeslot t = null;
			Connection con = DBConnection.getConnection();
			
			try {
				// Prepared Statement erstellen um einen Timeslot zu finden
				PreparedStatement findByTime = con
						.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.timeslot WHERE time=?;");
				findByTime.setInt(1, id);

				// Statement ausfüllen und als Query an die DB schicken
				ResultSet rs = findByTime.executeQuery();
				t = new Timeslot(rs.getTimestamp("time"), rs.getInt("userID"),rs.getInt("id"), rs.getTimestamp("creationDate"));
			} catch (SQLException e2) {
			      e2.printStackTrace();
			      return null;

			}
			return t;
		}
	 
	 public Timeslot insert(Timeslot t)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();

			try {
				// Prepared Statement erstellen um einen Timeslot in die Datenbank einzufügen
				PreparedStatement insert = con
						.prepareStatement("INSERT INTO softwarepraktikum_ws1920.timeslot(time, userID) VALUES (?,?);");
				// Jetzt erst erfolgt die tatsächliche Einfügeoperation
				insert.setTimestamp(1, t.getTime());
				
				insert.executeUpdate();

				PreparedStatement getnewTimeslot = con
						.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.timeslot ORDER BY creationDate DESC LIMIT 1;");
				// Ergebnis-Tupel in Objekt umwandeln
				ResultSet rs = getnewTimeslot.executeQuery();
				if (rs.next()) {
					return new Timeslot(rs.getTimestamp("time"),rs.getInt("userID"),rs.getInt("id"), rs.getTimestamp("creationDate"));
				}
			
				 // Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;

				
			}

		
			return null;
		}
	  
	  
}