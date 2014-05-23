package hu.unideb.millionare;

import static org.junit.Assert.*;

import java.util.LinkedList;

import game.core.Answer;
import game.core.Audience;
import game.core.Question;
import game.core.notEnoughAnswersException;

import org.junit.Assert;
import org.junit.Test;

public class AudienceTest {

	private static String question = "I asketh thee thy question";
	private static String type = "típus";
	private static LinkedList<Answer> answers = new LinkedList<Answer>();
	private static int diff = 1;
	private static Answer correct = new Answer("irronically");

	private static Question dummyQuestion;
	private Audience dummy = new Audience("type");

	@Test
	public void testGiveAnswer() {
		answers.add(new Answer("Moses supposes"));
		answers.add(new Answer("his toeses"));
		answers.add(new Answer("are roses,"));
		answers.add(new Answer("moses supposes"));

		dummyQuestion = new Question(question, answers, diff, type, correct);

		Answer testA = null;
		
		try {
			dummyQuestion.setDisplayAnswers();
		} catch (
				 notEnoughAnswersException e) {
			Assert.fail("Exception occured");
		}
		
		testA = dummy.giveAnswer(dummyQuestion);

		
		assertNotNull(testA);
		if(!testA.getAnswer().equals(dummyQuestion.getCorrect().getAnswer()) && !answers.contains(testA))
			Assert.fail("Answer is not correct!");
	}

}