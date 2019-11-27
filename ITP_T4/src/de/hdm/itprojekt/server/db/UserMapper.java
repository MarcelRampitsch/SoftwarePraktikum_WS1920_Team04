package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.User;
import de.hdm.itprojekt.shared.bo.Admin;

/**
 * Die Klasse UserMapper bildet <code>User</code> Objekte auf eine
 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
 * Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden wie z.B. (insert, delete).
 */

public class UserMapper {
	
	/**
	 * Die Klasse UserMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert null
	 * erzeugt. Sie speichert die Instanz dieser Klasse.
	 */

	private static UserMapper userMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse durch <code>new</code>.
	 */

	protected UserMapper() {

	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der UserMapper-Klasse existiert. Aufgerufen wird
	 * die Klasse somit über UserMapper.userMapper() und nicht über den
	 * New-Operator.
	 * 
	 * @return Das <code/>userMapper<code/> Objekt.
	 */

	public static UserMapper UserMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}
		return userMapper;
	}
}
