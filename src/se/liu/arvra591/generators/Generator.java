package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.jsonParser.JsonParser;
import se.liu.arvra591.objects.AbstractObject;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Generator
{
    protected JsonParser jsonParser = new JsonParser();

    public Generator() {
	jsonParser = new JsonParser();
    }

    protected <S extends AbstractObject> List<S> genObjectListFromFactory(JsonArray jsonArray, Map<String, Factory<? extends S>> map) {
	List<S> list = new ArrayList<>();
	jsonArray.forEach((object) -> {
	    list.add(map.get(object.getAsString()).gen());
	});
	return list;
    }

    protected <S extends AbstractObject> List<S> genObjectListFromName(List<String> names, Map<String, S> map) {
	List<S> list = new ArrayList<>();
	names.forEach((object) -> {
	    list.add(map.get(object));
	});
	return list;
    }

    protected List<String> genStringListFromJson(JsonArray jsonArray) {
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

    private static URL getUrl(final String filepath) throws FileNotFoundException {
	URL url = ClassLoader.getSystemResource(filepath);
	if (url == null)
	    throw new FileNotFoundException("File: " + filepath + "was not found");
	return url;
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
