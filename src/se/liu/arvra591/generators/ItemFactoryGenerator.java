package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.items.Consumables;
import se.liu.arvra591.objects.items.Equippables;
import se.liu.arvra591.objects.items.Item;

import java.io.FileNotFoundException;

/**
 * A class the generates all the itemfactories used in the game from given files
 */
public class ItemFactoryGenerator extends ObjectGenerator<Factory<? extends Item>>
{

    /**
     * Generates itemsfactories from given file
     *
     * @param fileName The name of the file containing items
     *
     * @throws FileNotFoundException
     */
    @Override public void generateObjects(final String fileName) throws FileNotFoundException {
	JsonArray jsonArray = loadJsonArrayFile("items/" + fileName);
	generateObjects(jsonArray);
    }

    @Override protected void generateObject(final JsonObject object) {
	String type = object.get("type").getAsString();
	String name = object.get("name").getAsString();
	String desc = object.get("description").getAsString();
	int weight = object.get("weight").getAsInt();

	switch (type) {
	    case "consumable" -> {
		objects.put(name, new Factory<>(new Consumables(name, desc, weight)));
	    }
	    case "equipable" -> {
		objects.put(name, new Factory<>(new Equippables(name, desc, weight)));
	    }
	    default -> {
		System.out.println("itemtype not valid");
		System.exit(1);
	    }
	}
    }
}
