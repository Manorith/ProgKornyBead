package game.core;

public class notEnoughQustionsException extends Exception {

	@Override
	public String getMessage() {
		return "notEnoughQustionsException occured!\n" + super.getMessage();
	}
}
