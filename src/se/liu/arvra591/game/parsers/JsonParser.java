package se.liu.arvra591.game.parsers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * A class that parses a json file The class uses the gson library to parse the json file
 */
public class JsonParser
{
    protected Gson gson;

    /**
     * The constructor for the JsonParser
     */
    public JsonParser() {
	gson = new Gson();
    }

    /**
     * @param path The path of the file
     *
     * @return Returns a jsonarray containing the data of the file specified in the path
     * @throws IOException
     */
    public JsonArray parseArrayFile(String path) throws IOException {
	StringBuilder json = new StringBuilder();

	try (BufferedReader br = new BufferedReader(
		new InputStreamReader(ClassLoader.getSystemResourceAsStream(path), StandardCharsets.UTF_8))) {
	    br.lines().forEach(json::append);
	}
	return gson.fromJson(json.toString(), JsonArray.class);
    }

    /**
     * @param path The path of the file
     *
     * @return Returns a jsonobject containing the data of the file specified in the path
     * @throws IOException
     */
    public JsonObject parseObjectFile(final String path) throws IOException {
	StringBuilder json = new StringBuilder();

	try (BufferedReader br = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(path)))) {
	    br.lines().forEach(json::append);
	}
	return gson.fromJson(json.toString(), JsonObject.class);
    }
}
