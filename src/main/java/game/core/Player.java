package game.core;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

/**
 * Class that represents the player.
 * 
 * @author david
 *
 */
public class Player {

	/**
	 * The score of the player.
	 */
	private int score = 0;
	
	/**
	 * The name of the player.
	 */
	private String name;
	
	/**
	 * List to represent all the questions the player answered correctly. It contains values given by {@link game.core.Question#getQuestion()}.
	 * @see game.core.Question
	 */
	private LinkedList<String> knownAnswers = null;

	/**
	 * Boolean array to represent which helps the player still has.
	 */
	private boolean[] help = new boolean[3];

	/**
	 * Constructs a {@code Player} object.
	 * 
	 * @param name the name of the player
	 */
	public Player(String name) {
		super();
		this.name = name;
		for (int i = 0; i < help.length; i++)
			help[i] = true;
		this.knownAnswers = new LinkedList<String>();
	}

	/**
	 * Method to get the {@code score} of the {@code Player}.
	 * 
	 * @return the {@code score} of the {@code Player}
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Method to set the {@code score} of the {@code Player}.
	 * 
	 * @param score the new {@code score} value
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Method to get the {@code knownAnswers}.
	 * 
	 * @return the {@code LinkedList<String>} list
	 */
	public LinkedList<String> getKnownQuestions() {
		return knownAnswers;
	}

	/**
	 * Method to get the {@code Player}'s {@code name}.
	 * 
	 * @return the {@code Player}'s {@code name}
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Method to get the given element of the {@code help} array.
	 * 
	 * @param i the element to get
	 * @return the {@code boolean} value of the asked element
	 */
	public boolean getHelp(int i){
		return help[i];
	}
	
	/**
	 * Method to set an element of the {@code help} array.
	 * 
	 * @param i the element to be set
	 * @param bool the new the {@code boolean} value
	 */
	public void setHelp(int i, boolean bool){
		help[i] = bool;
	}

	/**
	 * Method to add a known {@link game.core.Question} to the {@code knownAnswers} list.
	 * 
	 * @param question values given by {@link game.core.Question#getQuestion()}
	 */
	public void addKnownAnswer(String question) {
		knownAnswers.add(question);
	}

	/**
	 * Method to get the correct answer to a {@link game.core.Question}. Used in the {@link game.core.MrVago#wiseManHelp(Question)}.
	 * 
	 * @param question the {@link game.core.Question} to be answered
	 * @return the correct answer to the question
	 */
	public Answer getOneAnswer(Question question){
		for (String s : knownAnswers)
			if (question.getQuestion().equals(s)) {
				return question.getCorrect();
			}
		Random r = new Random();
		return question.getDisplayAnswers().get(
				r.nextInt(question.getDisplayAnswers().size()));
	}




}
