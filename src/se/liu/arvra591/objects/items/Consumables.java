package se.liu.arvra591.objects.items;
/**
 * class for consumable items
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
