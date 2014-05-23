package game.core;

import java.util.Random;


/**
 * Class that represents a member of the audience.
 * @see game.core.MrVago#audianceHelp()
 * 
 * @author david
 *
 */
public class Audience {
	
	/**
	 * The type of question this particular person has knowledge in.
	 */
	protected String type;

	/**
	 * Constructor for creating an {@code Audience} object.
	 * 
	 * @param type the type of questions the person has knowledge in
	 */
	public Audience(String type) {
		super();
		this.type = type;
	}
	
	/**
	 * Method to get the {@code type}.
	 * 
	 * @return the {@code type}
	 */
	public String getType(){
		return type;
	}
	
	/**
	 * Returns an answer to the given question. The correctness of the answer highly depends on the persons knowledge, and the difficulty of the question.
	 * 
	 * @param question the asked question
	 * @return an answer to the asked question
	 */
	public Answer giveAnswer(Question question){
		Random r = new Random();
		Answer answer = question.getDisplayAnswers().get(r.nextInt(question.getDisplayAnswers().size()));
		
		if(question.getType().equals(type)){
			if(r.nextInt(100) <= (99 - (question.getDifficulty() * 2)))
					return question.getCorrect();
		}
		while(answer.getAnswer().equals("")){
			answer = question.getDisplayAnswers().get(r.nextInt(question.getDisplayAnswers().size()));
		}
			
		return answer;
	}
}
