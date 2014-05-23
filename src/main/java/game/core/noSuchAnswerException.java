package game.core;

public class noSuchAnswerException extends Exception {

	@Override
	public String getMessage() {
		return "noSuchAnswerException occured!\n" + super.getMessage();
	}
}
