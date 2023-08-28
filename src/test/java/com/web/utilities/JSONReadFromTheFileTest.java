package com.web.utilities;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JSONReadFromTheFileTest {
public static void main(String args[])
{
	JSONParser parser = new JSONParser();
    try {
       Object obj = parser.parse(new FileReader("C:\\Users\\RaghavendraD\\Desktop\\sample.json"));
       JSONObject jsonObject = (JSONObject)obj;
       String name = (String)jsonObject.get("name");
      String phonenumber = (String)jsonObject.get("phonenumber");
    
    System.out.println(name);
    System.out.println(phonenumber);
    } catch(Exception e) {
       e.printStackTrace();
    } 
 }


}
