package se.liu.arvra591.objects.itemContainers;

import se.liu.arvra591.ListHelper;
import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.items.Item;

import java.util.List;

public class CreatureInventory extends ObjectContainer<Item>
{
    protected CreatureInventory(final List<Item> itemList) {
	super(itemList);
    }
}
