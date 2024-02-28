package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.objects.creatures.PlayerStats;
import se.liu.arvra591.objects.items.Item;

import java.util.ArrayList;
import java.util.List;

public class PlayerInventory extends CreatureInventory
{
    protected PlayerStats playerStats;

    protected int currentWeight;

    public PlayerInventory(List<Item> itemList, PlayerStats stats){
	super(itemList);
	this.playerStats = stats;
	this.currentWeight = 0;
    }

    @Override
    public boolean addObject(Item item){
	int maxWeight = playerStats.getCarryWeight();
	int weight = item.getWeight();
	if (maxWeight >= weight + getCurrentWeight()){
	    return super.addObject(item);
	}
	return false;
    }

    public int getCurrentWeight() {
	currentWeight = 0;
	for (Item item : objectList) {
	    currentWeight += item.getWeight();
	}
	return currentWeight;
    }


    public static void main(String[] args) {
	List<Item> itemList = new ArrayList<>();
	PlayerStats basicStats = new PlayerStats(10, 10, 10, 10, 30, 10, 10, 10);
	PlayerInventory inventory = new PlayerInventory(itemList, basicStats);
	Item sword = new Item("Sword", "A sword", 10);
	Item shield = new Item("Shield", "A shield", 10);
	inventory.addObject(sword);
	inventory.addObject(shield);
	inventory.addObject(sword);
	inventory.removeObject("Sword");
	inventory.addObject(shield);
	inventory.printContainer();
	inventory.getObject("Sword");
	inventory.getObject("Shield");

    }
}
