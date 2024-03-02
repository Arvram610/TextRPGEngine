package se.liu.arvra591.game.objects.creatures;

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

    /**
     * @param maxHealth how much health the player currently has
     * @param level the level of the player
     * @param attack how much damage the player does
     * @param defense how much damage the player can mitigate
     * @param carryWeight the capacity for how many items the player can carry
     * @param currentWeight the current weight of the items the player is carrying
     * @param experience the experience points the player has, this determines level
     * @param maxEnergy the energy of the player
     */
    public PlayerStats(int maxHealth, int level, int attack, int defense,
		       int carryWeight, int currentWeight, int experience, int maxEnergy, int energyRegen) {
	super(maxHealth, attack, defense, maxEnergy, energyRegen);
	this.carryWeight = carryWeight;
	this.experience = 0;
    }

    /**
     * Prints the players stats
     */
    @Override
    public void printStats(){
	super.printStats();
	System.out.println("Carry Weight: " + getCarryWeight());
	System.out.println("Experience: " + getExperience());
    }

    /**
     * @return Returns the maximum carry weight of the player
     */
    public int getCarryWeight() {
	return carryWeight;
    }

    /**
     * @return Returns the experience points of the player
     */
    public int getExperience() {
	return experience;
    }
}
