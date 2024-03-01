package se.liu.arvra591.objects.Containers;

import se.liu.arvra591.objects.items.Item;

import java.util.List;
/**
 * Class of the inventory of a creature. The inventory can contain items and can be printed.
 * The inventory can also remove and add items. {@link PlayerInventory} inherits a creature inventory and can use it to pick up and drop items.
 */
public class CreatureInventory extends ObjectContainer<Item>
{
    /**
     * @param items is the list of items in the inventory
     */
    public CreatureInventory(final List<Item> items) {
	super(items);
    }
}
