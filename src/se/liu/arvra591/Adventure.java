package se.liu.arvra591;

import se.liu.arvra591.inputParsers.InputParser;
import se.liu.arvra591.objects.creatures.Player;

/**
 * The adventure class is the class that controls the adventure mode of the game and the player.
 * It has a parser that takes input from the player and parses it to get the correct command and applies the command to an action
 */
public class Adventure
{
    private Player player;

    private Parser parser;

    /**
     * @param player The player that is playing the game
     */
    public Adventure(Player player){
	this.player = player;
	this.parser = new Parser();
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
    public void printLocation(String input){ //input kommer vara tom? Man kommer bara skriva "location"
	player.getCurrentLocation().printObject(); //vill vi printa hela objektet eller bara namnet?
	System.out.println("Current location: " + player.getCurrentLocation().getName());
    }

    /**
     * @param input The input from the player will be empty
     * Prints the players inventory
     */
    public void checkInventory(String input){
	player.printInventory();
    }

    /**
     * @param input The input from the player
     */
    public void parseInput(String input){
	parser.parseInput(input);
    }

    public void dropItem(String item){
	boolean success = player.dropItem(item);
	if (!success) {
	    System.out.println("Couldnt find " + item + " in your inventory");
	}
	else{
	    System.out.println("You dropped " + item);
	}
    }

    private class Parser extends InputParser
    {
	private Parser(){
	    parseInputs.put("move", Adventure.this::move);
	    parseInputs.put("pickUp", Adventure.this::pickUp);
	    parseInputs.put("inspect", Adventure.this::inspect);
	    parseInputs.put("location", Adventure.this::printLocation);
	    parseInputs.put("checkInventory", Adventure.this::checkInventory);
	    parseInputs.put("drop", Adventure.this::dropItem);
	    //TODO: Add more commands
	}
    }

    public static void main(String[] args){
	Adventure ad = new Adventure(null);
	ad.parseInput("help");
    }
}
