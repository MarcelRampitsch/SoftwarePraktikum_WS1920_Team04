package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.Vote;

/**
 * Die Klasse VoteMapper bildet <code>Vote</code> Objekte auf eine
 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
 * Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * 
 * Methoden wie z.B. (insert, delete, update ...)
 */

public class VoteMapper {

	
	/**
	 * Die Klasse VoteMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	private static VoteMapper voteMapper = null;

	
	
	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse durch <code>new</code>.
	 */
	
	protected VoteMapper() {
		
		
	}
	
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der VoteMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über VoteMapper.voteMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>VoteMapper<code/> Objekt.
	 */
	
	public static VoteMapper PresentationMapper() {
		if (voteMapper == null) {
			voteMapper = new VoteMapper();
		}
		return voteMapper;
	}
	
	public Vector<Vote> findVoteByVoteID(int id) {
		
		Connection con = DBConnection.getConnection();

		Vote v = null;
			
		Vector<Vote> result = new Vector<Vote>();
		
		try {
			
			PreparedStatement findVoteByUserID = con.prepareStatement( 
					"SELECT * FROM softwarepraktikum_ws1920.vote" + "WHERE id=?");
			findVoteByUserID.setInt(1, id);
				
			ResultSet rs = findVoteByUserID.executeQuery();

			while (rs.next()) {
					
				v = new Vote(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("surveyentryID"), rs.getInt("userID"), rs.getInt("voteResult"));

				result.addElement(v);
				
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
					
		}
			
		return result;
		
	}
	
	
	public Vector<Vote> findVoteByUserID(int userID) {

		Connection con = DBConnection.getConnection();

		Vote v = null;
			
		Vector<Vote> result = new Vector<Vote>();


		try {
				
			PreparedStatement findVoteByUserID = con.prepareStatement( 
					"SELECT * FROM softwarepraktikum_ws1920.vote" + "WHERE userID=?");
			findVoteByUserID.setInt(1, userID);
				
			ResultSet rs = findVoteByUserID.executeQuery();

			while (rs.next()) {
					
				v = new Vote(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("surveyentryID"), rs.getInt("userID"), rs.getInt("voteResult"));

				result.addElement(v);
				
			}
					
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
					
		}
				
		return result;
				
	}
	  
	  
	public Vector<Vote> findAllVoteBySurveyEntryID(int id) {

		Connection con = DBConnection.getConnection();

		Vote v = null;

		Vector<Vote> result = new Vector<Vote>();

		try {
			
			PreparedStatement findAllVoteBySurveyEntryID = con.prepareStatement( 
					"SELECT * FROM softwarepraktikum_ws1920.vote" + "WHERE surveyentryID=?");
			findAllVoteBySurveyEntryID.setInt(1, id);

			ResultSet rs = findAllVoteBySurveyEntryID.executeQuery();
			
			while (rs.next()) {
				
				v = new Vote(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("surveyentryID"), rs.getInt("userID"), rs.getInt("voteResult"));

				result.addElement(v);
				
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		    return null;
			
		}

		return result;
		
	}
	
	
	public Vote insert(Vote v) {
	
		Connection con = DBConnection.getConnection();
		
		try {
			
			PreparedStatement insert = con
					.prepareStatement("INSERT INTO softwarepraktikum_ws1920.vote(id, creationDate, surveyentryID, userID, voteResult) VALUES(?,?,?,?,?);");
		
			insert.setInt(1, v.getId());
			//insert.setInt(2, v.getCreationDate());
			insert.setInt(3, v.getSurveyEntryID());
			insert.setInt(4, v.getUserID());
			insert.setInt(5, v.getVoteResult());
			
			insert.executeUpdate();
	
			PreparedStatement getnewVote= con
					.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.vote ORDER BY creationDate DESC LIMIT 1;");
		
			ResultSet rs = getnewVote.executeQuery();
			
			if (rs.next()) {
				
				return new Vote(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("surveyentryID"), rs.getInt("userID"), rs.getInt("voteResult"));
			
			}
				
		} catch (SQLException e) {
		      e.printStackTrace();
		      return null;

		}

		return null;
		
	}
	
	
	public Vote update(Vote v) {
		
		  Connection con = DBConnection.getConnection();

		  try {
			  
			  PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.vote SET id=?, creationDate=?, surveyentryID=?, userID=?, voteResult  WHERE id=?;");
			  
			  update.setInt(1, v.getId());
			  //update.setInt(2, v.getCreationDate());
			  update.setInt(3, v.getSurveyEntryID());
			  update.setInt(4, v.getUserID());
			  update.setInt(5, v.getVoteResult());
			  
			  update.executeUpdate();
			  
			  PreparedStatement stm = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.vote WHERE id=?;");
			  
			  stm.setInt(1, v.getId());
			  ResultSet rs = stm.executeQuery();
			  
			  if (rs.next()) {
				  
				  return new Vote(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("surveyentryID"), rs.getInt("userID"), rs.getInt("voteResult"));
			
			  }
			  
		  } catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		      
		  } 
	
		  return null;
		
	}
	
	
	public void deleteVoteByVoteID(int id)  {
		
		Connection con = DBConnection.getConnection();
		
		try {
			 
			 PreparedStatement deleteVoteByVoteID = con
					 .prepareStatement("DELETE FROM softwarepraktikum_ws1920.vote WHERE id=?;");
		
			 deleteVoteByVoteID.setInt(1, id);
			 deleteVoteByVoteID.executeUpdate();
		} catch (SQLException e) {
		      e.printStackTrace();
		}
	
	}
	
	
	public void deleteAllVote(int id)  {
		
		Connection con = DBConnection.getConnection();
		
		try {
			 
			 PreparedStatement deleteAllVote = con
					 .prepareStatement("DELETE FROM softwarepraktikum_ws1920.vote WHERE id=?;");
		
			 deleteAllVote.setInt(1, id);
			 deleteAllVote.executeUpdate();
		} catch (SQLException e) {
		      e.printStackTrace();
		}
	
	}
	
	
	
	
}
