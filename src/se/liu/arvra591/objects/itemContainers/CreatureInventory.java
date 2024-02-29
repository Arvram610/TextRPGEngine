package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.ListHelper;
import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.items.Item;

import java.util.List;
/**
 * Class of the inventory of a creature. The inventory can contain items and can be printed.
 * The inventory can also remove and add items. {@Link PlayerInventory} inherits a creature inventory and can use it to pick up and drop items.
 */
public class CreatureInventory extends ObjectContainer<Item>
{
    /**
     * @param itemList is the list of items in the inventory
     */
    public CreatureInventory(final List<Item> itemList) {
	super(itemList);
    }
}
