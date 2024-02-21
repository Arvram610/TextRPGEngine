package se.liu.arvra591.models.creatures;

import se.liu.arvra591.models.AbstractObject;

public class Creature extends AbstractObject
{
    protected int health;
    protected CreatureStats stats;

    public Creature(String name, String description, int health, CreatureStats stats){
	super(name, description);
	this.health = health;
        this.stats = stats;
    }

    public int getHealth(){
        return health;
    }

    public CreatureStats getStats() {
        return stats;
    }
}
