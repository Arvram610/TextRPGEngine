package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.itemContainers.CreatureInventory;
import se.liu.arvra591.objects.items.Item;

/**
 * A class representing all creatures, all creatures have some basic stats as well as health and level
 * All creatures such as {@link Player} and {@link Npc} inherit from this class
 */
public abstract class Creature extends AbstractObject
{
    protected int health;

    protected CreatureStats stats;
    protected CreatureInventory inventory;

    protected Creature(String name, String description, int health, CreatureStats stats, CreatureInventory inventory){

	super(name, description);
	this.health = health;
        this.stats = stats;
        this.inventory = inventory;
    }

    /**
     * Prints the creature
     */
    @Override
    public void printObject(){
        super.printObject();
        System.out.println("Health: " + getHealth());
        stats.printStats();
    }

    /**
     * @return Returns the current health of the creature
     */
    public int getHealth(){
        return health;
    }

    public void pickUpItem(Item item){
        inventory.addItem(item);
    }


    /**
     * @return Returns the stats of the creature
     */
    public CreatureStats getStats() {
        return stats;
    }
}
