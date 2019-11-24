package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import de.hdm.itprojekt.shared.bo.CinemaGroup;

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
		
		Connection con = DBConnection.getConnection();
		
		try {
			
			PreparedStatement insert  = 
					
					con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.cinemaGroupname(name, userID) VALUES (?,?);");
			
					insert.setString(3, c.getName());
					insert.setInt(4, c.getUserID());
					
					insert.executeUpdate();
					
					
				PreparedStatement getnewCinemaGroup= con
							.prepareStatement("SELECT *FROM softwarepraktikum_ws1920.cinemaGroup ORDER BY creationDate DESC LIMIT 1;");
					// Ergebnis-Tupel in Objekt umwandeln
					ResultSet rs = getnewCinemaGroup.executeQuery();
					if (rs.next()) {

						return new CinemaGroup(rs.getInt("cinemaGroupID"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"));
					}
					 // Fehlerbehandlung hinzufügen
				} catch (SQLException e2) {
				      e2.printStackTrace();
				      return null;


					}

					return null;
		
	}
	
	public CinemaGroup findCinemaGroupByID(int id) {
		CinemaGroup cg = null;
		
		Connection con = DBConnection.getConnection();
		
		
		try {
			
			PreparedStatement findCinemaGroupByID = 
					con.prepareStatement("SELECT softwarepraktikum_ws1920.cinemagroup WHERE presentationdID=?;");
			
			findCinemaGroupByID.setInt(1, id);
			
			
			ResultSet rs = findCinemaGroupByID.executeQuery();
			
			cg = new CinemaGroup(rs.getInt("cinemaGroupID"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"));
			

		} catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;

		}
		
		return cg;
			
}
	
	
	
	public CinemaGroup updateCinemaGroup(CinemaGroup cinemaGroup) {
		
		Connection con = DBConnection.getConnection();
		
		
		
		try {
			
			PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.cinemagroup SET userID=?, creationDate=?, name=? WHERE cinemaGroupID=?;");
			
			update.setInt(4, cinemaGroup.getUserID());
			update.setTimestamp(2, cinemaGroup.getCreationDate());
			update.setString(3, cinemaGroup.getName());
			update.setInt(1, cinemaGroup.getId());

			update.executeUpdate();
			PreparedStatement stm = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinemagroup WHERE cinemaGroupID=?;");
			stm.setInt(1, cinemaGroup.getId());
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				
				
				return new CinemaGroup(rs.getInt("cinemaGroupID"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"));
			}
			 // Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;

		} 
		return null;
	}
	
	
	public void deleteCinemaGroupByID(int id) {
		
		Connection con = DBConnection.getConnection();
		
		
		
		try {
			
			PreparedStatement deleteCinemaGroupById =
					
					con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.cinemagroup WHERE 'cinemaGroupID' = ?;");
					
			deleteCinemaGroupById.setInt(1, id);
			
			deleteCinemaGroupById.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
		
}
	
