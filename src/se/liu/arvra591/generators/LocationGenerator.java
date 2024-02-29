package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.jsonParser.JsonParser;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationGenerator
{
    protected JsonParser jsonParser = new JsonParser();

    protected Map<String, Location> locations;

    protected Map<String, Factory<Item>> items;
    protected Map<String, Factory<Npc>> npcs;

    public LocationGenerator(Map<String, Factory<Item>> items, Map<String, Factory<Npc>> npcs) {
	jsonParser = new JsonParser();
	locations = new HashMap<>();
    }

    public void genLocations(String fileName) throws IOException {
	JsonArray jsonArray = jsonParser.parseFile(ClassLoader.getSystemResource("locatons/" + fileName).getPath());
	for (JsonElement jsonElement : jsonArray) {
	    genLocation(jsonElement.getAsJsonObject());
	}
    }

    protected void genLocation(JsonObject object){
	String name = object.get("name").getAsString();
	String description = object.get("description").getAsString();
	List<Npc> npcList = genNpcList(object.get("npcs").getAsJsonArray());
	List<Item> itemList = genItemList(object.get("items").getAsJsonArray());
	List<String> stringLocationList = genStringLocationsList(object.get("exits").getAsJsonArray());

	locations.put(name, new Location(name, description, npcList, itemList, stringLocationList));
    }

    protected List<Npc> genNpcList(JsonArray npcArray){
	List<Npc> list = new ArrayList<>();
	npcArray.forEach((npc) -> {
	    list.add(npcs.get(npc.getAsString()).gen());
	});
	return list;
    }

    protected List<Item> genItemList(JsonArray itemArray){
	List<Item> list = new ArrayList<>();
	itemArray.forEach((item) -> {
	    list.add(items.get(item.getAsString()).gen());
	});
	return list;
    }

    protected List<String> genStringLocationsList(JsonArray itemArray){
	List<String> list = new ArrayList<>();
	itemArray.forEach((location) -> {
	    list.add(location.getAsString());
	});
	return list;
    }
}
