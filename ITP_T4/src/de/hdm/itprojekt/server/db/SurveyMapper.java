package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Survey;

public class SurveyMapper {
	
	/**
	 * Die Klasse SurveyMapper bildet <code>Survey</code> Objekte auf eine
	 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static SurveyMapper surveyMapper =null;
	

	/**
	 * Die Klasse SurveyMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected SurveyMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der SurveyMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über SurveyMapper.surveyMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>SurveyMapper<code/> Objekt.
	 */

	
	public static SurveyMapper SurveyMapper() {
		if (surveyMapper == null) {
			surveyMapper = new SurveyMapper();
		}
		return surveyMapper;
	}
	  public Survey findBySurveyID(int id) {
		  Survey s = null;
		  
		  // Aufbau der DB-Verbindung
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  // Erstellung des Prepared Statement um alle Surveys anhand der SurveyID zu finden
			  
			  PreparedStatement findBySurveyID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920 WHERE surveyID=? ;");
			  findBySurveyID.setInt(1, id);
			  
			  // ausführen des Queries
			  ResultSet rs = findBySurveyID.executeQuery();
			  
			  s = new Survey(rs.getInt("surveyID"), rs.getString("name"), rs.getTimestamp("creationDate"), rs.getInt("surveyEntryID"), rs.getInt("userID"), rs.getInt("groupID"));
			  
		  } catch (SQLException e2) {
			  e2.printStackTrace();
			  return null;
		  }
		  //Rückgabe des Survey
		  return s;
	  }
	  
	  public Survey findByName(String name) {
		  Survey s = null;
		  //Aufbau der DB-Verbindung
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement findByName = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.survey WHERE name=?;");
			  findByName.setString(2, name);
			  
			  ResultSet rs = findByName.executeQuery();
			  s = new Survey(rs.getInt("surveyID"), rs.getString("name"), rs.getTimestamp("creationDate"), rs.getInt("surveyEntryID"), rs.getInt("userID"), rs.getInt("groupID"));
		  } catch (SQLException e2) {
			  e2.printStackTrace();
			  return null;
		  }
		  return s;
	  }
	  
	  public Survey insert(Survey s) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement insert = con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.survey(surveyID, name, creationDate) VALUES(?,?,?);");
			  
			  insert.setInt(1,  s.getUserID());
			  insert.setString(2, s.getName());
			  insert.setTimestamp(3, s.getCreationDate());
			  
			  insert.executeUpdate();
		  } catch (SQLException e2) {
			  e2.printStackTrace();
			  return null;
		  }
		  return null;
		  
	  }
	  
	  public void deleteSurveyBySurveyID (int id) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement deleteBySurveyID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.survey WHERE `SurveyID`=?;");
			  deleteBySurveyID.setInt(1, id);
			  deleteBySurveyID.executeUpdate();
			  
		  } catch (SQLException e2) {
			  e2.printStackTrace();
		  }
	  }
	  
	  public Survey updateSurvey(Survey survey) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.survey SET surveyID=?, name=?, creationDate=?, surveyEntryID=?, userID=?, groupID=?;");
			  
			  update.setInt(1, survey.getSurveyID());
			  update.setString(2, survey.getName());
			  update.setTimestamp(3, survey.getCreationDate());
			  update.setInt(4, survey.getSurveyEntryID());
			  update.setInt(5, survey.getUserID());
			  update.setInt(6, survey.getGroupID());
			  
			  update.executeUpdate();
			  
			  PreparedStatement stmt = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.survey WHERE 'surveyID'=?;");
			  
			  update.setInt(1, survey.getId());
			  
			  ResultSet rs = stmt.executeQuery();
			  
			  if(rs.next()) {
				  
				  return new Survey(rs.getInt("surveyID"), rs.getString("name"), rs.getTimestamp("creationDate"), rs.getInt("surveyEntryID"), rs.getInt("userID"), rs.getInt("groupID"));
			  }
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  return null;
	  }
	  
	  
}