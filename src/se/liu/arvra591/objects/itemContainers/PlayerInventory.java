package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.objects.creatures.Player;
import se.liu.arvra591.objects.creatures.PlayerStats;
import se.liu.arvra591.objects.items.Item;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory extends AbstractInventory
{
    protected PlayerStats playerStats;

    protected int currentWeight;

    public PlayerInventory(List<Item> itemList, PlayerStats stats){
	super(itemList);
	this.playerStats = stats;
	this.currentWeight = 0;
    }

    @Override
    public boolean addItem(Item item){
	int maxWeight = playerStats.getCarryWeight();
	int weight = item.getWeight();
	if (maxWeight >= weight + getCurrentWeight()){
	    return super.addItem(item);
	}
	System.out.println("You can't carry that much weight");
	return false;
    }

    public int getCurrentWeight() {
	currentWeight = 0;
	for (Item item : itemList) {
	    currentWeight += item.getWeight();
	}
	return currentWeight;
    }


    public void removeItem(Item item){
	//TODO: Loop through inventory and check if item exists
	itemList.remove(item);
    }

    public Item getItem(Item item){
	//TODO: Loop through inventory and check if item exists
	return item;
    }

    public static void main(String[] args) {
	List<Item> itemList = new ArrayList<>();
	PlayerStats basicStats = new PlayerStats(10, 10, 10, 10, 30, 10, 10, 10);
	PlayerInventory inventory = new PlayerInventory(itemList, basicStats);
	Item sword = new Item("Sword", "A sword", 10);
	Item shield = new Item("Shield", "A shield", 10);
	inventory.addItem(sword);
	inventory.addItem(shield);
	inventory.addItem(sword);
	inventory.removeItem(sword);
	inventory.addItem(shield);
	inventory.printInventory();

    }
}
