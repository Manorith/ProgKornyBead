package hu.unideb.millionare;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import game.core.Answer;
import game.core.Question;
import game.core.noSuchAnswerException;
import game.core.notEnoughAnswersException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class QuestionTest {

	private static String question = "I asketh thee thy question";
	private static String type = "típus";
	private static Question dummyQuestion;
	private static LinkedList<Answer> answers = null;
	private static Answer correct = new Answer("lel");
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void beforeTests() {
		int diff = 1;
		answers = new LinkedList<Answer>();
		answers.add(new Answer("Moses supposes"));
		answers.add(new Answer("his toeses"));

		dummyQuestion = new Question(question, answers, diff, type, correct);
	}

	@Test(expected = notEnoughAnswersException.class)
	public void testSetDisplayAnswer2() throws notEnoughAnswersException {
		dummyQuestion.setDisplayAnswers();

	}


	@Test
	public void testSetDisplayAnswer3() {
		int diff = 1;
		answers = new LinkedList<Answer>();
		answers.add(new Answer("are roses,"));
		answers.add(new Answer("moses supposes"));
		answers.add(new Answer("irronically"));

		dummyQuestion = new Question(question, answers, diff, type, correct);

		try {
			dummyQuestion.setDisplayAnswers();
		} catch (notEnoughAnswersException e1) {
			Assert.fail("notEnoughAnswersException occured");
		}

		assertEquals(dummyQuestion.getDisplayAnswers().size(), 4);

		for (int i = 0; i < 4; i++) {
			Answer a = dummyQuestion.getDisplayAnswers().get(i);
			for (int j = i + 1; j < 4; j++) {
				Answer b = dummyQuestion.getDisplayAnswers().get(j);
				if (a.equals(b))
					Assert.fail("Contains the same answers!");
			}
		}

	}
	
	@Test
	public void testGetDifficulty(){
		assertEquals(dummyQuestion.getDifficulty(), 1);
	}
	
	@Test
	public void testSetDifficulty(){
		dummyQuestion.setDifficulty(10);
		assertEquals(dummyQuestion.getDifficulty(), 10);
	}
	
	@Test
	public void testGetAnswers(){
		assertEquals(dummyQuestion.getAnswers(), answers);
	}
	
	@Test
	public void testAddAnswer(){
		Answer addedOne = new Answer("Stand Alone Complex");
		answers.add(addedOne);
		dummyQuestion.addAnswer("Stand Alone Complex");
		assertEquals(dummyQuestion.getAnswers(), answers);
	}
	
	@Test
	public void testRemoveAnswer(){
		try {
			dummyQuestion.removeAnswer("Moses supposes");
		} catch (noSuchAnswerException e) {
			Assert.fail("noSuchAnswerException occured");
		}
		assertEquals(dummyQuestion.getAnswers(), answers);
	}
	
	@Test(expected = noSuchAnswerException.class)
	public void testRemoveAnswer2() throws noSuchAnswerException{
			dummyQuestion.removeAnswer("I dont even exist:(");
	}
	
	@Test
	public void testPrintAnswer(){
		System.setOut(new PrintStream(outContent));
		
		String s = question + ":\n" + correct + "\n" + "Moses supposes false" + "\n" + "his toeses false" + "\n";
		dummyQuestion.printAnswer();
		assertEquals(outContent.toString(), s);
		
		System.setOut(null);
	}

}