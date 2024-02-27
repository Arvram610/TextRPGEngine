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

    public void printInventory(){
	for (Item item : itemList) {
	    item.printObject();
	}
    }

    public boolean addItem(final Item item) {
	if (itemList.contains(item)) {
	    System.out.println("Item already in inventory");
	    return false;
	}
	itemList.add(item);
	return true;
    }
}
