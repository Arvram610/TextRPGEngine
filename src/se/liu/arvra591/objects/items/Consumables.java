package se.liu.arvra591.objects.items;
/**
 * class for consumable items which inherit from the item class. Consumable items are items that when
 * used is consumed and removed from the inventory.
 */
public class Consumables extends Item
{
    public Consumables(final String name, final String description, final int weight) {
	super(name, description, weight);

    }

    private void use(){
	//uses and consumes the item
    }
}
