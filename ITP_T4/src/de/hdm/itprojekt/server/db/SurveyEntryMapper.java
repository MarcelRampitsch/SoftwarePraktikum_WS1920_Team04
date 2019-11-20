package de.hdm.itprojekt.server.db;


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



	
	

}
