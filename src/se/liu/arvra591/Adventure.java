package se.liu.arvra591;

import se.liu.arvra591.inputParsers.AbstractInputParser;
import se.liu.arvra591.objects.creatures.Player;

/**
 * The adventure class is the class that controls the adventure mode of the game and the player.
 * It has a parser that takes input from the player and parses it to get the correct command and applies the command to an action
 */
public class Adventure
{
    private Player player;

    Parser parser = new Parser();

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
	player.move(location);
    }

    /**
     * @param item The item to pick up
     */
    public void pickUp(String item){
	player.pickUpItem(item);
    }

    /**
     * @param input The input from the player
     */
    public void parseInput(String input){
	parser.parseInput(input);
    }

    private class Parser extends AbstractInputParser
    {
	private Parser(){
	    parseInputs.put("move", Adventure.this::move);
	    parseInputs.put("pickUp", Adventure.this::pickUp);
	    //TODO: Add more commands
	}
    }

    public static void main(String[] args){
	Adventure ad = new Adventure(null);
	ad.parseInput("help");
    }
}
