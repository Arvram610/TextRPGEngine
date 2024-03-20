package se.liu.arvra591.game.generators;

import com.google.gson.JsonObject;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.creatures.CreatureStats;
import se.liu.arvra591.game.objects.items.Consumable;
import se.liu.arvra591.game.objects.items.Equipable;
import se.liu.arvra591.game.objects.items.Item;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * A class the generates all the itemfactories used in the game from given files
 */
public class ItemFactoryGenerator extends ObjectGenerator<Factory<? extends Item>>
{

    protected ItemFactoryGenerator(final CommandHandler commandHandler) {
	super(commandHandler);
    }

    /**
     * Generates itemsfactories from given file
     *
     * @param fileName The name of the file containing items
     *
     * @throws FileNotFoundException
     */
    @Override public void generateObjects(final String fileName) throws FileNotFoundException {
	super.generateObjects("items/" + fileName);
    }

    @Override protected void generateObject(final JsonObject object) {
	String type = object.get("type").getAsString();
	String name = object.get("name").getAsString();
	String description = object.get("description").getAsString();
	int weight = object.get("weight").getAsInt();

	switch (type) {
	    case "consumable" -> {
		List<String> useCommands = generateStringListFromJson(object.get("useCommands").getAsJsonArray());
		objects.put(name, new Factory<>(new Consumable(name, description, weight, useCommands), commandHandler));
	    }
	    case "equipable" -> {
		CreatureStats stats = generateCreatureStats(object.get("stats").getAsJsonObject());
		objects.put(name, new Factory<>(new Equipable(name, description, weight, stats), commandHandler));
	    }
	    default -> {
		System.out.println("itemtype not valid");
		System.exit(1);
	    }
	}
    }
}
