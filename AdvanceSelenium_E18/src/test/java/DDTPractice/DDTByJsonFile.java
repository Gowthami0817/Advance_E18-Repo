package DDTPractice;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DDTByJsonFile {

	public static void main(String[] args) throws IOException, ParseException {
		JSONParser parser=new JSONParser();
		FileReader file=new FileReader("C:\\Users\\91830\\eclipse-workspace\\AdvanceSelenium_E18\\src\\test\\resources\\Data_E18.json");
		Object javaObj = parser.parse(file);
		
		JSONObject obj=(JSONObject)javaObj;
		 String name = obj.get("name").toString();
		 String id = obj.get("id").toString();
		 String branch = obj.get("Branch").toString();
		 String Age = obj.get("Age").toString();
		 String isStudent = obj.get("isStudent").toString();
		 Object backlogs = obj.get("backlogs");
		 
		 System.out.println(name);
		 System.out.println(id);
		 System.out.println(branch);
		 System.out.println(Age);
		 System.out.println(isStudent);
		 System.out.println(backlogs);
		 

	}

}
