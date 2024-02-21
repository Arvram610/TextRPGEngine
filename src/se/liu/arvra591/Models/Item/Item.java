package se.liu.arvra591.Models.Item;

import se.liu.arvra591.Models.Object;

public abstract class Item extends Object
{
    private int weight;  // Limits how many items can be carried at a time

    protected Item(final String name, final String description, final int weight) {
	super(name, description);
	this.weight = weight;
    }
}
