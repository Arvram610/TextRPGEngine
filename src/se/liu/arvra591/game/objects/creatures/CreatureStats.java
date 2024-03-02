package se.liu.arvra591.game.objects.creatures;
/**
 * class for creature stats such as health, attack, defense, energy, and energy regen
 * Used by {@link Npc} and {@link Player} to store stats
 * This class is used to make it easier to create players and npcs with different stats
 */

public class CreatureStats
{
    private int maxHealth;
    private int attack;
    private int defense;
    private int maxEnergy;
    private int energyRegenRate; //kodgranskning does not recoqnise "Regen" as a word, I do not agree

    /**
     * Temporary field for testing purposes
     */
    public static CreatureStats basic = new CreatureStats(100,10,10,100,10);


    /**
     * @param maxHealth is the maximum health of the creature
     * @param attack is the amount of damage the creature does
     * @param defense is the amount of damage the creature can mitigate
     * @param maxEnergy is the maximum energy of the creature
     * @param energyRegenRate is the rate at which the creature regenerates energy
     */
    public CreatureStats(final int maxHealth, final int attack, final int defense, final int maxEnergy,
                         final int energyRegenRate)
    {
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.maxEnergy = maxEnergy;
        this.energyRegenRate = energyRegenRate;
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
    public void increaseAttack(int amount){
        attack += amount;
    }

    /**
     * @param amount is the amount of defense the creature will gain
     */
    public void increaseDefense(int amount){
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
    public int getEnergyRegenRate() {
        return energyRegenRate;
    }

    /**
     * Prints the stats of the creature
     */
    public void printStats(){
        System.out.println("Attack: " + getAttack());
        System.out.println("Defense: " + getDefense());
        System.out.println("Max Health: " + getMaxHealth());
        System.out.println("Max Energy: " + getMaxEnergy());
        System.out.println("Energy Regen: " + getEnergyRegenRate());
    }
}
