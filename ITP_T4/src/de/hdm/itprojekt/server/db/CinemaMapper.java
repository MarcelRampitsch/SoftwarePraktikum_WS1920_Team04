package de.hdm.itprojekt.server.db;

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
	
	
	
	
}
