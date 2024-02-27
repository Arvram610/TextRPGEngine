package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.AbstractObject;

public class Creature extends AbstractObject
{
    protected int health;

    protected int level;

    protected CreatureStats stats;

    public Creature(String name, String description, int health, int level, CreatureStats stats){
	super(name, description);
	this.health = health;
        this.stats = stats;
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

    public CreatureStats getStats() {
        return stats;
    }
}
