package se.liu.arvra591.game.generators;

import com.google.gson.JsonObject;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * A class that generates all the locations used in the game from given files
 */
public class LocationGenerator extends ObjectGenerator<Location>
{

    protected Map<String, Factory<? extends Item>> items;
    protected Map<String, Factory<? extends Npc>> npcs;

    /**
     * The constructor for the locationgenerator class
     *
     * @param items A map containing itemfactories used to generate items for locations
     * @param npcs  A map containing npcfactories used to generate npcsc for locations
     */
    public LocationGenerator(CommandHandler commandHandler, Map<String, Factory<? extends Item>> items,
			     Map<String, Factory<? extends Npc>> npcs)
    {
	super(commandHandler);
	this.items = items;
	this.npcs = npcs;
    }

    @Override protected void generateObject(JsonObject object) {
	String name = object.get("name").getAsString();
	String description = object.get("description").getAsString();
	List<Npc> npcs = generateObjectListFromFactory(object.get("npcs").getAsJsonArray(), this.npcs);
	List<Item> items = generateObjectListFromFactory(object.get("items").getAsJsonArray(), this.items);
	List<String> stringLocations = generateStringListFromJson(object.get("exits").getAsJsonArray());
	List<String> firstEnteredCommands = generateStringListFromJson(object.get("firstEnter").getAsJsonArray());
	List<String> normalEnterCommands = generateStringListFromJson(object.get("normalEnter").getAsJsonArray());

	Location location = new Location(name, description, npcs, items, stringLocations, firstEnteredCommands, normalEnterCommands);

	location.setCommandHandler(commandHandler);
	objects.put(name, location);
    }


    /**
     * A method used to connect rooms to eachother, if not used things will not work
     */
    public void connectRooms() {
	objects.forEach((name, location) -> {
	    List<String> exitStrings = location.getExitStrings();
	    List<Location> exits = generateObjectListFromName(exitStrings, objects);
	    exits.forEach(location::addExit);
	});
    }

    /**
     * generates all the locations from given file
     *
     * @param fileName The name of the file containing locations
     *
     * @throws FileNotFoundException
     */
    @Override public void generateObjects(String fileName) throws FileNotFoundException {
	super.generateObjects("locations" + File.separator + fileName);
    }
}
