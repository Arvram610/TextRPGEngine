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
    /**
    * Limits how many items can be carried at a time
     */
    private int weight;

    /**
     * @param name What the item is called
     * @param description What the item does
     * @param weight How much the item weighs
     */
    public Item(final String name, final String description, final int weight) {
	super(name, description);
	this.weight = weight;
    }

    /**
     * @return The weight of the item
     */
    public int getWeight(){
	return weight;
    }
}
