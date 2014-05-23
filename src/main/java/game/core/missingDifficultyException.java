package game.core;

public class missingDifficultyException extends Exception {

	@Override
	public String getMessage() {
		return "missingDifficultyException occured!\n" + super.getMessage();
	}
	
	
}
