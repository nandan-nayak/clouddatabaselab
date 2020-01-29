/* class which contains log4jproperties
 * */ 
 



package swingsql;

import org.apache.log4j.PropertyConfigurator;

public class Log4JProperties {

	static String log4jConfPath = "E://nandan/swingsql/src/log4j.properties";
	public	static void initialize()
	{
		PropertyConfigurator.configure(log4jConfPath);
		
	}

	
	
}
