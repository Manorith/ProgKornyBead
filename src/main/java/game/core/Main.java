package game.core;

import java.awt.EventQueue;
import java.util.LinkedList;

import game.GUI.GameGUI3;
import game.data.LOGG;
import game.data.PlayerDOM;
import game.data.QuestionDOM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main class.
 * 
 * @author david
 *
 */
public class Main{
	
	/**
	 * The name of the storage files.
	 */
	private static String fileName1 = "questions.xml";
	private static String fileName2 = "pplayer.xml";
	
	/**
	 * The main function.
	 * 
	 * @param args main args
	 */
	public static void main(String[] args) {
		
		LOGG.logInfo("Start up.");
		
		MrVago.setPrevPlayerFile(fileName2);
		MrVago.setQuestionFile(fileName1);
		
		LOGG.logInfo("File names set.");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LOGG.logInfo("Starting up GUI.");
					
					GameGUI3 frame = new GameGUI3();
					frame.setVisible(true);
				} catch (Exception e) {
					LOGG.logError(e.getMessage());
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

}
