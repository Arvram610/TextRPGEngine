package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.objects.creatures.PlayerStats;
import se.liu.arvra591.objects.items.Item;

import java.util.List;

public class PlayerInventory extends AbstractInventory
{
    protected PlayerStats playerStats;

    protected int currentWeight;

    public PlayerInventory(List<Item> itemList, PlayerStats stats, int currentWeight){
	super(itemList);
	this.playerStats = stats;
	this.currentWeight = 0;
    }

    public void addItem(Item item){
	int maxWeight = playerStats.getCarryWeight();
	int weight = item.getWeight();
	if (maxWeight >= weight + getCurrentWeight()){
	    itemList.add(item);
	}
	else
	    System.out.println("You can't carry that much weight");
    }

    public int getCurrentWeight() {
	for (Item item : itemList) {
	    currentWeight += item.getWeight();
	}
	return currentWeight;
    }


    public void removeItem(Item item){
	itemList.remove(item);
    }
}
