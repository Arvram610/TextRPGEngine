package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.Player;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameGenerator extends Generator
{
    private Map<String, Factory<? extends Item>> items;
    private Map<String, Factory<? extends Npc>> npcs;
    private Map<String, Location> locations;
    private Player player;

    public GameGenerator(){
        items = new HashMap<>();
        npcs = new HashMap<>();
        locations = new HashMap<>();
        player = null;
    }

    public void generateGame(){
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

        List<String> itemPaths = new ArrayList<>();
        game.getAsJsonArray("items").forEach(item -> itemPaths.add(item.getAsString()));
	generateItems(itemPaths);

        List<String> npcPaths = new ArrayList<>();
        game.getAsJsonArray("npcs").forEach(npc -> npcPaths.add(npc.getAsString()));
        generateNpcs(npcPaths);

        List<String> locationPaths = new ArrayList<>();
        game.getAsJsonArray("locations").forEach(location -> locationPaths.add(location.getAsString()));
        generateLocations(locationPaths);

        String playerPath = game.get("player").getAsString();
        generatePlayer(playerPath);
    }

    private void generateItems(List<String> paths){
        ItemFactoryGenerator itemFactoryGenerator = new ItemFactoryGenerator();
        paths.forEach(path -> {
            try {
                itemFactoryGenerator.genObjects(path);
            } catch (IOException e) {
                System.out.println("Could not find itemfile: items/" + path);
                System.exit(1);
            }
        });
        items = itemFactoryGenerator.getObjects();
    }

    private void generateNpcs(List<String> paths){
        NpcFactoryGenerator npcFactoryGenerator = new NpcFactoryGenerator(items);
        paths.forEach(path -> {
            try {
                npcFactoryGenerator.genObjects(path);
            } catch (IOException e) {
                System.out.println("Could not find npcfile: npcs/" + path);
                System.exit(1);
            }
        });
        npcs = npcFactoryGenerator.getObjects();
    }


    private void generateLocations(List<String> paths){
        LocationGenerator locationGenerator = new LocationGenerator(items, npcs);
        paths.forEach(path -> {
	    try {
		locationGenerator.genObjects(path);
	    } catch (IOException e) {
                System.out.println("Could not find locationfile: location/" + path);
                System.exit(1);
	    }
	});
        locationGenerator.connectRooms();
        locations = locationGenerator.getObjects();
    }

    private void generatePlayer(String path){
        PlayerGenerator playerGenerator = new PlayerGenerator(items, locations);
        try {
            playerGenerator.genObjects(path);
        } catch (IOException e) {
            System.out.println("Could not find playerfile: player/" + path);
            System.exit(1);
        }
        player = playerGenerator.getObjects().get("player");
    }

    public Map<String, Factory<? extends Item>> getItems() {
        return items;
    }

    public Map<String, Factory<? extends Npc>> getNpcs() {
        return npcs;
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public Player getPlayer() {
        return player;
    }
}
