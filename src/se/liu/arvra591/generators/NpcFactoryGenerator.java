package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.containers.CreatureInventory;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.NpcDialogue;
import se.liu.arvra591.objects.items.Item;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

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
    public NpcFactoryGenerator(final Map<String, Factory<? extends Item>> items) {
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
	JsonArray jsonArray = loadJsonArrayFile("npcs/" + fileName);
	generateObjects(jsonArray);
    }

    @Override protected void generateObject(final JsonObject object) {
	String name = object.get("name").getAsString();
	String desc = object.get("description").getAsString();
	int health = object.get("health").getAsInt();
	CreatureStats stats = generateCreatureStats(object.getAsJsonObject("stats"));
	NpcDialogue dialogue = new NpcDialogue(generateStringListFromJson(object.getAsJsonArray("dialogue")));
	CreatureInventory inventory = generateInventory(object.getAsJsonArray("inventory"));

	objects.put(name, new Factory<>(new Npc(name, desc, health, stats, dialogue, inventory)));
    }

    private CreatureInventory generateInventory(final JsonArray array) {
	List<Item> itemList = generateObjectListFromFactory(array, items);
	return new CreatureInventory(generateObjectListFromFactory(array, items));
    }
}
