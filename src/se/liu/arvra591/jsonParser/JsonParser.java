package se.liu.arvra591.jsonParser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonParser
{
    protected Gson gson;

    public JsonParser(){
	gson = new Gson();
    }

    public JsonArray parseFile(String path) throws IOException {
	String json;

	try (BufferedReader br = new BufferedReader(
		new FileReader(path))){
	    json = br.readLine();
	} catch (IOException e) {
	    throw e;
	}

	return gson.fromJson(json, JsonArray.class);
    }
}
