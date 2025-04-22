package dataDriven;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;
import org.testng.xml.internal.Parser;

public class DDT_using_Json {
	@Test
	public void fetchDataFromJsonFile() throws FileNotFoundException, IOException, ParseException

	{
		JSONParser  parser = new JSONParser();
		Object obj=parser.parse(new FileReader("./src/test/resources/Datadriven.json"));
		JSONObject js= (JSONObject)obj;
		System.out.println(js.get("url"));
		
	}
}
