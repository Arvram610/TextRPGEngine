package se.liu.arvra591.game.objects.creatures;

/**
 * class for creature stats such as health, attack, defense, energy, and energy regen Used by {@link Npc} and {@link Player} to store stats
 * This class is used to make it easier to create players and npcs with different stats
 */

public class CreatureStats
{
    private int maxHealth;
    private int attack;
    private int defense;
    private int maxEnergy;
    private int energyRegenerationRate;

    /**
     * Temporary field for testing purposes
     */

    /**
     * @param maxHealth              is the maximum health of the creature
     * @param attack                 is the amount of damage the creature does
     * @param defense                is the amount of damage the creature can mitigate
     * @param maxEnergy              is the maximum energy of the creature
     * @param energyRegenerationRate is the rate at which the creature regenerates energy
     */
    public CreatureStats(final int maxHealth, final int attack, final int defense, final int maxEnergy, final int energyRegenerationRate)
    {
	this.maxHealth = maxHealth;
	this.attack = attack;
	this.defense = defense;
	this.maxEnergy = maxEnergy;
	this.energyRegenerationRate = energyRegenerationRate;
    }

    /**
     * @param stats is the stats that will be added to the creature
     *
     * @return Returns the new stats of the creature
     */
    public CreatureStats calculateStats(CreatureStats stats) {
	int maxHealth = this.maxHealth + stats.maxHealth;
	int attack = this.attack + stats.attack;
	int defense = this.defense + stats.defense;
	int maxEnergy = this.maxEnergy + stats.maxEnergy;
	int energyRegenerationRate = this.energyRegenerationRate + stats.energyRegenerationRate;
	return new CreatureStats(maxHealth, attack, defense, maxEnergy, energyRegenerationRate);
    }

    /**
     * @return Returns the max health of the creature
     */
    public int getMaxHealth() {
	return maxHealth;
    }

    /**
     * @return Returns the attack of the creature
     */
    public int getAttack() {
	return attack;
    }

    /**
     * @param amount is the amount of attack the creature will gain
     */
    public void increaseAttack(int amount) {
	attack += amount;
    }

    /**
     * @param amount is the amount of defense the creature will gain
     */
    public void increaseDefense(int amount) {
	defense += amount;
    }

    /**
     * @return Returns the defense of the creature
     */
    public int getDefense() {
	return defense;
    }

    /**
     * @return Returns the max energy of the creature
     */
    public int getMaxEnergy() {
	return maxEnergy;
    }

    /**
     * @return Returns the energy regen rate of the creature
     */
    public int getEnergyRegenerationRate() {
	return energyRegenerationRate;
    }

    /**
     * Prints the stats of the creature
     */
    public void printStats() {
	System.out.println("Attack: " + attack);
	System.out.println("Defense: " + defense);
	System.out.println("Max Health: " + maxHealth);
	System.out.println("Max Energy: " + maxEnergy);
	System.out.println("Energy Regen: " + energyRegenerationRate);
    }
}
