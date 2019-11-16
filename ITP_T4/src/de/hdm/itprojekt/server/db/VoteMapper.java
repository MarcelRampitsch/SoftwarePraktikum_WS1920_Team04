package de.hdm.itprojekt.server.db;

/**
 * Die Klasse VoteMapper bildet <code>Vote</code> Objekte auf eine
 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
 * Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * 
 * Methoden wie z.B. (insert, delete, update ...)
 */

public class VoteMapper {

	
	/**
	 * Die Klasse VoteMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */
	private static VoteMapper voteMapper = null;

	
	
	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse durch <code>new</code>.
	 */
	
	protected VoteMapper() {
		
		
	}
	
	
	
	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der VoteMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über VoteMapper.voteMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>VoteMapper<code/> Objekt.
	 */
	
	public static VoteMapper PresentationMapper() {
		if (voteMapper == null) {
			voteMapper = new VoteMapper();
		}
		return voteMapper;
	}
}
