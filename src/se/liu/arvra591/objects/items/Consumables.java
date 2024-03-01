package se.liu.arvra591.objects.items;
/**
 * class for consumable items which inherit from the item class. Consumable items are items that when
 * used is consumed and removed from the inventory.
 */
public class Consumables extends Item //Kodgranskning complains that the word Consumables should be split, I do not agree
{

    /**
     * @param name of the item
     * @param description Describes the item
     * @param weight what the item weighs which is used to determine how much the player can carry
     */
    public Consumables(final String name, final String description, final int weight) {
	super(name, description, weight);
    }

    private void use(){
	//uses and consumes the item
    }
}
