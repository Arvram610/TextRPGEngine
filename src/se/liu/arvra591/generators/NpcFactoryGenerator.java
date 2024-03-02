package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.containers.CreatureInventory;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.NpcDialogue;
import se.liu.arvra591.objects.items.Item;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class NpcFactoryGenerator extends ObjectGenerator<Factory<? extends Npc>>
{
    Map<String, Factory<? extends Item>> items;

    public NpcFactoryGenerator(final Map<String, Factory<? extends Item>> items) {
	this.items = items;
    }

    @Override public void genObjects(final String fileName) throws IOException {
	JsonArray jsonArray = loadJsonArrayFile("npcs/" + fileName);
	genObjects(jsonArray);
    }

    @Override protected void genObject(final JsonObject object) {
	String name = object.get("name").getAsString();
	String desc = object.get("description").getAsString();
	int health = object.get("health").getAsInt();
	CreatureStats stats = genCreatureStats(object.getAsJsonObject("stats"));
	NpcDialogue dialogue = new NpcDialogue(genStringListFromJson(object.getAsJsonArray("dialogue")));
	CreatureInventory inventory = genInventory(object.getAsJsonArray("inventory"));

	objects.put(name, new Factory<>(new Npc(name, desc, health, stats, dialogue, inventory)));
    }

    private CreatureInventory genInventory(final JsonArray array) {
	List<Item> itemList = genObjectListFromFactory(array, items);
	return new CreatureInventory(genObjectListFromFactory(array, items));
    }

    public static void main(String[] args) {
	ItemFactoryGenerator itemFactoryGenerator = new ItemFactoryGenerator();
	try {
	    itemFactoryGenerator.genObjects("testItems.json");
	} catch (IOException e) {
	    System.exit(1);
	}
	Map<String, Factory<? extends Item>> items = itemFactoryGenerator.getObjects();

	NpcFactoryGenerator npcFactoryGenerator = new NpcFactoryGenerator(items);
	try {
	    npcFactoryGenerator.genObjects("testNpcs.json");
	} catch (IOException e) {
	    System.exit(1);
	}
	Map<String, Factory<? extends Npc>> npcs = npcFactoryGenerator.getObjects();

	npcs.get("Carl").gen().talk();
    }
}
