package se.liu.arvra591.game.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.parsers.JsonParser;

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
    protected JsonParser jsonParser = new JsonParser();

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

    protected <S extends AbstractObject> List<S> generateObjectListFromFactory(JsonArray jsonArray, Map<String, Factory<? extends S>> map) {
	List<S> list = new ArrayList<>();
	jsonArray.forEach((object) -> {
	    list.add(map.get(object.getAsString()).generate());
	});
	return list;
    }

    protected <S extends AbstractObject> List<S> generateObjectListFromName(List<String> names, Map<String, S> map) {
	List<S> list = new ArrayList<>();
	names.forEach((object) -> {
	    list.add(map.get(object));
	});
	return list;
    }

    protected List<String> generateStringListFromJson(JsonArray jsonArray) {
	List<String> list = new ArrayList<>();
	jsonArray.forEach((objectName) -> {
	    list.add(objectName.getAsString());
	});
	return list;
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
