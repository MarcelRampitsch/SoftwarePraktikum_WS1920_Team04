package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.User;

/**
 * Die Klasse GroupMapper bildet <code>Group</code> Objekte auf eine relationale
 * Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel Java-Objekte zu
 * erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden wie z.B. (insert, delete).
 */

public class GroupMapper {

	/**
	 * Die Klasse GroupMapper wird nur einmal instantiiert (Singleton-Eigenschaft).
	 * Damit diese Eigenschaft erfüllt werden kann, wird zunächst eine Variable
	 * mit dem Schlüsselwort static und dem Standardwert null erzeugt. Sie
	 * speichert die Instanz dieser Klasse.
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

	public Vector<Group> findAllByUserID(User u) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		Group g = null;
		// Ergebnisvektor vorbereiten
		Vector<Group> result = new Vector<Group>();
		try { // Prepared Statement erstellen um alle Group eines bestimmten User zu finden
			PreparedStatement findAllByUserID = con
					.prepareStatement("SELECT * From softwarepraktikum_ws1920.group " + "WHERE userID=? ");
			findAllByUserID.setInt(1, u.getId());

			// Ergebnis-Tupel erstellen

			ResultSet rs = findAllByUserID.executeQuery();

			while (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				g = new Group(rs.getInt("groupID"), rs.getTimestamp("creationDate"), rs.getString("name"),
						rs.getInt("userID"));

				result.addElement(g);
			}

			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		// Ergebnisvektor zurückgeben
		return result;
	}

	public Vector<Group> findAllGroupnameByUserID(User u) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		Group g = null;
		// Ergebnisvektor vorbereiten
		Vector<Group> result = new Vector<Group>();
		try { // Prepared Statement erstellen um alle Group eines bestimmten User zu finden
			PreparedStatement findAllByUserID = con
					.prepareStatement("SELECT * From softwarepraktikum_ws1920.group " + "WHERE userID=? ");
			findAllByUserID.setInt(1, u.getId());

			// Ergebnis-Tupel erstellen

			ResultSet rs = findAllByUserID.executeQuery();

			while (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				g = new Group(rs.getString("name"));

				result.addElement(g);
			}

			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		// Ergebnisvektor zurückgeben
		return result;
	}

	public Group findByGroupID(Group g) {
		Group gr = null;
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Prepared Statement erstellen um eine Group zu finden
			PreparedStatement findByGroupID = con
					.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.group WHERE groupID=? ;");
			findByGroupID.setInt(1, g.getId());

			// Statement ausfüllen und als Query an die DB schicken
			ResultSet rs = findByGroupID.executeQuery();
			// Ergebnis-Tupel in Objekt umwandeln
			while (rs.next()) {

				gr = new Group(rs.getInt("groupID"), rs.getTimestamp("creationDate"), rs.getString("name"),
						rs.getInt("userID"));
			}
			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		} // Group zurückgeben
		return gr;
	}

	public Group insertGroup(Group g) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Prepared Statement erstellen um eine Präsentation in die Datenbank
			// einzufügen
			PreparedStatement insert = con
					.prepareStatement("INSERT INTO softwarepraktikum_ws1920.group(name, userID) VALUES (?,?);");
			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			insert.setString(1, g.getName());
			insert.setInt(2, g.getUserID());

			insert.executeUpdate();

			PreparedStatement getnewGroup = con.prepareStatement(
					"SELECT *FROM softwarepraktikum_ws1920.group ORDER BY creationDate DESC LIMIT 1;");
			// Ergebnis-Tupel in Objekt umwandeln
			ResultSet rs = getnewGroup.executeQuery();
			if (rs.next()) {

				return new Group(rs.getInt("groupID"), rs.getTimestamp("creationDate"), rs.getString("name"),
						rs.getInt("userID"));
			}
			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Group updateGroup(Group g) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Updaten einer bestimmten Presentation
			PreparedStatement update = con
					.prepareStatement("UPDATE softwarepraktikum_ws1920.group SET name=? WHERE groupID=?;");
			update.setString(1, g.getName());
			update.setInt(2, g.getId());

			// PreparedStatement aufrufen und als Query an die DB schicken.
			update.executeUpdate();
			PreparedStatement stm = con
					.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.group WHERE groupID=?;");
			stm.setInt(1, g.getId());
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				return new Group(rs.getInt("groupID"), rs.getTimestamp("creationDate"), rs.getString("name"),
						rs.getInt("userID"));
			}
			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	public void deleteByGroupID(Group g) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Prepared Statement zum Löschen einer bestimmten Group in der Datenbank
			PreparedStatement deleteByPresentationID = con
					.prepareStatement("DELETE FROM softwarepraktikum_ws1920.group WHERE `groupID`=?;");
			deleteByPresentationID.setInt(1, g.getId());
			deleteByPresentationID.executeUpdate();
			// deleteByPresentationID.executeDeletion();

			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void deleteAllByUserID(User u) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Prepared Statement zum Löschen einer bestimmten Präsentation in der
			// Datenbank
			PreparedStatement deleteByPresentationID = con
					.prepareStatement("DELETE FROM softwarepraktikum_ws1920.presentation WHERE `PresentationID`=?;");
			deleteByPresentationID.setInt(1, u.getId());
			deleteByPresentationID.executeUpdate();

			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}