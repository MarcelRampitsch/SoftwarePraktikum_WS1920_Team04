package de.hdm.itprojekt.server.db;

public class UserMapper {
	
	private static UserMapper userMapper =null;
	
	protected UserMapper() {
		
	}

	public static UserMapper UserMapper() {
		if (userMapper == null) {
			userMapper = new UserMapper();
		}
		return userMapper;
	}
}
