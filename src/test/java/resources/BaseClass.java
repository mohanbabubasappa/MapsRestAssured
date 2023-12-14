package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {

	public static RequestSpecification reqSpec;
	
	JsonPath jsp;

	public RequestSpecification requestSpec() throws IOException
	{
		if(reqSpec==null)
		{
			PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
			reqSpec=new RequestSpecBuilder()
					.setBaseUri(getGlobalValue("baseURL"))
					.addQueryParam("key", "qaclick123")
					.setContentType(ContentType.JSON)
					.addHeader("Content-Type","application/json")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.build();
			return reqSpec;
		}
		return reqSpec;		
	}
	
	public String deletePlacePayload(String placeid)
	{
		return "{\r\n"
				+ "    \"place_id\":\\"+placeid+"\r\n"
				+ "}\r\n"
				+ "";
	}
	
	public String getJsonPathResponse(Response response,String key)
	{
		String resp=response.asString();
		jsp=new JsonPath(resp);
		return jsp.getString(key);
	}

	
	public String getGlobalValue(String key) throws IOException
	{
		Properties properties=new Properties();

		FileInputStream fis=new FileInputStream("C://Users//nanie//eclipse-workspace//Maps//src//test//java//resources//Global.properties");

		properties.load(fis);

		return properties.getProperty(key);

	}

}
