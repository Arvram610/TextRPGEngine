package se.liu.arvra591.game.objects.creatures;

/**
 * A class representing the stats of the player extends creaturestats PlayerStats has in addition to CreatureStats a carry weight and
 * experience Carryweight is the capacity for how many items the player can carry
 */
public class PlayerStats extends CreatureStats
{
    /**
     * capacity for how many items the player can carry
     */
    protected int carryWeight;

    /**
     * @param cStats      The creaturestats the playerstats are based on
     * @param carryWeight The amount of weight tha player can carry
     */
    public PlayerStats(CreatureStats cStats, int carryWeight) {
	super(cStats.getMaxHealth(), cStats.getAttack(), cStats.getDefense(), cStats.getMaxEnergy(), cStats.getEnergyRegenerationRate());
	this.carryWeight = carryWeight;
    }

    /**
     * Prints the players stats
     */
    @Override public void printStats() {
	super.printStats();
	System.out.println("Carry Weight: " + carryWeight);
    }

    /**
     * @return Returns the maximum carry weight of the player
     */
    public int getCarryWeight() {
	return carryWeight;
    }
}
