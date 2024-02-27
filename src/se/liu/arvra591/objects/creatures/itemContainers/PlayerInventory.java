package se.liu.arvra591.objects.creatures.itemContainers;

import se.liu.arvra591.objects.creatures.PlayerStats;
import se.liu.arvra591.objects.items.Item;

import java.util.List;

public class PlayerInventory extends AbstractInventory
{
    protected PlayerStats playerStats;

    public PlayerInventory(List<Item> itemList, PlayerStats stats){
	super(itemList);
	this.playerStats = stats;
    }


}
