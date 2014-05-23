package game.core;

import java.util.Random;

/**
 * Class that represents a member of the Wise Mans.
 * @see game.core.MrVago#wiseManHelp()
 * 
 * @author david
 *
 */
public class WiseMan extends Audience{
	
	/**
	 * Constructs a {@code WiseMan} object.
	 * 
	 * @param type represents the type of question the wise man is expert at
	 */
	public WiseMan(String type) {
		super(type);
	}
	
	/**
	 * Method to set the {@code type}.
	 * 
	 * @param type the {@code type}
	 */
	public void setType(String type){
		this.type = type;
	}
	

	/**
	 *Overrides the {@link game.core.Audience#giveAnswer(Question)} method with a more accurate one.
	 *
	 * @param question the asked {@code Question}
	 * @return an {@code Answer} to the asked {@code Question}
	 */
	@Override
	public Answer giveAnswer(Question question) {
		Random r = new Random();
		Answer answer = question.getDisplayAnswers().get(r.nextInt(question.getDisplayAnswers().size()));
		
		if(question.getType().equals(super.getType()))
			return question.getCorrect();
		
		while(answer.getAnswer().equals("")){
			answer = question.getDisplayAnswers().get(r.nextInt(question.getDisplayAnswers().size()));
		}
		
		return answer;
	}
	
	

	
}
