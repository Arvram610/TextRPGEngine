package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationGenerator extends ObjectGenerator<Location>{

    protected Map<String, Factory<? extends Item>> items;
    protected Map<String, Factory<? extends Npc>> npcs;

    public LocationGenerator(Map<String, Factory<? extends Item>> items, Map<String, Factory<? extends Npc>> npcs) {
	super();
	this.items = items;
	this.npcs = npcs;
    }

    @Override protected void genObject(JsonObject object){
	String name = object.get("name").getAsString();
	String description = object.get("description").getAsString();
	List<Npc> npcList = genObjectListFromFactory(object.get("npcs").getAsJsonArray(), npcs);
	List<Item> itemList = genObjectListFromFactory(object.get("items").getAsJsonArray(), items);
	List<String> stringLocationList = genStringListFromJson(object.get("exits").getAsJsonArray());

	objects.put(name, new Location(name, description, npcList, itemList, stringLocationList));
    }


    public void connectRooms(){
	objects.forEach((name, location) -> {
	    List<String> exitStringList = location.getExitStringList();
	    List<Location> exits = genObjectListFromName(exitStringList, objects);
	    exits.forEach(location::addExit);
	});
    }

    @Override
    public void genObjects(String fileName) throws IOException {
	JsonArray jsonArray = loadJsonArrayFile("locations/" + fileName);
	genObjects(jsonArray);
    }
}
