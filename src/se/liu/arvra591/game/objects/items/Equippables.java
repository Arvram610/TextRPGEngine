package se.liu.arvra591.game.objects.items;
/**
 * Equipable items which inherit from the item class. Equipable items are items that can be equipped
 * This can be ex armor, weapons, etc
 * you equip for examble a sword and it will be removed from the inventory and added to the players equipment and then you can use it
 * to attack, or equip armor which gives you more defense
 */
public class Equippables extends Item //Kodgranskning does not reqognize the word Equippables, I do not agree
{
    private int stats; //might be changed later, ex armor, damage, etc

    /**
     * @param name What the item is called
     * @param description What the item does
     * @param weight How much the item weighs
     */
    public Equippables(final String name, final String description, final int weight) {
	super(name, description, weight);
    }
}
