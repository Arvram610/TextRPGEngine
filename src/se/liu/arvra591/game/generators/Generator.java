package se.liu.arvra591.game.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.game.parsers.JsonParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * An abstract class used to generate objects from a json file
 */
public abstract class Generator
{
    protected JsonParser jsonParser; //= new JsonParser();

    protected Generator() {
	jsonParser = new JsonParser();
    }

    private static URL getUrl(final String filepath) throws FileNotFoundException {
	URL url = ClassLoader.getSystemResource(filepath);
	if (url == null) {
	    throw new FileNotFoundException("File: " + filepath + "was not found");
	}
	return url;
    }

    protected <S extends AbstractObject> List<S> generateObjectListFromFactory(JsonArray jsonObjects, Map<String, Factory<? extends S>> map) {
	List<S> objects = new ArrayList<>();
	jsonObjects.forEach((object) -> {
	    objects.add(map.get(object.getAsString()).generate());
	});
	return objects;
    }

    protected <S extends AbstractObject> List<S> generateObjectListFromName(List<String> names, Map<String, S> map) {
	List<S> objects = new ArrayList<>();
	names.forEach((object) -> {
	    objects.add(map.get(object));
	});
	return objects;
    }

    protected List<String> generateStringListFromJson(JsonArray jsonObjects) {
	List<String> strings = new ArrayList<>();
	jsonObjects.forEach((objectName) -> {
	    strings.add(objectName.getAsString());
	});
	return strings;
    }

    protected JsonArray loadJsonArrayFile(String filepath) throws FileNotFoundException {
	URL url = getUrl(filepath);
	JsonArray array = null;
	try {
	    array = jsonParser.parseArrayFile(url.getPath());
	} catch (IOException ignored) {
	    System.out.println("An IOException ocurred");
	    System.exit(1);
	}
	return array;
    }

    protected JsonObject loadJsonObjectFile(String filepath) throws FileNotFoundException {
	URL url = getUrl(filepath);
	JsonObject object = null;
	try {
	    object = jsonParser.parseObjectFile(url.getPath());
	} catch (IOException ignored) {
	    System.out.println("An IOException ocurred");
	    System.exit(1);
	}
	return object;
    }
}
