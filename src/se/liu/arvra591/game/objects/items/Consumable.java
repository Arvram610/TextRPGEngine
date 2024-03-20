package se.liu.arvra591.game.objects.items;

import java.util.List;

/**
 * class for consumable items which inherit from the item class. Consumable items are items that when used is consumed and removed from the
 * inventory.
 */
public class Consumable extends Item //Kodgranskning complains that the word Consumables should be split, I do not agree
{
    private final List<String> useCommands;

    /**
     * @param name        of the item
     * @param description Describes the item
     * @param weight      what the item weighs which is used to determine how much the player can carry
     */
    public Consumable(final String name, final String description, final int weight, List<String> useCommands) {
	super(name, description, weight);
	this.useCommands = useCommands;
    }

    public void use() {
	sendCommands(useCommands);
    }
}
