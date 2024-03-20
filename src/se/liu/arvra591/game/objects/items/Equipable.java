package se.liu.arvra591.game.objects.items;

import se.liu.arvra591.game.objects.creatures.CreatureStats;

/**
 * Equipable items which inherit from the item class. Equipable items are items that can be equipped This can be ex armor, weapons, etc you
 * equip for examble a sword and it will be removed from the inventory and added to the players equipment and then you can use it to attack,
 * or equip armor which gives you more defense
 */
public class Equipable extends Item //Kodgranskning does not reqognize the word Equippables, I do not agree
{
    private CreatureStats stats;

    /**
     * @param name        What the item is called
     * @param description What the item does
     * @param weight      How much the item weighs
     */
    public Equipable(final String name, final String description, final int weight, CreatureStats stats) {

	super(name, description, weight);
	this.stats = stats;
    }

    public CreatureStats getStats() {
	return stats;
    }

    @Override public void printObject() {
	super.printObject();
	stats.printStats();
    }
}
