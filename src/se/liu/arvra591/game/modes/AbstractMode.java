package se.liu.arvra591.game.modes;

import se.liu.arvra591.game.objects.creatures.Player;

public abstract class AbstractMode
{
    protected Player player;

    protected AbstractMode(Player player){
	this.player = player;
    }

    /**
     * @param input The input from the player will be empty
     * Prints the players inventory
     */
    public void printInventory(String input){
	player.printInventory();
    }

    /**
     * @param input The input from the player will be empty
     */
    public void printStats(String ignored){
	player.printStats();
    }

}
