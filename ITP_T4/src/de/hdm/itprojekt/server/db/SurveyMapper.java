package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Survey;
import de.hdm.itprojekt.shared.bo.User;

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
			  PreparedStatement findBySurveyID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.survey WHERE surveyID=? ;");
			  findBySurveyID.setInt(1, id);
			  
			  // Ausführen des Queries
			  ResultSet rs = findBySurveyID.executeQuery();

			  s = new Survey(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("groupID"));
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  //Rückgabe des Survey
		  return s;
	  }
	  
	  // Methode zum Finden eines Surveys anhand des Namens
	  public Survey findByName(String name) {
		  Survey s = null;

		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement findByName = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.survey WHERE name=?;");
			  findByName.setString(1, name);
			  
			  ResultSet rs = findByName.executeQuery();

			  s = new Survey(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("groupID"));
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  return s;
	  }
	  
	  // Methode zum einfügen von Surveys in die Datenbank
	  public Survey insert(Survey s) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement insert = con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.survey(name) VALUES(?);");
			  
			  insert.setString(1, s.getName());
			  
			  insert.executeUpdate();
			  
			  PreparedStatement getnewSurvey= con.prepareStatement("SELECT *FROM softwarepraktikum_ws1920.survey ORDER BY creationDate DESC LIMIT 1;");
			  
			  ResultSet rs = getnewSurvey.executeQuery();
			  
			  if (rs.next()) {
				  return new Survey(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("groupID"));
			  }
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  return null;
	  }
	  
	  // Methode zum Löschen eines Surveys anhand der SurveyID
	  public void deleteSurveyBySurveyID (int id) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement deleteBySurveyID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.survey WHERE surveyID=?;");
			  deleteBySurveyID.setInt(1, id);
			  deleteBySurveyID.executeUpdate();
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  //TODO Methode korrekt umgesetzt?
	  // Methode zum aktualisieren von Surveys
	  public Survey updateSurvey(Survey survey) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.survey SET name=?;");
			  
			  update.setString(1, survey.getName());
			  
			  update.executeUpdate();
			  
			  PreparedStatement stmt = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.survey WHERE 'surveyID'=?;");
			  
			  update.setInt(1, survey.getId());
			  
			  ResultSet rs = stmt.executeQuery();
			  
			  if(rs.next()) {
				  return new Survey(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getString("name"), rs.getInt("userID"), rs.getInt("groupID"));
			  }
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  return null;
	  }
	  
	  // Methode zum löschen aller Surveys die von einem User erstellt wurden
	  public void deleteAllByUserID(User u) {

		  Connection con = DBConnection.getConnection();
		  
		  try {
			  
			  PreparedStatement deleteAllByUserID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.surveyID WHERE userID =?;");
			  deleteAllByUserID.setInt(1,  u.getId());
			  
			  deleteAllByUserID.executeUpdate();
			  
		  } catch(SQLException e) {
			  e.printStackTrace();
		  }
	  }
	  
	  // Methode zum Löschen aller Surveys die einer Gruppe zugeordnet sind
	  public void deleteAllByGroupID (Group g) {

		  Connection con = DBConnection.getConnection();
		  
		  try {
			  
			  PreparedStatement deleteAllByGroupID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.surveyID WHERE groupID =?;");
			  deleteAllByGroupID.setInt(1, g.getId());
			  
			  deleteAllByGroupID.executeUpdate();
			  
		  } catch(SQLException e) {
			  e.printStackTrace();
		  }
	  }
}