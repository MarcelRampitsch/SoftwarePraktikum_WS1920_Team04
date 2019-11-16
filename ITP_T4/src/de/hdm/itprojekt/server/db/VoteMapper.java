package de.hdm.itprojekt.server.db;

public class VoteMapper {

	
	private static VoteMapper voteMapper = null;

	
	
	public VoteMapper() {
		
	}
	
	
	public static VoteMapper PresentationMapper() {
		if (voteMapper == null) {
			voteMapper = new VoteMapper();
		}
		return voteMapper;
	}
}
