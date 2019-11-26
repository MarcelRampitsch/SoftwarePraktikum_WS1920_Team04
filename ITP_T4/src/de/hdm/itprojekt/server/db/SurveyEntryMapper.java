package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.SurveyEntry;

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
	  
	  //TODO surveyEntryMapper

	  public Vector<SurveyEntry> findSurveyEntryBySurveyEntryID(int id) {
		  
			Connection con = DBConnection.getConnection();

			SurveyEntry se = null;
			
			Vector<SurveyEntry> result = new Vector<SurveyEntry>();

			try {
				
				PreparedStatement findSurveyEntryBySurveyEntryID = con.prepareStatement( 
						"SELECT * FROM softwarepraktikum_ws1920.survey" + "WHERE surveyID=?");
				findSurveyEntryBySurveyEntryID.setInt(1, id);
				
				ResultSet rs = findSurveyEntryBySurveyEntryID.executeQuery();

				while (rs.next()) {
				
					se = new SurveyEntry(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("surveyID"), rs.getInt("presentationID"));

					result.addElement(se);
				}
				
			} catch (SQLException e) {

				e.printStackTrace();
			    return null;
				
			}
		  
			return result;
			
	  }

	
	
	  

}
