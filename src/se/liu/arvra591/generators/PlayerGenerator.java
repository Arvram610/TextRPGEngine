package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.containers.PlayerInventory;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.Player;
import se.liu.arvra591.objects.creatures.PlayerStats;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.io.IOException;
import java.util.Map;

public class PlayerGenerator extends ObjectGenerator<Player>
{
    private Map<String, Factory<? extends Item>> items;
    private Map<String, Location> locations;

    public PlayerGenerator(Map<String, Factory<? extends Item>> items, Map<String, Location> locations){
	this.items = items;
	this.locations = locations;
    }

    @Override public void genObjects(final String fileName) throws IOException {
	JsonArray jsonArray = loadJsonArrayFile("player/" + fileName);
	genObjects(jsonArray);
    }

    @Override protected void genObject(final JsonObject object) {
	String name = object.get("name").getAsString();
	String desc = object.get("description").getAsString();
	int health = object.get("health").getAsInt();
	PlayerStats stats = genPlayerStats(object.get("stats").getAsJsonObject());
	Location location = locations.get(object.get("startLocation").getAsString());
	PlayerInventory inventory = new PlayerInventory(
		genObjectListFromFactory(object.get("inventory").getAsJsonArray(), items),
		stats
	);
	objects.put("player", new Player(name, desc, health, stats, location, inventory));
    }

    private PlayerStats genPlayerStats(JsonObject object){
	CreatureStats cStats = genCreatureStats(object);
	int carryWeight = object.get("carryWeight").getAsInt();
	return new PlayerStats(cStats, carryWeight);
    }
}
