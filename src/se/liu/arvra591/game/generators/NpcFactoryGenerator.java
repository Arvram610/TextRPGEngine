package se.liu.arvra591.game.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.containers.CreatureInventory;
import se.liu.arvra591.game.objects.creatures.CreatureStats;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.items.Item;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;

/**
 * A class that generates all the npcfactories used in the game from given files
 */
public class NpcFactoryGenerator extends ObjectGenerator<Factory<? extends Npc>>
{
    private Map<String, Factory<? extends Item>> items;

    /**
     * The constructor for the npcfactorygenerator
     *
     * @param items A map containing all the itemfactories in the game
     */
    public NpcFactoryGenerator(CommandHandler commandHandler, final Map<String, Factory<? extends Item>> items) {
	super(commandHandler);
	this.items = items;
    }

    /**
     * Generates the npcs from given file
     *
     * @param fileName The file that the object come from
     *
     * @throws FileNotFoundException
     */
    @Override public void generateObjects(final String fileName) throws FileNotFoundException {
	JsonArray jsonObjects = loadJsonArrayFile("npcs/" + fileName);
	generateObjects(jsonObjects);
    }

    @Override protected void generateObject(final JsonObject object) {
	String name = object.get("name").getAsString();
	String description = object.get("description").getAsString();
	int health = object.get("health").getAsInt();
	int energy = object.get("energy").getAsInt();
	CreatureStats stats = generateCreatureStats(object.getAsJsonObject("stats"));
	List<List<String>> dialogues = new ArrayList<>();
	object.getAsJsonArray("dialogue").forEach(element -> dialogues.add(generateStringListFromJson(element.getAsJsonArray())));
	CreatureInventory inventory = generateInventory(object.getAsJsonArray("inventory"));
	boolean canDisengage = object.get("canDisengage").getAsBoolean();

	objects.put(name,
		    new Factory<>(new Npc(name, description, health, energy, stats, dialogues, inventory, canDisengage), commandHandler));
    }

    private CreatureInventory generateInventory(final JsonArray jsonObjects) {
	List<Item> items = generateObjectListFromFactory(jsonObjects, this.items);
	return new CreatureInventory(items);
    }
}
