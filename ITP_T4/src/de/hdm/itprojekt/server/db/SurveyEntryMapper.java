package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Presentation;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.SurveyEntry;
import de.hdm.itprojekt.shared.bo.User;

/**
 * Die Klasse SurveyEntryMapper bildet <code>SurveyEntry</code> Objekte auf eine
 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
 * Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden wie z.B. (insert, delete).
 */

public class SurveyEntryMapper {
	
	/**
	 * Die Klasse SurveyEntryMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	private static SurveyEntryMapper surveyEntryMapper = null;

	  /**
	   * Geschützter Konstruktor - verhindert die Möglichkeit, mit <code>new</code>
	   * neue Instanzen dieser Klasse zu erzeugen.
	   */
	  protected SurveyEntryMapper() {
	  }

	  /**
		 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
		 * nur eine einzige Instanz der SurveyEntryMapper-Klasse existiert. Aufgerufen wird
		 * die Klasse somit über SurveyEntryMapper.surveyEntryMapper() und nicht über den
		 * New-Operator.
		 * 
		 * @return Das <code/>surveyEntryMapper<code/> Objekt.
		 */
	  public static SurveyEntryMapper SurveyEntryMapper() {
	    if (surveyEntryMapper == null) {
	    	surveyEntryMapper = new SurveyEntryMapper();
	    }

	    return surveyEntryMapper;
	  }
	  

	  public SurveyEntry findSurveyEntryBySurveyEntryID(SurveyEntry se) {
		  
			Connection con = DBConnection.getConnection();
			
			try {
				
				PreparedStatement findSurveyEntryBySurveyEntryID = con.prepareStatement( 
						"SELECT * FROM softwarepraktikum_ws1920.surveyentry" + "WHERE surveyEntryID=?");
				findSurveyEntryBySurveyEntryID.setInt(1, se.getId());
				
				ResultSet rs = findSurveyEntryBySurveyEntryID.executeQuery();

				while (rs.next()) {
				
					se = new SurveyEntry(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("surveyID"), rs.getInt("presentationID"));

				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			    return null;
				
			}
		  
			return se;
			
	  }
	  
	  
	  public Vector<SurveyEntry> findAllSurveyEntryBySurveyID(Survey s) {

			Connection con = DBConnection.getConnection();

			SurveyEntry se = null;
			Vector<SurveyEntry> result = new Vector<SurveyEntry>();
			try {
				
				PreparedStatement findAllSurveyEntryBySurveyID = con.prepareStatement( 
						"SELECT * From softwarepraktikum_ws1920.surveyentry " 
				+ "WHERE surveyID=? ");
				findAllSurveyEntryBySurveyID.setInt(1, s.getId());

				ResultSet rs = findAllSurveyEntryBySurveyID.executeQuery();

				while (rs.next()) {
					
					se = new SurveyEntry(rs.getInt("surveyEntryID"), rs.getTimestamp("creationDate"), rs.getInt("surveyID"), rs.getInt("presentationID"));
					
					result.add(se);
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			    return null;
				
			}
				
			return result;
		  
	  }

	
	  public SurveyEntry insert(SurveyEntry se) {
		  
			Connection con = DBConnection.getConnection();

			try {
				
				PreparedStatement insert = con
						.prepareStatement("INSERT INTO softwarepraktikum_ws1920.surveyentry(surveyID, presentationID) VALUES(?,?);");
				
				insert.setInt(1, se.getSurveyID());
				insert.setInt(2, se.getPresentationID());
				
				insert.executeUpdate();
				
				PreparedStatement getnewSurveyEntry= con
						.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.surveyentry ORDER BY creationDate DESC LIMIT 1;");
				
				ResultSet rs = getnewSurveyEntry.executeQuery();

				if (rs.next()) {
					
					return new SurveyEntry(rs.getInt("surveyEntryID"), rs.getTimestamp("creationDate"), rs.getInt("surveyID"), rs.getInt("presentationID"));
				
				}
				
			} catch (SQLException e) {
			      e.printStackTrace();
			      return null;

			}
				
			return null;
		  
	  }
	  
	  
	  public SurveyEntry update(SurveyEntry se) {

		  Connection con = DBConnection.getConnection();
		  
		  try {
			  
			  PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.surveyentry SET presentationID=?  WHERE surveyEntryID=?;");
			  
			  update.setInt(1, se.getPresentationID());
			  
			  update.executeUpdate();
			  
			  PreparedStatement stm = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.surveyentry WHERE surveyEntryID=?;");
			  
			  stm.setInt(1, se.getId());
			  ResultSet rs = stm.executeQuery();
			  
			  if (rs.next()) {
				  
					return new SurveyEntry(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("surveyID"), rs.getInt("presentationID"));
			
			  }
			  
		  } catch (SQLException e) {
		      e.printStackTrace();
		      return null;
		      
		  } 
			  
		  return null;
		  
	  }
	  

	  public void deleteSurveyEntryBySurveyEntryID(SurveyEntry se)  {
	  
		 Connection con = DBConnection.getConnection();
		  
		 try {
			 
			 PreparedStatement deleteBySurveyEntryID = con
					 .prepareStatement("DELETE FROM softwarepraktikum_ws1920.surveyentry WHERE surveyEntryID=?;");
			 
			 deleteBySurveyEntryID.setInt(1, se.getId());
			 deleteBySurveyEntryID.executeUpdate();
		 
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }
		 
	  }
	  
	  
	  public void deleteAllBySurveyID(Survey s)  {
		  
		  Connection con = DBConnection.getConnection();
			  
		  try {
				 
			  PreparedStatement deleteAllBySurveyID = con
					  .prepareStatement("DELETE FROM softwarepraktikum_ws1920.surveyentry WHERE surveyID=?;");
				 
			  deleteAllBySurveyID.setInt(1, s.getId());
			  deleteAllBySurveyID.executeUpdate();
			 
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
			 	 
	  }
	  
	  public void deleteAllByUserID(User u) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement deleteAllByUserID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.surveyentry WHERE userID=?;");
			  
			  deleteAllByUserID.setInt(1, u.getId());
			  deleteAllByUserID.executeUpdate();
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  
	  public void deleteAllByPresentationID(Presentation p)  {
		  
		  Connection con = DBConnection.getConnection();
			  
		  try {
				 
			  PreparedStatement deleteAllByPresentationID = con
					  .prepareStatement("DELETE FROM softwarepraktikum_ws1920.surveyentry WHERE presentationID=?");
				 
			  deleteAllByPresentationID.setInt(1, p.getId());
			  deleteAllByPresentationID.executeUpdate();
			 
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
			 	 
	  }

		  
}
