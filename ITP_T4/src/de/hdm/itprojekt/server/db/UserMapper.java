package de.hdm.itprojekt.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import de.hdm.itprojekt.shared.bo.User;

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
	  public User findByNickname(User user) {
		  User u = null;
		  
		  // Aufbau der DB-Verbindung
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  // Erstellung des Prepared Statement um einen User per Nickname zu finden
			  
			  PreparedStatement findBySurveyID = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.user WHERE nickname=? ;");
			  findBySurveyID.setString(1, user.getNickname());
			  
			  // ausf�hren des Queries
			  ResultSet rs = findBySurveyID.executeQuery();

			  u = new User(rs.getInt("userID"), rs.getString("Nickname"), rs.getString("email"), rs.getTimestamp("creationDate"));
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  //R�ckgabe des User
		  return u;
	  }
	  
	  public User findByEmail(User u) {
		  User user = null;
		  //Aufbau der DB-Verbindung
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement findByEmail = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.user " + "WHERE email=?");
			  findByEmail.setString(1, u.getEmail());
			  
			  ResultSet rs = findByEmail.executeQuery();
			  if(rs.next()){
			  user = new User(rs.getInt("userID"), rs.getString("nickname"), rs.getString("email"), rs.getTimestamp("creationDate"));
			  }
			  } catch (SQLException e2) {
			  e2.printStackTrace();
			  return null;
		  }
		  return user;
	  }
	  
	  public User insert(User u) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement insert = con.prepareStatement("INSERT INTO softwarepraktikum_ws1920.user(nickname, email) VALUES(?,?);");
			  
			  insert.setString(1, u.getNickname());
			  insert.setString(2, u.getEmail());
			  
			  insert.executeUpdate();
			  
			  PreparedStatement getnewUser= con.prepareStatement("SELECT *FROM softwarepraktikum_ws1920.user ORDER BY creationDate DESC LIMIT 1;");
			  
			  ResultSet rs = getnewUser.executeQuery();
			  if (rs.next()) {
				  
				  return new User(rs.getInt("userID"), rs.getString("Nickname"), rs.getString("email"), rs.getTimestamp("creationDate"));
			  }
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  return null;
	  }
	  
	  public User updateUser(User user) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement update = con.prepareStatement("UPDATE softwarepraktikum_ws1920.user SET nickname=?;");
			  
			  update.setString(1, user.getNickname());
			  
			  update.executeUpdate();
			  
			  PreparedStatement stmt = con.prepareStatement("SELECT * FROM softwarepraktikum_ws1920.user WHERE 'userID'=?;");
			  
			  update.setInt(1, user.getId());
			  
			  ResultSet rs = stmt.executeQuery();
			  
			  if(rs.next()) {
				  return new User(rs.getInt("userID"), rs.getString("Nickname"), rs.getString("email"), rs.getTimestamp("creationDate"));
			  }
		  } catch (SQLException e) {
			  e.printStackTrace();
			  return null;
		  }
		  return null;
	  }

	public void deleteUserByUserID (User u) {
		  
		  Connection con = DBConnection.getConnection();
		  
		  try {
			  PreparedStatement deleteByUserID = con.prepareStatement("DELETE FROM softwarepraktikum_ws1920.user WHERE userID=?;");
			  deleteByUserID.setInt(1, u.getId());
			  deleteByUserID.executeUpdate();
			  
		  } catch (SQLException e) {
			  e.printStackTrace();
		  }
	  }
}
