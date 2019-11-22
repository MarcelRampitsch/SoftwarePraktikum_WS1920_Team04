package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

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
		
		Connection con = DBConnection.getConnection();

		try { 
			
			PreparedStatement  insert = 
					con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.cinema(location,name,cinemaGroupID,userID) VALUES (?,?,?,?);");
					
				insert.setString(3, c.getLocation());
				insert.setString(4, c.getName());
				insert.setInt(5, c.getCinemaGroupID());
				insert.setInt(6, c.getUserID());
					
				
				insert.executeUpdate();
				
				PreparedStatement getnewCinema = 
						con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinema ORDER BY creationDate DESC LIMIT 1;");
						
						ResultSet rs = getnewCinema.executeQuery();
						
						if(rs.next()) {
							
							return new Cinema(rs.getInt("cinemaID"), rs.getTimestamp("creationdate"), rs.getString("location"), rs.getString("name"), rs.getInt("cinemaGroupID"), rs.getInt("userID"));
							
		} 

		} catch (SQLException e2) {
		      e2.printStackTrace();
		      return null;


			}


			return null;
		}
	
	
	
	public void deleteCinemaByCinemaID(int id) {
		
		Connection con = DBConnection.getConnection();
		
		try {
			
			PreparedStatement deleteCinemaByCinemaID =
					con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.cinema WHERE 'cinemaID' =?;");
			
			deleteCinemaByCinemaID.setInt(1, id);
			
			
			deleteCinemaByCinemaID.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
			
			
		}
		
		
		
	}
	
	
	public Cinema updateCinema(Cinema cinema) {
		
		Connection con = DBConnection.getConnection();
		
		
		try {
			
			PreparedStatement update = 
					
					con.prepareStatement("UPDATE softwarepraktikum_ws1920.cinema SET creationDate=?, location=?, name=?, cinemaGroupID=?, userID=? WHERE cinemaID=?;");
					
					update.setTimestamp(2, cinema.getCreationDate());
					update.setString(3, cinema.getLocation());
					update.setString(4, cinema.getName());
					update.setInt(5, cinema.getCinemaGroupID());
					update.setInt(6, cinema.getUserID());

					
					update.executeUpdate();
					
					PreparedStatement stm  = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.cinema WHERE 'cinemaID'=?;");
	
					update.setInt(1, cinema.getId());
					
					ResultSet rs  = stm.executeQuery();
					
					if(rs.next()) {
						
						return new Cinema(rs.getInt("cinemaID"), rs.getTimestamp("creationDate"), rs.getString("location"), rs.getString("name"), rs.getInt("cinemaGroupID") , rs.getInt("userID"));          
								
								
								
					}
					
		} catch (SQLException e) {
		      e.printStackTrace();
		      return null;

		} 
		return null;
		}
		
		
		
	}
	
	

	
	
	

