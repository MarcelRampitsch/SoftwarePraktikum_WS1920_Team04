package de.hdm.itprojekt.server.db;

/**
 * Die Klasse GroupMapper bildet <code>Group</code> Objekte auf eine
 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
 * Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden wie z.B. (insert, delete).
 */

public class GroupMapper {
	
	/**
	 * Die Klasse GroupMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */

	private static GroupMapper groupMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse durch <code>new</code>.
	 */

	protected GroupMapper() {

	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der GroupMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über GroupMapper.groupMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>groupMapper<code/> Objekt.
	 */

	public static GroupMapper GroupMapper() {
		if (groupMapper == null) {
			groupMapper = new GroupMapper();
		}
		return groupMapper;
	}
	
	
	
	

}
