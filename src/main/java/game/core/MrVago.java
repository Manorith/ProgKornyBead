package game.core;

import game.data.LOGG;
import game.data.PlayerDOM;
import game.data.QuestionDOM;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/**
 * A static class that controls many game-flow aspects.
 * 
 * @author david
 *
 */
public class MrVago {

	/**
	 * The audience containing 30  {@link game.core.Audience} members.
	 */
	private static LinkedList<Audience> audiance = null;
	
	/**
	 * All the possible {@link game.core.Question}s to be asked.
	 */
	private static LinkedList<Question> questions = null;
	
	/**
	 * All the already asked questions during the game.
	 */
	private static LinkedList<Question> askedQuestions = null;
	
	/**
	 * The actual {@link game.core.Question}.
	 */
	private static Question actQuestion;
	
	/**
	 * All the prizes that can be win.
	 */
	private static int prizes[] = new int[16];
	
	/**
	 * All the question types. Does not contain a {@code type} more than once.
	 */
	private static LinkedList<String> types = null;
		
	/**
	 * The number of the current round.
	 */
	private static int round;
	

	/**
	 * {@link game.core.WiseMan} and {@link game.core.Player} objects used for the Wise Man help.
	 * 
	 * @see wiseManHelp()
	 */
	private static WiseMan wiseMan1 = new WiseMan("no type");
	private static WiseMan wiseMan2 = new WiseMan("no type");
	private static Player previousPlayer;
	
	/**
	 * List of the Wise Man's answers.
	 */
	private static LinkedList<Answer> wiseAnswers = null;

	/**
	 * Represents the current player.
	 */
	private static Player currentPlayer;
	
	/**
	 * File names, where the asked questions and the previous player is contained.
	 */
	private static String prevPlayerFile;
	private static String questionFileName;
	
	/**
	 * Readers for {@code xml} input.
	 * 
	 * @see game.data.PlayerDOM
	 * @see game.data.QuestionDOM
	 */
	private static PlayerDOM reader2;
	private static QuestionDOM reader;

	/**
	 * Method for basic initializing steps.
	 */
	public static void basicInit(){
		setQuestions(questionFileName);
		askedQuestions = new LinkedList<Question>();
		setTypes();
		
		LOGG.logInfo("Basic initialization done.");
	}
	
	/**
	 * Method to initialize the game.
	 * 
	 * @param currName the current player's name
	 * @param wise1 the {@code type} of {@code wiseMan1}
	 * @param wise2 the {@code type} of {@code wiseMan2}
	 * @param prizeI a number to set the {@code prizes} array
	 * @see setPrizes(int)
	 */
	public static void gameInit(String currName, String wise1, String wise2, int prizeI){
		round = 0;
		setCurrentPlayer(currName);
		setPreviousPlayer(prevPlayerFile);
		setPrizes(prizeI);
		setAudience();
		setWiseMan(wise1, wise2);
		
		LOGG.logInfo("Game initialization done.\n");
	}
	
	/**
	 * Method to set the {@code questions} list. All data is stored in an {@code xml} file. There must be at least 15 questions, with a difficulty range of 10.
	 * They are contained in {@code questions} in a random order.
	 * If any of the criterias are not met, {@code missingDifficultyException} or {@code notEnoughQustionsException} exceptions can be thrown.
	 * 
	 * @param fileName an {@code xml} file name that contains the data for all questions 
	 */
	public static void setQuestions(String fileName){
		questions = new LinkedList<Question>();
		reader = new QuestionDOM(fileName);
		questions = reader.getQuestionList();
		
		try {
			if (questions.size() < 15)
				throw new notEnoughQustionsException();

			int diff = 1;
			for (int i = 1; i <= 10; i++) {
				for (int j = 0; j < questions.size(); j++) {
					if (questions.get(j).getDifficulty() == i) {
						diff++;
						break;
					}
				}
			}
			if (diff < 10)
				throw new missingDifficultyException();
		} catch (missingDifficultyException | notEnoughQustionsException e) {
			LOGG.logError(e.getMessage());
			e.printStackTrace();
		}
		
		Collections.shuffle(questions);
		
		LOGG.logInfo("Questions set, and shuffled.");
	}
	
	/**
	 * Method that updates {@code round}, {@code actQuestion} and {@code currentPlayer}.
	 */
	public static void update(){
		try{
			round++;
			setActQuestion(getQuestion(round));
			actQuestion.setDisplayAnswers();
			currentPlayer.setScore(prizes[round-1]);
		} catch(notEnoughAnswersException e){ 
			LOGG.logError(e.getMessage());
			e.printStackTrace();
		}
		
		LOGG.logInfo("Game updated!");
	}
	
	
	/**
	 * Method to set the {@code actQuestion}.
	 * 
	 * @param actQuestion the new {@code Question}
	 */
	public static void setActQuestion(Question actQuestion) {
		MrVago.actQuestion = actQuestion;
	}

	/**
	 * Method that sets the {@code currentPlayer}'s {@code score} and saves one's data.
	 */
	public static void fail(){
		if (round < 6) {
			currentPlayer.setScore(MrVago.getPrizes()[0]);
		}
		if (round >= 6 && round < 11) {
			currentPlayer.setScore(MrVago.getPrizes()[5]);
		}
		if (round >= 11) {
			currentPlayer.setScore(MrVago.getPrizes()[10]);
		}
		
		saveCurrentPlayer(prevPlayerFile);
		
		LOGG.logInfo("The player gave a false answer." + " " + "The question was: " + actQuestion.getQuestion());
	}
	
	/**
	 * Method to set {@code prevPlayerFile}.
	 * 
	 * @param fileName the name of the {@code xml} file
	 */
	public static void setPrevPlayerFile(String fileName){
		prevPlayerFile = fileName;
	}
	
	/**
	 * Method to set {@code questionFileName}.
	 * 
	 * @param fileName he name of the {@code xml} file
	 */
	public static void setQuestionFile(String fileName){
		questionFileName = fileName;
	}
	
	/**
	 * Method to set {@code previousPlayer}.
	 * 
	 * @param fileName the file name he name of the {@code xml} file
	 * @see setPrevPlayerFile(String)
	 */
	public static void setPreviousPlayer(String fileName){
		reader2 = new PlayerDOM(fileName);
		previousPlayer = reader2.getPPlayer();
		
		LOGG.logInfo("Previous player set. Name: " + previousPlayer.getName());
	}
	
	/**
	 * Method to set {@code currentPlayer}.
	 * 
	 * @param name the name of the current player
	 */
	public static void setCurrentPlayer(String name){
		currentPlayer = new Player(name);
		
		LOGG.logInfo("Current player set. Name: " + currentPlayer.getName());
	}
	
	/**
	 * Method to save the {@code currentPlayer}'s data.
	 * 
	 * @param fileName the file name to save
	 * @see setPrevPlayerFile(String)
	 */
	public static void saveCurrentPlayer(String fileName){
		reader2 = new PlayerDOM(fileName);
		reader2.playerSave(currentPlayer);
		
		LOGG.logInfo("Current player saved.");
	}
	

	/**
	 * Sets {@code wiseMan1} and {@code wiseMan2}.
	 * 
	 * @param type1 the type of the first Wise Man
	 * @param type2 the type of the second Wise Man
	 */
	public static void setWiseMan(String type1, String type2) {
		wiseMan1.setType(type1);
		wiseMan2.setType(type2);
		
		LOGG.logInfo("Wise Man 1 and 2 set. Their types: " + wiseMan1.getType() + " " + wiseMan2.getType());
	}
	
	/**
	 * Method to set the {@code prizes} array.
	 * 
	 * @param prizeIncrease a value
	 */
	public static void setPrizes(int prizeIncrease){
		prizes[0] = 0;
		for (int i = 1; i < 16; i++) {
			if (i < 5)
				prizes[i] = prizeIncrease * (i + 1);
			if (i < 10 && i >= 5)
				prizes[i] = prizeIncrease * 2 * (i + 1);
			if (i >= 10)
				prizes[i] = prizeIncrease * 3 * (i + 1);
		}
		
		LOGG.logInfo("Prizes set.");
	}

	/**
	 * Method to get the {@code prizes} array.
	 * 
	 * @return the {@code prizes} array
	 */
	public static int[] getPrizes() {
		return prizes;
	}
	
	/**
	 * Method to set the {@code types} list.
	 */
	public static void setTypes(){
		types = new LinkedList<String>();
		for (int i = 0; i < questions.size(); i++) {
			String tmp = questions.get(i).getType();
			if (!types.contains(tmp))
				types.add(tmp);
		}
		
		LOGG.logInfo("Types set.");
	}
	
	/**
	 * Returns the types in a {@code String} array.
	 * 
	 * @return a {@code String[]}
	 */
	public static String[] getTypes(){
		int size = types.size();
		String[] s = new String[size];
		for(int i = 0; i < size; i++)
			s[i] = types.get(i);
		
		return s;
	}
	
	/**
	 * Method to set the {@code audience}.
	 * 
	 * @param types the {@code types} list
	 */
	public static void setAudience(){
		audiance = new LinkedList<Audience>();
		for (int i = 0; i < 30; i++) {
			Random r = new Random();
			audiance.add(new Audience(types.get(r.nextInt(types.size()))));
		}
		
		LOGG.logInfo("The audience is all fired up. MrVago is pleased.");
	}
	
	/**
	 * Method to get the {@code audience}.
	 * 
	 * @return the {@code audience}
	 */
	public static LinkedList<Audience> getAudiance() {
		return audiance;
	}

	/**
	 * Method to get the {@code wiseAnswers} list.
	 * 
	 * @return the {@code wiseAnswers} list
	 */
	public static LinkedList<Answer> getWiseAnswers() {
		return wiseAnswers;
	}

	/**
	 * Method to get the {@code currentPlayer}.
	 * 
	 * @return the {@code currentPlayer}
	 */
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Method to get the {@code round}.
	 * 
	 * @return the {@code round}
	 */
	public static int getRound() {
		return round;
	}

	/**
	 * Method to set the {@code round}.
	 * 
	 * @param round the new {@code round} value
	 */
	public static void setRound(int round) {
		MrVago.round = round;
	}

	/**
	 * Method to get the {@code questions} list.
	 * 
	 * @return the {@code questions} list
	 */
	public static LinkedList<Question> getQuestions() {
		return questions;
	}
	
	/**
	 * Method to get the {@code actQuestion}.
	 * 
	 * @return the {@code actQueston}
	 */
	public static Question getActQuestion() {
		return actQuestion;
	}
	
	/**
	 * Method to get the {@code wiseMan1}.
	 * 
	 * @return the {@code wiseMan1}
	 */
	public static WiseMan getWiseMan1() {
		return wiseMan1;
	}

	/**
	 * Method to get the {@code wiseMan2}.
	 * 
	 * @return the {@code wiseMan2}
	 */
	public static WiseMan getWiseMan2() {
		return wiseMan2;
	}
	
	/**
	 * Method to get the {@code previousPlayer}.
	 * 
	 * @return the {@code previousPlayer}
	 */
	public static Player getPreviousPlayer() {
		return previousPlayer;
	}

	/**
	 * Gets a randomly chosen question, based on the number of round as difficulty from the {@code questions} list. Then adds that question to the {@code askedQuestions} list.
	 * If there are no question in that difficulty, starts looking for an easier, than a harder question.
	 * 
	 * @param round the number of {@code round}
	 * @return a randomly chosen {@link game.core.Question}
	 */
	public static Question getQuestion(int round) {
		boolean found = false;
		Question question = null;
		int tmp = round;

		while (!found && tmp >= 1) {
			if (tmp < 10) {
				for (int i = 0; i < questions.size(); i++)
					if (questions.get(i).getDifficulty() == tmp) {
						question = questions.get(i);
						found = true;
						askedQuestions.add(questions.remove(i));
						break;
					}
			} else {
				for (int i = 0; i < questions.size(); i++)
					if (questions.get(i).getDifficulty() == 10) {
						question = questions.get(i);
						found = true;
						askedQuestions.add(questions.remove(i));
						break;
					}
			}

			tmp--;
		}
		if (found)
			return question;

		tmp = round + 1;
		while (!found && tmp <= 15) {
			if (tmp < 10) {
				for (int i = 0; i < questions.size(); i++)
					if (questions.get(i).getDifficulty() == tmp) {
						question = questions.get(i);
						found = true;
						askedQuestions.add(questions.remove(i));
						break;
					}
			} else {
				for (int i = 0; i < questions.size(); i++)
					if (questions.get(i).getDifficulty() == 10) {
						question = questions.get(i);
						found = true;
						askedQuestions.add(questions.remove(i));
						break;
					}
			}

			tmp++;
		}

		LOGG.logInfo("An answer was chosen: " + question.getQuestion());
		
		return question;
	}

	/**
	 * Method for the Halfing help. Removes two incorrect {@link game.core.Answer}s from the displayer answers of {@code actQuestion}.
	 */
	public static void halfingHelp() {
		Random r = new Random();
		Answer answer;

			while (actQuestion.getDisplayAnswers().size() != 2) {
				answer = actQuestion.getDisplayAnswers().get(r.nextInt(actQuestion.getDisplayAnswers().size()));
				if(!answer.getIsCorrect())
					actQuestion.getDisplayAnswers().remove(answer);
				
			}
			actQuestion.getDisplayAnswers().add(new Answer(""));
			actQuestion.getDisplayAnswers().add(new Answer(""));

		LOGG.logInfo("Halfing help was used.");
		// question.printDisplay();
	}

	/**
	 * Method for the Audience help. 
	 * 
	 * @see game.core.Audience
	 */
	public static void audianceHelp() {
		
			for (int i = 0; i < 30; i++) {
				String answer = audiance.get(i).giveAnswer(actQuestion).getAnswer();
				for (int j = 0; j < actQuestion.getDisplayAnswers().size(); j++) {
					if (actQuestion.getDisplayAnswers().get(j).getAnswer().equals(answer)) {
						actQuestion.getDisplayAnswers()
								.get(j)
								.setVote(
										actQuestion.getDisplayAnswers().get(j)
												.getVote() + 1);
						break;
					}
				}
			}
		
			LOGG.logInfo("Audience help was used.");
	}
	
	/**
	 * Method for the Wise Man help.
	 * 
	 * @see game.core.WiseMan
	 */
	public static void wiseManHelp() {
		wiseAnswers = new LinkedList<Answer>();
		
			wiseAnswers.add(wiseMan1.giveAnswer(actQuestion));
			wiseAnswers.add(wiseMan2.giveAnswer(actQuestion));
			wiseAnswers.add(previousPlayer.getOneAnswer(actQuestion));

			LOGG.logInfo("Wise Man help was used.");
	}

	
	/**
	 * Returns a {@code String} object of the wise man's choice.
	 * Format:
	 * <pre>
	 * {@code wiseMan1.getType()'s answer: wiseAnswers.get(0).getAnswer()
	 * 	wiseMan2.getType()'s answer: wiseAnswers.get(1).getAnswer()
	 * previousPlayer.getName()'s answer: wiseAnswers.get(2).getAnswer()}
	 * </pre>
	 * 
	 * @see game.core.Player
	 * @see game.core.WiseMan	 
	 * 
	 * @return a String object
	 */
	public static String stringWiseMan(){
		String s = wiseMan1.getType() + "'s answer: " + wiseAnswers.get(0).getAnswer() + "\n"
				+ wiseMan2.getType() + "'s answer: " + wiseAnswers.get(1).getAnswer() + "\n"
				+ previousPlayer.getName() + "'s answer: " + wiseAnswers.get(2).getAnswer();
		return s;
	}
}
