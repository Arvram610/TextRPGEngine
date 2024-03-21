package se.liu.arvra591.game.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.game.parsers.JsonParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * An abstract class used to generate objects from a json file
 */
public abstract class Generator
{
    protected JsonParser jsonParser;

    protected CommandHandler commandHandler;

    protected Generator(CommandHandler commandHandler) {
	jsonParser = new JsonParser();
	this.commandHandler = commandHandler;
    }

    protected <S extends AbstractObject> List<S> generateObjectListFromFactory(JsonArray jsonObjects, Map<String, Factory<? extends S>> map)
    {
	List<S> objects = new ArrayList<>();
	jsonObjects.forEach((object) -> objects.add(map.get(object.getAsString()).generate()));
	return objects;
    }

    protected <S extends AbstractObject> List<S> generateObjectListFromName(List<String> names, Map<String, S> map) {
	List<S> objects = new ArrayList<>();
	names.forEach((object) -> objects.add(map.get(object)));
	return objects;
    }

    protected List<String> generateStringListFromJson(JsonArray jsonObjects) {
	List<String> strings = new ArrayList<>();
	jsonObjects.forEach((objectName) -> strings.add(objectName.getAsString()));
	return strings;
    }

    protected JsonArray loadJsonArrayFile(String filePath) throws IOException, FileNotFoundException {
	JsonArray arr = jsonParser.parseArrayFile(filePath);
	return arr;
    }

    protected JsonObject loadJsonObjectFile(String filePath) throws IOException, FileNotFoundException {
	JsonObject obj = jsonParser.parseObjectFile(filePath);
	return obj;
    }
}
