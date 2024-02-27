package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.objects.items.Item;

import java.util.List;

public abstract class AbstractInventory
{
    protected List<Item> itemList;

    protected AbstractInventory(List<Item> itemList){
	this.itemList = itemList;
    }

    public List<Item> getItemList() {
	return itemList;
    }
}
