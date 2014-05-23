package hu.unideb.millionare;

import static org.junit.Assert.*;

import java.util.LinkedList;

import game.core.MrVago;
import game.core.Question;
import game.data.QuestionDOM;

import org.junit.Assert;
import org.junit.Test;

public class MrVagoTest {

	private String file1 = "test1.xml";
	private String file2 = "test2.xml";
	private String file3 = "testplayer.xml";
	
	private String wiseType = "Socrates";
	
	private LinkedList<Question> questions = null;
	private static QuestionDOM reader;
	
	@Test
	public void testSetCurrentPlayer(){
		MrVago.setCurrentPlayer("dummy");
		assertEquals(MrVago.getCurrentPlayer().getName(), "dummy");
	}
	
	@Test
	public void testSaveCurrentPlayer(){
		MrVago.saveCurrentPlayer(file3);
		
		MrVago.setPreviousPlayer(file3);
		
		assertEquals(MrVago.getPreviousPlayer().getName(), "dummy");
	}
	
	@Test
	public void testSetWiseMan(){
		MrVago.setWiseMan(wiseType, wiseType);
		
		assertEquals(MrVago.getWiseMan1().getType(), "Socrates");
		assertEquals(MrVago.getWiseMan2().getType(), "Socrates");
	}
	
	@Test
	public void testSetPrizes(){
		MrVago.setPrizes(1000);
		
		int[] test = {0, 2000, 3000, 4000, 5000, 12000, 14000, 16000, 18000, 20000, 33000, 36000, 39000, 42000, 45000, 48000};
	
		for(int i = 0; i < test.length; i++)
			if(MrVago.getPrizes()[i] != test[i])
				Assert.fail("Prizes not equal at" + i);
	}
	
	@Test
	public void testSetQuestions(){
		MrVago.setQuestions(file2);
		
		questions = new LinkedList<Question>();
		reader = new QuestionDOM(file2);
		questions = reader.getQuestionList();
		
		for(int i = 0; i < MrVago.getQuestions().size(); i++){
			boolean found = false;
			for(int j = 0; j < questions.size(); j++)
				if(MrVago.getQuestions().get(i).getQuestion().equals(questions.get(j).getQuestion())){
					found = true;
					break;
				}
			if(!found)
				Assert.fail("Question list not equal!");
		}
	}
	
	@Test
	public void testSetTypes(){
		MrVago.setQuestions(file2);
		MrVago.setTypes();
		
		LinkedList<String> types = new LinkedList<String>();
		types.add("type1");
		types.add("type2");
		types.add("type3");
		types.add("type4");
		types.add("type5");
		
		for(int i = 0; i < MrVago.getTypes().length; i++){
			boolean found = false;
			for(int j = 0; j < types.size(); j++){
				if(MrVago.getTypes()[i].equals(types.get(j))){
					found = true;
					break;
				}
			}
			if(!found)
				Assert.fail("Type mismatch");
		}
		
	}
	
	@Test
	public void testSetAudience(){
		MrVago.setQuestions(file2);
		MrVago.setTypes();
		MrVago.setAudience();
		
		for(int i = 0; i < MrVago.getAudiance().size(); i++){
			boolean found = false;
			for(int j = 0; j < MrVago.getTypes().length; j++){
				if(MrVago.getTypes()[j].equals(MrVago.getAudiance().get(i).getType())){
					found = true;
					break;
				}
			}
			if(!found)
				Assert.fail("Audience type mismatch");
		}
	}
	
	@Test
	public void testGetWiseAnswers(){
		assertEquals(MrVago.getWiseAnswers(), null);
	}
	
	@Test
	public void testGetCurrentPlayer(){
		assertEquals(MrVago.getCurrentPlayer().getName(), "dummy");
	}
	
	@Test
	public void testGetRound(){
		assertEquals(MrVago.getRound(), 0);
	}
	
	@Test
	public void testSetRound(){
		MrVago.setRound(12);
		assertEquals(MrVago.getRound(), 12);
	}
	
	@Test
	public void testGetQuestions(){
		questions = new LinkedList<Question>();
		reader = new QuestionDOM(file2);
		questions = reader.getQuestionList();
		
		LinkedList<Question> test2 = new LinkedList<Question>();
		test2 = MrVago.getQuestions();
		
		for(int i = 0; i < MrVago.getQuestions().size(); i++){
			boolean found = false;
			for(int j = 0; j < test2.size(); j++){
				if(MrVago.getQuestions().get(i).equals(test2.get(j))){
					found = true;
					break;
				}
			}
			if(!found)
				Assert.fail("Question mismatch");
		}
	}
	
	@Test
	public void testGetQuestion(){
		MrVago.setQuestionFile(file2);
		MrVago.basicInit();
		
		Question test = null;
		
		MrVago.setRound(1);
		test = MrVago.getQuestion(MrVago.getRound());
		if(!test.getQuestion().equals("Kérdés1") && !test.getQuestion().equals("Kérdés16"))
			Assert.fail(test.getQuestion() + " " + test.getDifficulty());
		
		MrVago.setRound(3);
		
		test = MrVago.getQuestion(MrVago.getRound());
		if(!test.getQuestion().equals("Kérdés3"))
			Assert.fail(test.getQuestion() + " " + test.getDifficulty());
		
		test = MrVago.getQuestion(MrVago.getRound());
		if(!test.getQuestion().equals("Kérdés2"))
			Assert.fail(test.getQuestion() + " " + test.getDifficulty());
		
		MrVago.setRound(1);
		test = MrVago.getQuestion(MrVago.getRound());
		test = MrVago.getQuestion(MrVago.getRound());
		if(!test.getQuestion().equals("Kérdés4") && !test.getQuestion().equals("Kérdés11"))
			Assert.fail(test.getQuestion() + " " + test.getDifficulty());
		
		MrVago.setRound(11);
		test = MrVago.getQuestion(MrVago.getRound());
		if(!test.getQuestion().equals("Kérdés10") && !test.getQuestion().equals("Kérdés15") && !test.getQuestion().equals("Kérdés14"))
			Assert.fail(test.getQuestion() + " " + test.getDifficulty());
		
		test = MrVago.getQuestion(MrVago.getRound());
		test = MrVago.getQuestion(MrVago.getRound());
		test = MrVago.getQuestion(MrVago.getRound());
		if(!test.getQuestion().equals("Kérdés9") && !test.getQuestion().equals("Kérdés13"))
			Assert.fail(test.getQuestion() + " " + test.getDifficulty());
	}
	
	@Test
	public void testHalfingHelp(){
		MrVago.setQuestionFile(file2);
		MrVago.basicInit();
		
		MrVago.setRound(3);
		MrVago.update();
		MrVago.halfingHelp();
		
		int ok = 0;
		for(int i = 0; i < MrVago.getActQuestion().getDisplayAnswers().size(); i++){
			if(MrVago.getActQuestion().getDisplayAnswers().get(i).getAnswer().equals(MrVago.getActQuestion().getCorrect().getAnswer()))
				ok++;
			if(MrVago.getActQuestion().getDisplayAnswers().get(i).getAnswer().equals(""))
				ok++;
		}
		if(ok != 3)
			Assert.fail("Halfing malfunction");
	}
	
	@Test
	public void testAudienceHelp(){
		MrVago.setQuestionFile(file2);
		MrVago.setPrevPlayerFile(file3);
		MrVago.basicInit();
		MrVago.gameInit("dummy", wiseType, wiseType, 1000);
		
		MrVago.setRound(3);
		MrVago.update();
		MrVago.audianceHelp();
		
		int ok = 0;
		for(int i = 0; i < MrVago.getActQuestion().getDisplayAnswers().size(); i++){
			ok += MrVago.getActQuestion().getDisplayAnswers().get(i).getVote();
		}
		
		if(ok < 30)
			Assert.fail(Integer.toString(ok));
	}
	
	@Test
	public void testWiseHelp(){
		MrVago.setQuestionFile(file2);
		MrVago.setPrevPlayerFile(file3);
		MrVago.basicInit();
		MrVago.gameInit("dummy", "type4", "type4", 1000);
		
		MrVago.setRound(7);
		MrVago.update();
		
		MrVago.wiseManHelp();
		
		if(!MrVago.getWiseAnswers().get(0).getAnswer().equals("jovalasz8") && !MrVago.getWiseAnswers().get(1).getAnswer().equals("jovalasz8"))
			Assert.fail(MrVago.getWiseAnswers().get(0) + "\n" + MrVago.getWiseAnswers().get(1));
	}
	
	

}
