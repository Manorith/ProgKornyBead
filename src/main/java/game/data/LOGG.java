package game.data;


import game.core.Main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LOGG {

	private static Logger logger = LoggerFactory.getLogger(Main.class);
	
	
	public static void logInfo(String s){
		logger.info(s);	
	}
	
	public static void logError(String s){
		logger.error(s);
	}
	
}
