package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.containers.PlayerInventory;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.creatures.Player;
import se.liu.arvra591.objects.creatures.PlayerStats;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * A class that generates a player used in game from a given file
 */
public class PlayerGenerator extends ObjectGenerator<Player>
{
    private Map<String, Factory<? extends Item>> items;
    private Map<String, Location> locations;

    /**
     * The playergenerator constructor
     *
     * @param items     A map containing all the itemfactories in the game
     * @param locations A map containing all the locations in the game
     */
    public PlayerGenerator(Map<String, Factory<? extends Item>> items, Map<String, Location> locations) {
	this.items = items;
	this.locations = locations;
    }

    /**
     * Generates the player from a given file
     *
     * @param fileName The file that the player come from
     *
     * @throws FileNotFoundException
     */
    @Override public void generateObjects(final String fileName) throws FileNotFoundException {
	JsonArray jsonArray = loadJsonArrayFile("player/" + fileName);
	generateObjects(jsonArray);
    }

    @Override protected void generateObject(final JsonObject object) {
	String name = object.get("name").getAsString();
	String desc = object.get("description").getAsString();
	int health = object.get("health").getAsInt();
	PlayerStats stats = generatePlayerStats(object.get("stats").getAsJsonObject());
	Location location = locations.get(object.get("startLocation").getAsString());
	PlayerInventory inventory = new PlayerInventory(generateObjectListFromFactory(object.get("inventory").getAsJsonArray(), items), stats);
	objects.put("player", new Player(name, desc, health, stats, location, inventory));
    }

    private PlayerStats generatePlayerStats(JsonObject object) {
	CreatureStats cStats = generateCreatureStats(object);
	int carryWeight = object.get("carryWeight").getAsInt();
	return new PlayerStats(cStats, carryWeight);
    }
}