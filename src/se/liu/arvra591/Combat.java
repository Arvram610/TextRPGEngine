package se.liu.arvra591;

import se.liu.arvra591.parsers.InputParser;
import se.liu.arvra591.objects.creatures.Player;

public class Combat
{
    private Player player;

    private Parser parser;

    /**
     * @param player The player that is playing the game
     */
    public Combat(Player player){
	this.player = player;
	this.parser = new Parser();
    }

    /**
     * @param input The input from the player
     */
    public void parseInput(String input){
	parser.parseInput(input);
    }

    /**
     * @param name Will be empty string
     */
    public void attack(String name){
	System.out.println("You attacked " + name);
    }

    private class Parser extends InputParser
    {
	private Parser(){
	    parseInputs.put("attack", Combat.this::attack);
	}
    }

    public static void main(String[] args){
	Combat combat= new Combat(null);
	combat.parseInput("help");
    }

}
