package de.hdm.itprojekt.server.db;

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
}