package se.liu.arvra591.objects.items;

import se.liu.arvra591.objects.AbstractObject;
/**
 * Superclass for items. Items are objects that can be picked up and put in the inventory.
 * They can be used for different purposes, ex consumables, equippables, etc
 * They have a name, description and a weight
 * The weight limits how many items can be carried at a time
 * The class extends {@link AbstractObject}
 * The Item class is inherited by {@link Equippables} and {@link Consumables}
 */
public class Item extends AbstractObject
{
    private int weight;  // Limits how many items can be carried at a time

    public Item(final String name, final String description, final int weight) {
	super(name, description);
	this.weight = weight;
    }

    public int getWeight(){
	return weight;
    }
}
