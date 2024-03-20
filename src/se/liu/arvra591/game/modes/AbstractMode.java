package se.liu.arvra591.game.modes;

import se.liu.arvra591.game.listeners.EngageEventHandler;
import se.liu.arvra591.game.objects.containers.CreatureInventory;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.items.Equipable;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.parsers.InputParser;

import java.util.Objects;

/**
 * All modes in the game inherit from this class It contains the player and the methods that are common for all modes
 */
public abstract class AbstractMode implements Mode
{
    protected Player player;
    protected EngageEventHandler eventHandler;

    private InputParser parser = null;

    protected AbstractMode(Player player, EngageEventHandler eventHandler) {
	this.player = player;
	this.eventHandler = eventHandler;
    }

    protected void setParser(InputParser parser) {
	this.parser = parser;
    }

    /**
     * @param input The input from the player will be empty Prints the players inventory
     */
    public void printInventory(String input) {
	player.printInventory();
    }

    /**
     * @param input The input from the player will be empty
     */
    public void printStats(String ignored) {
	player.printStats();
    }

    /**
     * @param item The item to use
     */
    public void useItem(String item) {
	player.useItem(item);
    }

    /**
     * @param input The item to equip
     */
    public void equipItem(String input) {
	CreatureInventory inventory = player.getInventory();
	Item item = inventory.getObject(input);
	if (item == null) {
	    System.out.println("You don't have that item");
	    return;
	}
	if (Objects.equals(player.getEquippedItem(), item)) {
	    System.out.println("You already have that item equipped");
	    return;
	}
	if (item instanceof Equipable equipable) {
	    player.equipItem(equipable);
	    System.out.println("You equiped " + item.getName());
	    System.out.print("\nIt gave you: \n");
	    equipable.getStats().printStats();
	    return;
	}
	System.out.println("You can't equip that item");
    }

    /**
     * @param input The input from the player
     */
    public void parseInput(String input) {
	parser.parseInput(input);
    }
}
