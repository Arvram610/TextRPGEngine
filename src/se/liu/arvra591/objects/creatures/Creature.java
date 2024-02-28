package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.AbstractObject;

/**
 * A class representing all creatures, all creatures have some basic stats as well as health and level
 * All creatures such as {@link Player} and {@link Npc} inherit from this class
 */
public abstract class Creature extends AbstractObject
{
    protected int health;

    protected int level;

    protected CreatureStats stats;

    public Creature(String name, String description, int health, int level, CreatureStats stats){
	super(name, description);
	this.health = health;
        this.stats = stats;
        this.level = level;
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


    /**
     * @return Returns the current level of the creature
     */
    public int getLevel(){
        return level;
    }

    /**
     * @return Returns the stats of the creature
     */
    public CreatureStats getStats() {
        return stats;
    }
}
