package de.hdm.itprojekt.server.db;

public class SurveyMapper {
	
	/**
	 * Die Klasse SurveyMapper bildet <code>Survey</code> Objekte auf eine
	 * relationale Datenbank ab. Ebenfalls ist es m�glich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static SurveyMapper surveyMapper =null;
	

	/**
	 * Die Klasse SurveyMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erf�llt werden kann, wird
	 * zun�chst eine Variable mit dem Schl�sselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected SurveyMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt daf�r, dass
	 * nur eine einzige Instanz der SurveyMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit �ber SurveyMapper.surveyMapper() und nicht �ber den
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
	
}