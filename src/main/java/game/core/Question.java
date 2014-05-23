package game.core;

import java.util.LinkedList;
import java.util.Random;
import java.util.Collections;

/**
 * Class that represents a question.
 * @see game.core.Answer
 * 
 * @author david
 *
 */
public class Question {

	/**
	 * The actual question.
	 */
	private String question;
	
	/**
	 * The difficulty of the question.
	 */
	private int difficulty;
	
	/**
	 * The type of the question.
	 */
	private String type;
	
	/**
	 * A list of wrong {@link game.core.Answer}s.
	 */
	private LinkedList<Answer> answers = null;
	
	/**
	 * Contains three {@link game.core.Answer}s chosen from {@code answers}, and the {@code correct} answer.
	 */
	private LinkedList<Answer> displayAnswers = null;
	
	/**
	 * The correct {@link game.core.Answer}.
	 */
	private Answer correct; 
	
	/**
	 * Constructor for creating  a {@code Question} object.
	 * 
	 * @param question the {@code Question} in {@code String} format
	 * @param answers a list of {@link game.core.Answer} objects
	 * @param difficulty the difficulty of the question, ranges from {@code 1} to {@code 10}.
	 * @param type the type of the question
	 * @param correct the correct answer
	 */
	public Question(String question, LinkedList<Answer> answers, int difficulty, String type, Answer correct){
		super();
		this.question = question;
		this.answers = answers;
		this.difficulty = difficulty;
		this.type = type;
		this.correct = correct;
		this.correct.setIsCorrect(true);
	}

	/**
	 * Method to get the {@code difficulty} of the {@code Question}.
	 * 
	 * @return the {@code difficulty} of the {@code Question}
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * Method to set the {@code difficulty} of the {@code Question}.
	 * 
	 * @param difficulty the new {@code difficulty}
	 */
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * Method to get the {@code question} of the {@code Question}.
	 * 
	 * @return the {@code question} of the {@code Question}
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * Method to get the {@code type} of the {@code Question}.
	 * 
	 * @return the {@code type} of the {@code Question}.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Method to get the {@code answers} list.
	 * 
	 * @return the {@code answers} list
	 */
	public LinkedList<Answer> getAnswers() {
		return answers;
	}

	/**
	 * Method to get the {@code correct}  {@link game.core.Answer}.
	 * 
	 * @return the {@code correct}  {@link game.core.Answer}
	 */
	public Answer getCorrect(){
		return correct;
	}
	
	/**
	 * Sets the {@code displayAnswers} list. Contains the {@code correct}, and three randomly chosen element of {@code answers}, then shuffled.
	 * 
	 * @throws notEnoughAnswersException if there are less than three {@link game.core.Answer} in the {@code answers} list
	 */
	public void setDisplayAnswers() throws notEnoughAnswersException{
		displayAnswers = new LinkedList<Answer>();
		
	
		displayAnswers.add(correct);
		
		if(answers.size() < 3)
			throw new notEnoughAnswersException();
		
		
		Random r = new Random();
		
		Answer a;
		while(displayAnswers.size() != 4){
			a = answers.get(r.nextInt(answers.size()));
			if(!displayAnswers.contains(a))
				displayAnswers.add(a);
		}
		
		Collections.shuffle(displayAnswers);	
	}
	
	/**
	 * Method to get the previously set {@code displayAnswers} list.
	 * 
	 * @return the set {@code displayAnswers} list
	 */
	public LinkedList<Answer> getDisplayAnswers() {
		return displayAnswers;
	}
	
	/**
	 * Adds an {@link game.core.Answer} to the {@code answers} list.
	 * 
	 * @param answer the {@link game.core.Answer} to add
	 */
	public void addAnswer(String answer){
		answers.add(new Answer(answer));
	}
	
	/**
	 * Removes an {@link game.core.Answer} from {@code answers}.
	 * 
	 * @param answer the {@link game.core.Answer} to be removed
	 * @throws noSuchAnswerException if the given {@link game.core.Answer} is not an element of the {@code answers} list
	 */
	public void removeAnswer(String answer) throws noSuchAnswerException{
		boolean found = false;
		for(int i = 0; i < answers.size(); i++){
			if(answers.get(i).getAnswer().equals(answer)){
				answers.remove(i);
				found = true;
				break;
			}
		}
		if(!found)
			throw new noSuchAnswerException();
	}
	
	
	/**
	 * Prints the {@code question} and the {@code answers} list in the following format:
	 * <pre>
	 * {@code
	 * question :
	 * answer[0]
	 * answer[1]
	 * answer[2]
	 * .
	 * .
	 * .
	 * }
	 * </pre>	
	 */
	public void printAnswer() {
		System.out.println(question + ":");
		System.out.println(correct.toString());
		for(Answer a : answers)
			System.out.println(a.toString());
	}
	
}
