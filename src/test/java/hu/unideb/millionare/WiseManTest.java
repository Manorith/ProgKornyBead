package hu.unideb.millionare;

import static org.junit.Assert.*;
import game.core.Answer;
import game.core.Question;
import game.core.WiseMan;
import game.core.notEnoughAnswersException;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class WiseManTest {

	private static String question = "I asketh thee thy question";
	private static String type = "típus";
	private static LinkedList<Answer> answers = new LinkedList<Answer>();
	private static int diff = 1;
	private static Answer correct;

	private static Question dummyQuestion;
	private WiseMan dummy = new WiseMan("type");

	@Test
	public void testGiveAnswer() {
		answers.add(new Answer("Moses supposes"));
		answers.add(new Answer("his toeses"));
		answers.add(new Answer("are roses,"));
		answers.add(new Answer("moses supposes"));
		correct = new Answer("irronically");

		dummyQuestion = new Question(question, answers, diff, type, correct);

		Answer testA = null;
		
		try {
			dummyQuestion.setDisplayAnswers();
		} catch (notEnoughAnswersException e) {
			Assert.fail("Exception occured");
		}
		

		testA = dummy.giveAnswer(dummyQuestion);

		
		assertNotNull(testA);
		if(!testA.getAnswer().equals(dummyQuestion.getCorrect().getAnswer()) && !answers.contains(testA))
			Assert.fail("Answer is not correct!");
	}
	
	@Test
	public void testSetType(){
		dummy.setType("Socrates");
		
		assertEquals(dummy.getType(), "Socrates");
	}

}