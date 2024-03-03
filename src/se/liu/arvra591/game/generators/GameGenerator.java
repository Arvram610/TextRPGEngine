package se.liu.arvra591.game.generators;

import com.google.gson.JsonObject;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A class that generates all the objects needed for the game
 */
public class GameGenerator extends Generator
{
    private CommandHandler commandHandler;
    private Map<String, Factory<? extends Item>> items;
    private Map<String, Factory<? extends Npc>> npcs;
    private Map<String, Location> locations;
    private Player player;

    /**
     * The constructor for the gamegenerator
     */
    public GameGenerator(CommandHandler commandHandler) {
	items = new HashMap<>();
	npcs = new HashMap<>();
	locations = new HashMap<>();
	player = null;
	this.commandHandler = commandHandler;
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
	ItemFactoryGenerator itemFactoryGenerator = new ItemFactoryGenerator(commandHandler);
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
	NpcFactoryGenerator npcFactoryGenerator = new NpcFactoryGenerator(commandHandler, items);
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
	LocationGenerator locationGenerator = new LocationGenerator(commandHandler, items, npcs);
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
	PlayerGenerator playerGenerator = new PlayerGenerator(commandHandler, items, locations);
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
