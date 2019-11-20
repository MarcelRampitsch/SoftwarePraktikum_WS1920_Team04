package de.hdm.itprojekt.server.db;

public class TimeslotMapper {
	
	/**
	 * Die Klasse TimeslotMapper bildet <code>Timeslot</code> Objekte auf eine
	 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
	 * Java-Objekte zu erzeugen.
	 * 
	 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
	 * Methoden wie z.B. (insert, delete,update..).
	 */
	
	private static TimeslotMapper timeslotMapper =null;
	

	/**
	 * Die Klasse TimeslotMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	
	protected TimeslotMapper() {
		
	}
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der TimeslotMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über TimeslotMapper.timeslotMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>TimeslotMapper<code/> Objekt.
	 */

	
	public static TimeslotMapper TimeslotMapper() {
		if (timeslotMapper == null) {
			timeslotMapper = new TimeslotMapper();
		}
		return timeslotMapper;
	}
}