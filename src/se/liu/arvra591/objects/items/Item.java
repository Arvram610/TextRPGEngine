package se.liu.arvra591.objects.items;

import se.liu.arvra591.objects.AbstractObject;
/**
 * Superclass for items
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
