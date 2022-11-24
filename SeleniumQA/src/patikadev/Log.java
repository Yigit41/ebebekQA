package patikadev;

import java.util.logging.*;


public class Log {

	static Logger logger = Logger.getLogger(Log.class.getName());

	
	public void info (String message) 
	{
		
		logger.info(message);
		
	}
	
	
	public void warn (String message) 
	{
		logger.warning(message);
		
	}
}
