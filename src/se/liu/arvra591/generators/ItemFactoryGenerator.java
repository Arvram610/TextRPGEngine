package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.items.Consumables;
import se.liu.arvra591.objects.items.Equippables;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.io.IOException;
import java.util.Map;

public class ItemFactoryGenerator extends ObjectGenerator<Factory<? extends Item>>
{

    @Override public void genObjects(final String fileName) throws IOException {
	JsonArray jsonArray = loadJsonFile("items/" + fileName);
	genObjects(jsonArray);
    }

    @Override protected void genObject(final JsonObject object) {
	String type = object.get("type").getAsString();
	String name = object.get("name").getAsString();
	String desc = object.get("description").getAsString();
	int weight = object.get("weight").getAsInt();

	switch (type){
	    case "consumable" -> {
		objects.put(name, new Factory<>(new Consumables(name, desc, weight)));
	    }
	    case "equipable" -> {
		objects.put(name, new Factory<>(new Equippables(name, desc, weight)));
	    }
	    default -> {
		throw new RuntimeException();
	    }
	}
    }

    public static void main(String[] args) {
	ItemFactoryGenerator generator = new ItemFactoryGenerator();
	try {
	    generator.genObjects("testItems.json");
	} catch (IOException e) {
	    throw new RuntimeException(e);
	}

	Map<String, Factory<? extends Item>> items = generator.getObjects();
	Item i = items.get("potion").gen();
	i.printObject();
    }
}
