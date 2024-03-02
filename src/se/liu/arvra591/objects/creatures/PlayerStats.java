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
     * @param maxHealth how much maxHealth the player currently has
     * @param attack how much damage the player does
     * @param defense how much damage the player can mitigate
     * @param carryWeight the capacity for how many items the player can carry
     * @param currentWeight the current weight of the items the player is carrying
     * @param experience the experience points the player has, this determines level
     * @param energy the energy of the player
     */
    public PlayerStats(int maxHealth, int attack, int defense, int maxEnergy,
		       int energyRegenRate, int carryWeight) {
	super(maxHealth, attack, defense, maxEnergy, energyRegenRate);
	this.carryWeight = carryWeight;
    }

    public PlayerStats(CreatureStats cStats, int carryWeight){
	super(cStats.getMaxHealth(), cStats.getAttack(), cStats.getDefense(),
	      cStats.getMaxEnergy(), cStats.getEnergyRegenRate());
	this.carryWeight = carryWeight;
    }

    /**
     * Prints the players stats
     */
    @Override
    public void printStats(){
	super.printStats();
	System.out.println("Carry Weight: " + getCarryWeight());
    }

    /**
     * @return Returns the maximum carry weight of the player
     */
    public int getCarryWeight() {
	return carryWeight;
    }
}
