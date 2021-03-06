package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.Group;
import de.hdm.itprojekt.shared.bo.Groupmember;
import de.hdm.itprojekt.shared.bo.User;

/**
 * Die Klasse GroupmemberMapper bildet <code>Group</code> Objekte auf eine
 * relationale Datenbank ab. Ebenfalls ist es möglich aus Datenbank-Tupel
 * Java-Objekte zu erzeugen.
 * 
 * Zur Verwaltung der Objekte implementiert die Mapper-Klasse entsprechende
 * Methoden wie z.B. (insert, delete).
 */

public class GroupmemberMapper {

	/**
	 * Die Klasse GroupmemberMapper wird nur einmal instantiiert
	 * (Singleton-Eigenschaft). Damit diese Eigenschaft erfüllt werden kann, wird
	 * zunächst eine Variable mit dem Schlüsselwort static und dem Standardwert
	 * null erzeugt. Sie speichert die Instanz dieser Klasse.
	 */

	private static GroupmemberMapper groupmemberMapper = null;

	/**
	 * Ein geschützter Konstruktor verhindert das erneute erzeugen von weiteren
	 * Instanzen dieser Klasse durch <code>new</code>.
	 */

	protected GroupmemberMapper() {

	}

	/**
	 * Methode zum Sicherstellen der Singleton-Eigenschaft. Diese sorgt dafür, dass
	 * nur eine einzige Instanz der GroupmemberMapper-Klasse existiert. Aufgerufen
	 * wird die Klasse somit über GroupmemberMapper.groupmemberMapper() und nicht
	 * über den New-Operator.
	 * 
	 * @return Das <code/>groupmemberMapper<code/> Objekt.
	 */

	public static GroupmemberMapper GroupmemberMapper() {
		if (groupmemberMapper == null) {
			groupmemberMapper = new GroupmemberMapper();
		}
		return groupmemberMapper;
	}

	public Vector<Groupmember> findAllByUserID(User u) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		Groupmember gm = null;
		// Ergebnisvektor vorbereiten
		Vector<Groupmember> result = new Vector<Groupmember>();
		try { // Prepared Statement erstellen um alle Group eines bestimmten User zu finden
			PreparedStatement findAllByUserID = con
					.prepareStatement("SELECT * From softwarepraktikum_ws1920.groupmember " + "WHERE userID=? ");
			findAllByUserID.setInt(1, u.getId());

			// Ergebnis-Tupel erstellen

			ResultSet rs = findAllByUserID.executeQuery();

			while (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				gm = new Groupmember(rs.getInt("groupMemberID"), rs.getTimestamp("creationDate"), rs.getInt("groupID"),
						rs.getInt("userID"));

				result.addElement(gm);
			} // Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		// Ergebnisvektor zurückgeben
		return result;
	}

	public Vector<Groupmember> findAllByGroupID(Group g) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();
		Groupmember gm = null;
		// Ergebnisvektor vorbereiten
		Vector<Groupmember> result = new Vector<Groupmember>();
		try { // Prepared Statement erstellen um alle User einer bestimmten Group zu finden
			PreparedStatement findAllByGroupID = con
					.prepareStatement("SELECT * From softwarepraktikum_ws1920.groupmember " + "WHERE groupID=? ");
			findAllByGroupID.setInt(1, g.getId());

			// Ergebnis-Tupel erstellen

			ResultSet rs = findAllByGroupID.executeQuery();

			while (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				gm = new Groupmember(rs.getInt("groupmemberID"), rs.getTimestamp("creationDate"), rs.getInt("groupID"),
						rs.getInt("userID"));

				result.addElement(gm);
			} // Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		// Ergebnisvektor zurückgeben
		return result;
	}

	public Groupmember insertGroupmember(Groupmember gm) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Prepared Statement erstellen um einen groupmember in die Datenbank
			// einzufügen
			PreparedStatement insert = con.prepareStatement(
					"INSERT INTO softwarepraktikum_ws1920.groupmember(groupID, userID) VALUES (?,?);");
			// Jetzt erst erfolgt die tatsächliche Einfügeoperation
			insert.setInt(1, gm.getGroupID());
			insert.setInt(2, gm.getUserID());

			insert.executeUpdate();

			PreparedStatement getnewGroup = con.prepareStatement(
					"SELECT *FROM softwarepraktikum_ws1920.groupmember ORDER BY creationDate DESC LIMIT 1;");
			// Ergebnis-Tupel in Objekt umwandeln
			ResultSet rs = getnewGroup.executeQuery();
			if (rs.next()) {

				return new Groupmember(rs.getInt("groupMemberID"), rs.getTimestamp("creationDate"),
						rs.getInt("groupID"), rs.getInt("userID"));
			}
			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}

		return null;
	}

	public Groupmember updateGroupmember(Groupmember gm) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Updaten einer bestimmten Presentation
			PreparedStatement update = con
					.prepareStatement("UPDATE softwarepraktikum_ws1920.group SET name=? WHERE groupID=?;");
			update.setInt(1, gm.getGroupID());
			update.setInt(2, gm.getUserID());

			// PreparedStatement aufrufen und als Query an die DB schicken.
			update.executeUpdate();
			PreparedStatement stm = con
					.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.group WHERE groupID=?;");
			stm.setInt(1, gm.getId());
			ResultSet rs = stm.executeQuery();
			if (rs.next()) {
				// Ergebnis-Tupel in Objekt umwandeln
				return new Groupmember(rs.getInt("id"), rs.getTimestamp("creationDate"), rs.getInt("groupID"),
						rs.getInt("userID"));
			}
			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
	}

	public void deleteGroupmemberByGroupmemberID(Groupmember gm) {
		Connection con = DBConnection.getConnection();

		try {
			// Prepared Statement zum Löschen aller Groupmember eines bestimmten User in
			// der Datenbank
			PreparedStatement deleteByID = con
					.prepareStatement("DELETE FROM softwarepraktikum_ws1920.groupmember WHERE groupMemberID=?");
			deleteByID.setInt(1, gm.getId());
			deleteByID.executeUpdate();

			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void deleteAllGroupmemberByGroupID(Group g) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Prepared Statement zum Löschen eines bestimmten Groupmember in der Datenbank
			PreparedStatement deleteByPresentationID = con
					.prepareStatement("DELETE FROM softwarepraktikum_ws1920.groupmember WHERE `groupID`=?;");
			deleteByPresentationID.setInt(1, g.getId());
			deleteByPresentationID.executeUpdate();
			// deleteByPresentationID.executeDeletion();

			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}

	public void deleteAllGroupmemberByUserID(User u) {
		// DB-Verbindung holen
		Connection con = DBConnection.getConnection();

		try {
			// Prepared Statement zum Löschen aller Groupmember eines bestimmten User in
			// der Datenbank
			PreparedStatement deleteByUserID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.groupmember WHERE userID=?");
			deleteByUserID.setInt(1, u.getId());
			deleteByUserID.executeUpdate();

			// Fehlerbehandlung hinzufügen
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
	}
}
