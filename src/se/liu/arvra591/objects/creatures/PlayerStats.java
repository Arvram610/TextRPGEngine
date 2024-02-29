package se.liu.arvra591.objects.creatures;

/**
 * A class representing the stats of the player extends creaturestats
 * PlayerStats has in addition to CreatureStats a carry weight and experience
 * Carryweight is the capacity for how many items the player can carry
 */
public class PlayerStats extends CreatureStats
{
    /**
     * capacity for how many items the player can carry
     */
    protected int carryWeight;
    /**
     * experience points the player has
     */
    protected int experience;

    public PlayerStats(int health, int level, int attack, int defense,
		       int carryWeight, int currentWeight, int experience, int energy) {
	super(health, level, attack, energy, defense);
	this.carryWeight = carryWeight;
	this.experience = 0;
    }

    @Override
    public void printStats(){
	System.out.println("Carry Weight: " + getCarryWeight());
	System.out.println("Experience: " + getExperience());
    }

    public int getCarryWeight() {
	return carryWeight;
    }

    public int getExperience() {
	return experience;
    }
}
