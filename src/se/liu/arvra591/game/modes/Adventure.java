package se.liu.arvra591.game.modes;

import se.liu.arvra591.game.parsers.InputParser;
import se.liu.arvra591.game.objects.containers.PlayerInventory;
import se.liu.arvra591.game.objects.creatures.CreatureStats;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.NpcDialogue;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.creatures.PlayerStats;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The adventure class is the class that controls the adventure mode of the game and the player.
 * It has a parser that takes input from the player and parses it to get the correct command and applies the command to an action
 */
public class Adventure extends AbstractMode
{
    //private Player player;

    private AdventureParser parser;

    /**
     * @param player The player that is playing the game
     */
    public Adventure(Player player){
	super(player);
	this.parser = new AdventureParser();
    }

    /**
     * @param location The location to move to
     */
    public void move(String location){
	boolean success = player.move(location);
	if (!success) {
	    System.out.println("You cannot move to " + location);
	}
	else {
	    player.getCurrentLocation().printObject();
	    }
    }

    /**
     * @param item The item to pick up
     */
    public void pickUp(String item){
	boolean success = player.pickUpItem(item);
	if (!success) {
	    System.out.println("You cannot pick up " + item);
	}
	else {
	    System.out.println("You picked up " + item);
	}
    }

    /**
     * @param name The name of the object to inspect
     */
    public void inspect(String name){
	boolean success = player.inspect(name);
	if (!success) {
	    System.out.println("You cannot inspect " + name);
	}
    }

    /**
     * @param input The input from the player will be empty
     * Prints the current location of the player
     */
    public void printLocation(String input){ //input kommer vara tom. Man kommer bara skriva "location"
	player.getCurrentLocation().printObject();
    }

    /**
     * @param input The input from the player
     */
    public void parseInput(String input){
	parser.parseInput(input);
    }

    /**
     * @param item The item to drop
     */
    public void dropItem(String item){
	boolean success = player.dropItem(item);
	if (!success) {
	    System.out.println("Couldnt find " + item + " in your inventory");
	}
	else{
	    System.out.println("You dropped " + item);
	}
    }

    /**
     * @param input The input from the player will be the name of the npc to talk to
     */
    public void talk(String input){
	boolean success = player.talkToNpc(input);
	if (!success) {
	    System.out.println("No creature with the name " + input);
	}
    }

    private class AdventureParser extends InputParser
    {
	private AdventureParser(){
	    parseInputs.put("move", Adventure.this::move);
	    parseInputs.put("pickup", Adventure.this::pickUp);
	    parseInputs.put("inspect", Adventure.this::inspect);
	    parseInputs.put("location", Adventure.this::printLocation);
	    parseInputs.put("checkinventory", Adventure.this::printInventory);
	    parseInputs.put("inventory", Adventure.this::printInventory);
	    parseInputs.put("drop", Adventure.this::dropItem);
	    parseInputs.put("stats", Adventure.this::printStats);
	    parseInputs.put("talk", Adventure.this::talk); //input will be "talk npc" where npc is the name of the npc you want to talk to
	}
    }

    public static void main(String[] args){
	List<Npc> npcs = new ArrayList<>();
	List<Item> items = new ArrayList<>();
	List<Location> exits = new ArrayList<>();
	List<Item> itemsInInventory = new ArrayList<>();

	List<Npc> emptyNpcs = new ArrayList<>();

	NpcDialogue npcDialogue = new NpcDialogue(Arrays.asList("Hej!", "Hoppas du mår bra", "Ha en trevlig dag"));
	Npc npc = new Npc("Carl", "A friendly human", 10, 10, CreatureStats.basic, npcDialogue, null);
	Item item = new Item("TestItem", "TestDescription", 10);
	Location testExit = new Location("TestExit", "TestDescription", emptyNpcs, items, exits);

	npcs.add(npc);
	items.add(item);
	exits.add(testExit);

	Location testLocation = new Location("TestLocation", "TestDescription", npcs, items, exits);
	PlayerStats stats = new PlayerStats( 10,10,10,10,10,10,10, 110);

	PlayerInventory inventory = new PlayerInventory(itemsInInventory, stats);
	Player testPlayer = new Player("TestPlayer", "TestDescription", 100, 100, stats, testLocation,
				        inventory);

	Adventure ad = new Adventure(testPlayer);
	//ad.parseInput("help");
	//ad.parseInput("loCATiOn");
	//ad.parseInput("insPect TestItem");

	/*ad.parseInput("pickUp TestItem");
	ad.parseInput("checkInventory");
	ad.parseInput("drop TestItem");
	ad.parseInput("pickUp TestItem");
	ad.parseInput("pickUp TestItem");*/

	//ad.parseInput("stats");
	//ad.parseInput("talk Carl");

	//ad.parseInput("move TestExit");
	//ad.parseInput("location");

	ad.parseInput("inventory");
	ad.parseInput("checkinventory");
	ad.parseInput("stats");
    }
}
