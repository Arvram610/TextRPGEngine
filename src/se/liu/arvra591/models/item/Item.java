package se.liu.arvra591.models.item;

import se.liu.arvra591.models.Object;

public class Item extends Object
{
    private int weight;  // Limits how many items can be carried at a time

    Item(final String name, final String description, final int weight) {
	super(name, description);
	this.weight = weight;
    }

    public int getWeight(){
	return weight;
    }
}
