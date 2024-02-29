package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.ListHelper;
import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.items.Item;

import java.util.List;

/**
 * Abstract class the inventory of a creature. The inventory can contain items and can be printed.
 * The inventory can also remove and add items. {@Link Player} inherits a creature inventory and can use it to pick up and drop items.
 */
public abstract class CreatureInventory
{
    protected List<Item> itemList;

    protected CreatureInventory(List<Item> itemList){
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

    public Item removeItem(String name){
	AbstractObject object = ListHelper.findObjectInList(itemList, name);
	if (object != null) {
	    itemList.remove((Item) object);
	    return (Item) object;
	}
	return null;
    }

    public Item getItem(String name){
	AbstractObject object = ListHelper.findObjectInList(itemList, name);
	if (object != null) {
	    return (Item) object;
	}
	return null;
    }

    public void forceAddItem(Item item){
	itemList.add(item);
    }

    public boolean addItem(final Item item) {
	if (itemList.contains(item)) {
	    return false;
	}
	forceAddItem(item);
	return true;
    }
}
