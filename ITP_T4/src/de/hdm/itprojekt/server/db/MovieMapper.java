package de.hdm.itprojekt.server.db;

public class MovieMapper {
	
	/**
	 * Die Klasse MovieMapper bildet <code>Movie</code> Objekte auf eine
	 * relationale Datenbank ab. Ebenfalls ist es m�glich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static MovieMapper movieMapper =null;
	

	/**
	 * Die Klasse MovieMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erf�llt werden kann, wird
	 * zun�chst eine Variable mit dem Schl�sselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected MovieMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt daf�r, dass
	 * nur eine einzige Instanz der MovieMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit �ber MovieMapper.movieMapper() und nicht �ber den
	 * New-Operator.
	 * 
	 * @return Das <code/>MovieMapper<code/> Objekt.
	 */

	
	public static MovieMapper MovieMapper() {
		if (movieMapper == null) {
			movieMapper = new MovieMapper();
		}
		return movieMapper;
	}
	
	
	

}
