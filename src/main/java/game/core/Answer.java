package game.core;

/**
 * Class that represents an answer to a question.
 * 
 * @author david
 *
 */
public class Answer {
	
	/**
	 * Boolean that represents whether the answers is true or not.
	 */
	private boolean isCorrect = false;
	
	/**
	 * String that contains the answer.
	 */
	private String answer;
	
	/**
	 * The number of votes given to the answer by the audience. Used in {@link game.core.MrVago#audianceHelp()}.
	 */
	private int vote = 0;

	/**
	 * Constructs a {@code Answer} object with default {@code isCorrect} set to {@code false}, a default {@code vote} value set to 0, and a {@code String} as the answer.
	 *
	 *@param answer the {@code String} that represents the answer
	 */
	public Answer(String answer){
		super();
		this.answer = answer;
	}
	
	/**
	 * Method to get the {@code answer}.
	 * 
	 * @return the {@code String} that represents the answer
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * Method to get the value of {@code isCorrect}.
	 * 
	 * @return the current {@code boolean} value of {@code isCorrect}
	 */
	public boolean getIsCorrect() {
		return isCorrect;
	}


	/**
	 * Method to set the value of {@code isCorrect}.
	 * 
	 * @param isCorrect the new {@code boolean} value
	 */
	public void setIsCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	/**
	 * Method to get the value of {@code vote}.
	 * 
	 * @return the  {@code int} value of {@code Vote}
	 */
	public int getVote() {
		return vote;
	}

	/**
	 * Method to set the value of {@code Vote}.
	 * 
	 * @param vote the new {@code vote} value
	 */
	public void setVote(int vote) {
		this.vote = vote;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		return true;
	}

	/**
	 * Returns the string representation of this answer.
	 *
	 * @return the string representation of this event in the form:
	 *         <span><em>answer</em><code> </code><em>isCorrect</em></span>
	 */
	@Override
	public String toString() {
		return answer + " " +  isCorrect;
	}
	
	
}
