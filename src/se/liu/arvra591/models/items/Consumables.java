package se.liu.arvra591.models.items;
/**
 * class for consumable items
 */
public class Consumables extends Item
{
    private int stacksize; //how many of the item can be stacked in one slot
    private int amount; //how many of the item is in the slot ex 5 healthpotions

    Consumables(final String name, final String description, final int weight, final int stacksize, final int amount) {
	super(name, description, weight);
	this.stacksize = stacksize;
	this.amount = amount;
    }

    private void use(){
	//uses and consumes the item
    }
}
