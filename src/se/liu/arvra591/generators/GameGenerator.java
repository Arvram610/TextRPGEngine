package se.liu.arvra591.generators;

import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.Player;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that generates all the objects needed for the game
 */
public class GameGenerator extends Generator
{
    private Map<String, Factory<? extends Item>> items;
    private Map<String, Factory<? extends Npc>> npcs;
    private Map<String, Location> locations;
    private Player player;

    /**
     * The constructor for the gamegenerator
     */
    public GameGenerator() {
	items = new HashMap<>();
	npcs = new HashMap<>();
	locations = new HashMap<>();
	player = null;
    }

    /**
     * The method used to initialize everything
     */
    public void generateGame() {
	String gamePath = null;
	try {
	    gamePath = loadJsonObjectFile("game.json").get("game").getAsString();
	} catch (IOException e) {
	    System.out.println("Could not find game.json");
	    System.exit(1);
	}
	JsonObject game = null;
	try {
	    game = loadJsonObjectFile("games/" + gamePath);
	} catch (IOException e) {
	    System.out.println("Could not find gamefile: games/" + gamePath);
	    System.exit(1);
	}

	generateItems(generateStringListFromJson(game.getAsJsonArray("items")));

	generateNpcs(generateStringListFromJson(game.getAsJsonArray("npcs")));

	generateLocations(generateStringListFromJson(game.getAsJsonArray("locations")));

	String playerPath = game.get("player").getAsString();
	generatePlayer(playerPath);
    }

    private void generateItems(List<String> paths) {
	ItemFactoryGenerator itemFactoryGenerator = new ItemFactoryGenerator();
	paths.forEach(path -> {
	    try {
		itemFactoryGenerator.generateObjects(path);
	    } catch (IOException e) {
		System.out.println("Could not find itemfile: items/" + path);
		System.exit(1);
	    }
	});
	items = itemFactoryGenerator.getObjects();
    }

    private void generateNpcs(List<String> paths) {
	NpcFactoryGenerator npcFactoryGenerator = new NpcFactoryGenerator(items);
	paths.forEach(path -> {
	    try {
		npcFactoryGenerator.generateObjects(path);
	    } catch (IOException e) {
		System.out.println("Could not find npcfile: npcs/" + path);
		System.exit(1);
	    }
	});
	npcs = npcFactoryGenerator.getObjects();
    }


    private void generateLocations(List<String> paths) {
	LocationGenerator locationGenerator = new LocationGenerator(items, npcs);
	paths.forEach(path -> {
	    try {
		locationGenerator.generateObjects(path);
	    } catch (IOException e) {
		System.out.println("Could not find locationfile: location/" + path);
		System.exit(1);
	    }
	});
	locationGenerator.connectRooms();
	locations = locationGenerator.getObjects();
    }

    private void generatePlayer(String path) {
	PlayerGenerator playerGenerator = new PlayerGenerator(items, locations);
	try {
	    playerGenerator.generateObjects(path);
	} catch (IOException e) {
	    System.out.println("Could not find playerfile: player/" + path);
	    System.exit(1);
	}
	player = playerGenerator.getObjects().get("player");
    }

    /**
     * @return Returns a map with all the itemfactories
     */
    public Map<String, Factory<? extends Item>> getItems() {
	return items;
    }

    /**
     * @return Returns a map with all the npcfactories
     */
    public Map<String, Factory<? extends Npc>> getNpcs() {
	return npcs;
    }

    /**
     * @return Returns a map with all the locations
     */
    public Map<String, Location> getLocations() {
	return locations;
    }

    /**
     * @return Returns the player of the game
     */
    public Player getPlayer() {
	return player;
    }
}
