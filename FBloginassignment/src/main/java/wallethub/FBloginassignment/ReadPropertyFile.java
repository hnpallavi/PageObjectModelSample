package wallethub.FBloginassignment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadPropertyFile {

	private final Properties configProp = new Properties();
	private ReadPropertyFile() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
	      //System.out.println("Reading all properties from the file");
	      try {
	          configProp.load(in);
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	}
	private static class SingleInstance
	   {
	      private static final ReadPropertyFile INSTANCE = new ReadPropertyFile();
	   }
	 
	   public static ReadPropertyFile getInstance()
	   {
	      return SingleInstance.INSTANCE;
	   }
	    
	   public String getProperty(String key){
		   return configProp.getProperty(key);
	   }
}
