package se.liu.arvra591.game.modes;

import se.liu.arvra591.game.listeners.EngageEventHandler;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.parsers.InputParser;

/**
 * The adventure class is the class that controls the adventure mode of the game and the player. It has a parser that takes input from the
 * player and parses it to get the correct command and applies the command to an action
 */
public class Adventure extends AbstractMode
{

    private EngageEventHandler eventHandler;

    /**
     * @param player The player that is playing the game
     */
    public Adventure(Player player, EngageEventHandler eventHandler) {
	super(player);
	setParser(new AdventureParser());
	this.eventHandler = eventHandler;
    }

    /**
     * @param location The location to move to
     */
    private void move(String location) {
	boolean success = player.move(location);
	if (!success) {
	    System.out.println("You cannot move to " + location);
	} else {
	    player.getCurrentLocation().roomEntered();
	}
    }

    /**
     * @param item The item to pick up
     */
    private void pickUp(String item) {
	boolean success = player.pickUpItem(item);
	if (!success) {
	    System.out.println("You cannot pick up " + item);
	} else {
	    System.out.println("You picked up " + item);
	}
    }

    /**
     * @param name The name of the object to inspect
     */
    private void inspect(String name) {
	boolean success = player.inspect(name);
	if (!success) {
	    System.out.println("You cannot inspect " + name);
	}
    }

    /**
     * @param ignored The input from the player will be empty
     * Prints the current location of the player
     */
    private void printLocation(String ignored) {
	player.getCurrentLocation().printObject();
    }

    /**
     * @param item The item to drop
     */
    private void dropItem(String item) {
	boolean success = player.dropItem(item);
	if (!success) {
	    System.out.println("Couldnt find " + item + " in your inventory");
	} else {
	    System.out.println("You dropped " + item);
	}
    }

    /**
     * @param input The input from the player will be the name of the npc to talk to
     */
    private void talk(String input) {
	boolean success = player.talkToNpc(input);
	if (!success) {
	    System.out.println("No creature with the name " + input);
	}
    }


    /**
     * @param input The NPC to engage combat with
     */
    private void engage(String input) {
	eventHandler.engage(input);
    }

    private class AdventureParser extends InputParser
    {
	private AdventureParser() {
	    parseInputs.put("move", Adventure.this::move);
	    parseInputs.put("pickup", Adventure.this::pickUp);
	    parseInputs.put("inspect", Adventure.this::inspect);
	    parseInputs.put("location", Adventure.this::printLocation);
	    parseInputs.put("checkinventory", Adventure.this::printInventory);
	    parseInputs.put("inventory", Adventure.this::printInventory);
	    parseInputs.put("drop", Adventure.this::dropItem);
	    parseInputs.put("stats", Adventure.this::printStats);
	    parseInputs.put("talk", Adventure.this::talk);
	    parseInputs.put("engage", Adventure.this::engage);
	    parseInputs.put("equip", Adventure.this::equipItem);
	    parseInputs.put("use", Adventure.this::useItem);
	}
    }

}
