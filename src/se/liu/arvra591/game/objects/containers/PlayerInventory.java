package se.liu.arvra591.game.objects.containers;

import se.liu.arvra591.game.objects.creatures.PlayerStats;
import se.liu.arvra591.game.objects.items.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for the inventory of the player. The player inventory can contain items and can be printed.
 * The player inventory can also remove and add items.
 * The player inventory also has a weight limit.
 */
public class PlayerInventory extends CreatureInventory
{
    protected PlayerStats playerStats;

    protected int currentWeight;

    /**
     * @param items is the list of items in the inventory
     * @param stats is the stats of the player which contains the carry weight
     */
    public PlayerInventory(List<Item> items, PlayerStats stats){
	super(items);
	this.playerStats = stats;
	this.currentWeight = 0;
    }

    /**
     * @param item is the object to add to the inventory
     *
     * @return true if the item was added to the inventory, false if the item was not added to the inventory
     */
    @Override
    public boolean addObject(Item item){
	int maxWeight = playerStats.getCarryWeight();
	int weight = item.getWeight();
	if (maxWeight >= weight + getCurrentWeight()){
	    return super.addObject(item);
	}
	return false;
    }

    /**
     * @return Returns the current weight of the items in the inventory
     */
    public int getCurrentWeight() {
	currentWeight = 0;
	for (Item item : objects) {
	    currentWeight += item.getWeight();
	}
	return currentWeight;
    }
}
