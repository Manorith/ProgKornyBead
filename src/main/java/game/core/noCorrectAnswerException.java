package game.core;

public class noCorrectAnswerException extends Exception {

	@Override
	public String getMessage() {
		return "noCorrectAnswerException occured!\n" + super.getMessage();
	}
}
