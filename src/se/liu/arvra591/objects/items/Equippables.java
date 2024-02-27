package se.liu.arvra591.objects.items;
/**
 * class for equippable items
 */
public class Equippables extends Item
{
    private int stats; //might be changed later, ex armor, damage, etc
    public Equippables(final String name, final String description, final int weight) {
	super(name, description, weight);
    }
}
