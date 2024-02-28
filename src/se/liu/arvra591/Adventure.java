package se.liu.arvra591;

import se.liu.arvra591.inputParsers.AbstractInputParser;
import se.liu.arvra591.objects.creatures.Player;

public class Adventure
{
    private Player player;

    Parser parser = new Parser();
    public Adventure(Player player){
	this.player = player;
	this.parser = new Parser();
    }

    public void move(String location){
	player.move(location);
    }

    public void pickUp(String item){
	player.pickUpItem(item);
    }

    public void parseInput(String input){
	parser.parseInput(input);
    }

    private class Parser extends AbstractInputParser
    {
	private Parser(){
	    parseInputs.put("move", Adventure.this::move);
	    parseInputs.put("pickUp", Adventure.this::pickUp);
	}
    }

    public static void main(String[] args){
	Adventure ad = new Adventure(null);
	ad.parseInput("help");
    }
}
