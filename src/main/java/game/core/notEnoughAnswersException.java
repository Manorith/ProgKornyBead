package game.core;

public class notEnoughAnswersException extends Exception {

	@Override
	public String getMessage() {
		return "notEnoughAnswersException occured!\n" + super.getMessage();
	}
}
