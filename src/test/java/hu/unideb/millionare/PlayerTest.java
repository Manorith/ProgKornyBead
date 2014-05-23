package hu.unideb.millionare;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import game.core.Answer;
import game.core.Player;
import game.core.Question;
import game.core.notEnoughAnswersException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private static String name = "Dummy";
	private static Player MrDummy;
	
	private static String question = "I asketh thee thy question";
	private static String type = "típus";
	private static Question dummyQuestion1;
	private static Question dummyQuestion2;
	private static LinkedList<Answer> answers = null;
	private static int diff = 1;
	private static Answer correct;
	
	@Before
	public void beforeTests(){
		MrDummy = new Player(name);
		
		answers = new LinkedList<Answer>();
		answers.add(new Answer("Moses supposes"));
		answers.add(new Answer("his toeses"));
		answers.add(new Answer("are roses,"));
		answers.add(new Answer("moses supposes"));
		correct = new Answer("irronically");
		
		dummyQuestion1 = new Question(question, answers, diff, type, correct);
		dummyQuestion2 = new Question(question, answers, diff, type, correct);
		
		try{
		dummyQuestion1.setDisplayAnswers();
		dummyQuestion2.setDisplayAnswers();
		} catch (notEnoughAnswersException e){
			Assert.fail("Exception occured!");
		}
		
		MrDummy.addKnownAnswer(dummyQuestion1.getQuestion());
	}
	
	@Test
	public void testGetOneAnswer1(){
		Answer test = null;
		

		test = MrDummy.getOneAnswer(dummyQuestion1);

		
		assertNotNull(test);
		if(!dummyQuestion1.getDisplayAnswers().contains(test))
			Assert.fail("Unknown answer");
	}
	
	@Test
	public void testGetOneAnswer2(){
		Answer test = null;
		

		test = MrDummy.getOneAnswer(dummyQuestion2);

		
		assertNotNull(test);
		if(!dummyQuestion1.getDisplayAnswers().contains(test))
			Assert.fail("Unknown answer");
	}
	
	@Test
	public void testGetScore(){
		assertEquals(MrDummy.getScore(), 0);
	}
	
	@Test
	public void testSetScore(){
		MrDummy.setScore(10);
		assertEquals(MrDummy.getScore(), 10);
	}
	
	@Test
	public void testGetKnownQuestions(){
		assertEquals(MrDummy.getKnownQuestions().get(0), dummyQuestion1.getQuestion());
	}
	
	@Test
	public void testGetName(){
		assertEquals(MrDummy.getName(), "Dummy");
	}
	
	@Test
	public void testGetHelp(){
		assertEquals(MrDummy.getHelp(0), true);
	}
	
	@Test
	public void testSetHelp(){
		MrDummy.setHelp(0, false);
		assertEquals(MrDummy.getHelp(0), false);
	}
	

	

}