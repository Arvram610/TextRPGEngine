package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.itemContainers.AbstractInventory;
import se.liu.arvra591.objects.items.Item;

public class Creature extends AbstractObject
{
    protected int health;

    protected int level;

    protected CreatureStats stats;
    protected AbstractInventory inventory;

    public Creature(String name, String description, int health, int level, CreatureStats stats, AbstractInventory inventory){
	super(name, description);
	this.health = health;
        this.stats = stats;
        this.inventory = inventory;
    }

    @Override
    public void printObject(){
        super.printObject();
        System.out.println("Health: " + getHealth());
        stats.printStats();
    }

    public int getHealth(){
        return health;
    }

    public int getLevel(){
        return level;
    }

    public void pickUpItem(Item item){
        inventory.addItem(item);
    }

    public CreatureStats getStats() {
        return stats;
    }
}
