package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.jsonParser.JsonParser;
import se.liu.arvra591.objects.AbstractObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ObjectGenerator<T>
{
    protected JsonParser jsonParser = new JsonParser();

    protected Map<String, T> objects;

    protected ObjectGenerator() {
	jsonParser = new JsonParser();
	objects = new HashMap<>();
    }

    public abstract void genObjects(String fileName) throws IOException;

    protected abstract void genObject(JsonObject object);

    protected void genObjects(JsonArray jsonArray){
	for (JsonElement jsonElement : jsonArray) {
	    genObject(jsonElement.getAsJsonObject());
	}
    }

    protected <S extends AbstractObject> List<S> genObjectListFromFactory(JsonArray jsonArray, Map<String, Factory<S>> map){
	List<S> list = new ArrayList<>();
	jsonArray.forEach((object) -> {
	    list.add(map.get(object.getAsString()).gen());
	});
	return list;
    }

    protected <S extends AbstractObject> List<S> genObjectListFromName(List<String> names, Map<String, S> map){
	List<S> list = new ArrayList<>();
	names.forEach((object) -> {
	    list.add(map.get(object));
	});
	return list;
    }

    protected List<String> genStringListFromJson(JsonArray jsonArray){
	List<String> list = new ArrayList<>();
	jsonArray.forEach((objectName) -> {
	    list.add(objectName.getAsString());
	});
	return list;
    }

    public Map<String, T> getObjects(){
	return objects;
    }
}
