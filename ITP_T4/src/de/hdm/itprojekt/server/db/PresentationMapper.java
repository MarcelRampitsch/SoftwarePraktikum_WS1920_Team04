package de.hdm.itprojekt.server.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.Date;

import de.hdm.itprojekt.shared.bo.Cinema;
import de.hdm.itprojekt.shared.bo.Movie;
import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Timeslot;
import de.hdm.itprojekt.shared.bo.User;




/**
 * Die Klasse PresentationMapper bildet <code>Presentation</code> Objekte auf eine relationale
 * Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden wie z.B. (insert, delete).
 */

public class PresentationMapper {
	
	/**
	 * Die Klasse PresentationMapper wird nur einmal instantiiert (Singleton-Eigenschaft).
	 * Damit diese Eigenschaft erfüllt werden kann, wird zunächst eine Variable mit
	 * dem Schlüsselwort static und dem Standardwert null erzeugt. Sie speichert die
	 * Instanz dieser Klasse.
	 */
	
	private static PresentationMapper presentationMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	
	  protected PresentationMapper() {
		  
	  }

	  /**
		 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
		 * nur eine einzige Instanz der PresentationMapper-Klasse existiert. Aufgerufen wird die
		 * Klasse somit über PresentationMapper.presentationMapper() und nicht über den New-Operator.
		 * 
		 * @return Das <code/>presentationMapper<code/> Objekt.
		 */
	  
	  public static PresentationMapper PresentationMapper() {
	    if (presentationMapper == null) {
	    	presentationMapper = new PresentationMapper();
	    }

	    return presentationMapper;
	  }
	  
	  
	  public Vector<Presentation> findAllByMovieID(Movie m)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();
			Presentation p = null;
			// Ergebnisvektor vorbereiten
			Vector<Presentation> result = new Vector<Presentation>();
			try { // Prepared Statement erstellen um alle Präsentationen eines bestimmten Movies zu finden
				PreparedStatement findAllByMovieID = con.prepareStatement(
						"SELECT * From softwarepraktikum_ws1920.presentation "
						+ "WHERE movieID=? ");
				findAllByMovieID.setInt(1, m.getId());

                    // Ergebnis-Tupel erstellen

				ResultSet rs = findAllByMovieID.executeQuery();

				while (rs.next()) {
					// Ergebnis-Tupel in Objekt umwandeln
					p = new Presentation(rs.getString("name"), rs.getInt("cinemaID"), rs.getInt("movieID"), rs.getInt("userID"), rs.getInt("timeslotID"), rs.getDate("date"), rs.getInt("presentationID"), rs.getTimestamp("creationDate"));

					// Objekt in einen Ergebinsvektor übergeben
					result.addElement(p);
				}// Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
			      return null;
			}
			// Ergebnisvektor zurückgeben
			return result;
		
		}
	  
	  
	  public Vector<Presentation> findAllByTimeslotID(Timeslot t)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();
			Presentation p = null;
			// Ergebnisvektor vorbereiten
			Vector<Presentation> result = new Vector<Presentation>();
			try { // Prepared Statement erstellen um alle Präsentationen eines bestimmten Timeslots zu finden
				PreparedStatement findAllByTimeslotID = con.prepareStatement(
						"SELECT * From softwarepraktikum_ws1920.presentation "
						+ "WHERE TimeslotID=? ");
				findAllByTimeslotID.setInt(1, t.getId());

				// Ergebnis-Tupel erstellen


				ResultSet rs = findAllByTimeslotID.executeQuery();

				while (rs.next()) {
					// Ergebnis-Tupel in Objekt umwandeln
					p = new Presentation(rs.getString("name"), rs.getInt("cinemaID"), rs.getInt("movieID"), rs.getInt("userID"), rs.getInt("timeslotID"), rs.getDate("date"), rs.getInt("presentationID"), rs.getTimestamp("creationDate"));

					result.addElement(p);
				}// Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
			      return null;
			}
			// Ergebnisvektor zurückgeben
			return result;
		
		}
	  
	  
	  public Vector<Presentation> findAllByCinemaID(Cinema c)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();
			Presentation p = null;
			// Ergebnisvektor vorbereiten
			Vector<Presentation> result = new Vector<Presentation>();
			try { // Prepared Statement erstellen um alle Präsentationen eines bestimmten Cinema zu finden
				PreparedStatement findAllByCinemaID = con.prepareStatement(
						"SELECT * From softwarepraktikum_ws1920.presentation "
						+ "WHERE CinemaID=? ");
				findAllByCinemaID.setInt(1, c.getId());
				
				// Ergebnis-Tupel erstellen

				ResultSet rs = findAllByCinemaID.executeQuery();

				while (rs.next()) {
					// Ergebnis-Tupel in Objekt umwandeln
					p = new Presentation(rs.getString("name"), rs.getInt("cinemaID"), rs.getInt("movieID"), rs.getInt("userID"), rs.getInt("timeslotID"), rs.getDate("date"), rs.getInt("presentationID"), rs.getTimestamp("creationDate"));

					result.addElement(p);
				} // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
			      return null;
			}
			// Ergebnisvektor zurückgeben
			return result;
		
		}
	  
	  
	  public Vector<Presentation> findAllByUserID(User u)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();
			Presentation p = null;
			// Ergebnisvektor vorbereiten
			Vector<Presentation> result = new Vector<Presentation>();
			try { // Prepared Statement erstellen um alle Präsentationen eines bestimmten Cinema zu finden
				PreparedStatement findAllByUSerID = con.prepareStatement(
						"SELECT * From softwarepraktikum_ws1920.presentation "
						+ "WHERE UserID=? ");
				findAllByUSerID.setInt(1, u.getId());
				
				// Ergebnis-Tupel erstellen

				ResultSet rs = findAllByUSerID.executeQuery();

				while (rs.next()) {
					// Ergebnis-Tupel in Objekt umwandeln
					p = new Presentation(rs.getString("name"), rs.getInt("cinemaID"), rs.getInt("movieID"), rs.getInt("userID"), rs.getInt("timeslotID"), rs.getDate("date"), rs.getInt("presentationID"), rs.getTimestamp("creationDate"));

					result.addElement(p);
				} // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
			      return null;
			}
			// Ergebnisvektor zurückgeben
			return result;
		
		}
	  
	  
	  public Presentation findByPresentationID(Presentation p) {
		  Presentation pe = null;
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();
			
			try {
				// Prepared Statement erstellen um eine Präsentation zu finden
				PreparedStatement findByPresentationID = con
						.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.presentation WHERE presentationID=? ;");
				findByPresentationID.setInt(1, p.getId());

				// Statement ausfüllen und als Query an die DB schicken
				ResultSet rs = findByPresentationID.executeQuery();
				// Ergebnis-Tupel in Objekt umwandeln
				pe = new Presentation(rs.getString("name"), rs.getInt("cinemaID"), rs.getInt("movieID"), rs.getInt("userID"), rs.getInt("timeslotID"), rs.getDate("date"), rs.getInt("presentationID"), rs.getTimestamp("creationDate"));
				 // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
			      return null;

			} // Presentation zurückgeben
			return pe;
		}
	  
		  
		  public Vector<Presentation> findByDate(Presentation p)  {
				// DB-Verbindung holen
				Connection con = DBConnection.getConnection();
				Presentation pe = null;
				// Ergebnisvektor vorbereiten
				Vector<Presentation> result = new Vector<Presentation>();
				try {
					// Prepared Statement erstellen um alle Präsentationen eines bestimmten Date zu finden
					PreparedStatement findByDate = con.prepareStatement(
							"SELECT * From softwarepraktikum_ws1920.presentation "
							+ "WHERE date=? "
							+ "Order By TimeslotID ASC;");
					findByDate.setDate(1, p.getDate());

					// Statement ausfüllen und als Query an die DB schicken
					ResultSet rs = findByDate.executeQuery();

					while (rs.next()) {
						// Ergebnis-Tupel in Objekt umwandeln
						pe = new Presentation(rs.getString("name"), rs.getInt("cinemaID"), rs.getInt("movieID"), rs.getInt("userID"), rs.getInt("timeslotID"), rs.getDate("date"), rs.getInt("presentationID"), rs.getTimestamp("creationDate"));
                     // Objekt an ein Präsentationsvektor übergeben
						result.addElement(pe);
					} 				 // Fehlerbehandlung hinzufügen
				} catch (SQLException e) {
				      e.printStackTrace();
				      return null;
				}
				// Ergebnisvektor zurückgeben
				return result;
			
			}
		  
		  
		  public Presentation insert(Presentation p)  {  // Name des Movie/ Cinema ?!?!?!?!
				// DB-Verbindung holen
				Connection con = DBConnection.getConnection();

				try { 
					// Prepared Statement erstellen um eine Präsentation in die Datenbank einzufügen
					PreparedStatement insert = con
							.prepareStatement("INSERT INTO softwarepraktikum_ws1920.presentation(date, userID, cinemaID, timeslotID, movieID, name) VALUES (?,?,?,?,?,?);");
					// Jetzt erst erfolgt die tatsächliche Einfügeoperation
					
					insert.setDate(1, p.getDate());
					insert.setInt(2, p.getUserID());
					insert.setInt(3, p.getCinemaID());
					insert.setInt(4, p.getTimeslotID());
					insert.setInt(5, p.getMovieID());
					insert.setString(6, p.getName());

					
					
					insert.executeUpdate();

					PreparedStatement getnewPresentation= con
							.prepareStatement("SELECT *FROM softwarepraktikum_ws1920.presentation ORDER BY creationDate DESC LIMIT 1;");
					// Ergebnis-Tupel in Objekt umwandeln
					ResultSet rs = getnewPresentation.executeQuery();
					if (rs.next()) {
						
						return new Presentation(rs.getString("name"), rs.getInt("cinemaID"), rs.getInt("movieID"), rs.getInt("userID"), rs.getInt("timeslotID"), rs.getDate("date"), rs.getInt("presentationID"), rs.getTimestamp("creationDate"));
					}
					 // Fehlerbehandlung hinzufügen
				} catch (SQLException e) {
				      e.printStackTrace();
				      return null;

						
					}

					return null;
				}
		  
		  
		  public Presentation update(Presentation p) {
				// DB-Verbindung holen
				Connection con = DBConnection.getConnection();

				try {
		              // Updaten einer bestimmten Presentation  
					PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.presentation SET cinemaID=?, movieID=?, timeslotID=?, date=?, name=?  WHERE presentationID=?;");
					update.setInt(1, p.getCinemaID());
					update.setInt(2, p.getMovieID());
					update.setInt(3, p.getTimeslotID());
					update.setDate(4, p.getDate());
					update.setString(5, p.getName());
					update.setInt(6, p.getId());

					// PreparedStatement aufrufen und als Query an die DB schicken.
					update.executeUpdate();
					PreparedStatement stm = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.presentation WHERE presentationID=?;");
					stm.setInt(1, p.getId());
					ResultSet rs = stm.executeQuery();
					if (rs.next()) {
						// Ergebnis-Tupel in Objekt umwandeln
						return new Presentation(rs.getString("name"), rs.getInt("cinemaID"), rs.getInt("movieID"), rs.getInt("userID"), rs.getInt("timeslotID"), rs.getDate("date"), rs.getInt("presentationID"), rs.getTimestamp("creationDate"));
					}
					 // Fehlerbehandlung hinzufügen
				} catch (SQLException e) {
				      e.printStackTrace();
				      return null;
				      
				} 
				return null;
			}
		  
			
		  public void deleteByPresentationID(Presentation p)  {
				// DB-Verbindung holen
				Connection con = DBConnection.getConnection();

				try {
					// Prepared Statement zum Löschen einer bestimmten Präsentation in der Datenbank 
					PreparedStatement deleteByPresentationID = con
							.prepareStatement("DELETE FROM softwarepraktikum_ws1920.presentation WHERE presentationID=?");
					deleteByPresentationID.setInt(1, p.getId());
					deleteByPresentationID.executeUpdate();

					 // Fehlerbehandlung hinzufügen
				} catch (SQLException e) {
				      e.printStackTrace();
			}
		  }
		 
	  
	  public void deleteAllByMovieID(Movie m)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();

			try {
              // Löschen aller Presentations die einen bestimmten Movie enthalten 
				PreparedStatement deleteAllByMovieID = con
						.prepareStatement("DELETE FROM softwarepraktikum_ws1920.movie WHERE movieID =?;");
				deleteAllByMovieID.setInt(1, m.getId());
				// Statement ausfüllen und als Query an die DB schicken
				deleteAllByMovieID.executeUpdate();

				
			 // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
		}
			
						
		}
	  
	  
	  public void deleteAllByTimeslotID(Timeslot t)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();

			try {
	              // Löschen aller Presentations die einen bestimmten Timeslot enthalten 
				PreparedStatement deleteAllByTimeslotID = con
						.prepareStatement("DELETE FROM softwarepraktikum_ws1920.timeslot WHERE timeslotID =?;");
				deleteAllByTimeslotID.setInt(1, t.getId());
				// Statement ausfüllen und als Query an die DB schicken
				deleteAllByTimeslotID.executeUpdate();

			 // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
		}
			
						
		}
	  
	  
	  public void deleteAllByCinemaID(Cinema c)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();

			try {
	              // Löschen aller Presentations die einen bestimmtes Cinema enthalten 
				PreparedStatement deleteAllByCinemaID = con
						.prepareStatement("DELETE FROM softwarepraktikum_ws1920.cinema WHERE cinemaID =?;");
				deleteAllByCinemaID.setInt(1, c.getId());
				// Statement ausfüllen und als Query an die DB schicken
				deleteAllByCinemaID.executeUpdate();

				
				 // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
		}
			
						
		}
	  
	  
	  public void deleteAllByUserID(User u)  {
			// DB-Verbindung holen
			Connection con = DBConnection.getConnection();

			try {
	              // Löschen aller Presentations die einen bestimmtes Cinema enthalten 
				PreparedStatement deleteAllByCinemaID = con
						.prepareStatement("DELETE FROM softwarepraktikum_ws1920.cinema WHERE userID =?;");
				deleteAllByCinemaID.setInt(1, u.getId());
				// Statement ausfüllen und als Query an die DB schicken
				deleteAllByCinemaID.executeUpdate();

				
				 // Fehlerbehandlung hinzufügen
			} catch (SQLException e) {
			      e.printStackTrace();
		}
			
						
		}
	  

}
