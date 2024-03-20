package se.liu.arvra591.game.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.game.parsers.JsonParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * An abstract class used to generate objects from a json file
 */
public abstract class Generator
{
    protected JsonParser jsonParser; //= new JsonParser();

    protected CommandHandler commandHandler;

    private Logger logger = Logger.getLogger("MainLogger");

    protected Generator(CommandHandler commandHandler) {
	jsonParser = new JsonParser();
	this.commandHandler = commandHandler;
    }

    private static URL getUrl(final String filePath) throws FileNotFoundException {
	URL url = ClassLoader.getSystemResource(filePath);
	if (url == null) {
	    throw new FileNotFoundException("File: " + filePath + "was not found");
	}
	return url;
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

    protected JsonArray loadJsonArrayFile(String filePath) throws FileNotFoundException {
	URL url = getUrl(filePath);
	JsonArray arr = null;
	try {
	    arr = jsonParser.parseArrayFile(url.getPath());
	} catch (FileNotFoundException e) {
	    logger.log(Level.SEVERE, e.toString());
	    System.out.println("An FileNotFoundException ocurred");
	    System.out.println(Arrays.toString(e.getStackTrace()));
	    System.exit(1);
	} catch (IOException e) {
	    logger.log(Level.SEVERE, e.toString());
	    System.out.println("An IOException ocurred");
	    System.out.println(Arrays.toString(e.getStackTrace()));
	    System.exit(1);
	}
	return arr;
    }

    protected JsonObject loadJsonObjectFile(String filePath) throws FileNotFoundException {
	URL url = getUrl(filePath);
	JsonObject obj = null;
	try {
	    obj = jsonParser.parseObjectFile(url.getPath());
	} catch (FileNotFoundException e) {
	    logger.log(Level.SEVERE, e.toString());
	    System.out.println("An FileNotFoundException ocurred");
	    System.out.println(Arrays.toString(e.getStackTrace()));
	    System.exit(1);
	} catch (IOException e) {
	    logger.log(Level.SEVERE, e.toString());
	    System.out.println("An IOException ocurred");
	    System.out.println(Arrays.toString(e.getStackTrace()));
	    System.exit(1);
	}
	return obj;
    }
}
