package hu.unideb.millionare;

import static org.junit.Assert.*;
import game.core.Answer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnswerTest {
	private static String testAnswer = "dummy123";
	private static Answer dummy;
	
	@Before
	public void beforeAllTests(){
		dummy = new Answer(testAnswer);
	}
	
	@Test
	public void testConstructor() {
		assertNotNull(dummy);
		assertEquals(dummy.getAnswer(), testAnswer);
		assertFalse(dummy.getIsCorrect());
		assertEquals(dummy.getVote(), 0);
	}
	
	@Test
	public void testSetIsCorrect(){
		dummy.setIsCorrect(true);
		assertNotNull(dummy);
		assertTrue(dummy.getIsCorrect());
	}
	
	@Test
	public void testSetVote(){
		int vote = 5;
		dummy.setVote(vote);
		assertEquals(dummy.getVote(), vote);
	}
	
	@Test
	public void testToString(){
		assertEquals(dummy.toString(), "dummy123 false");
	}
	
}