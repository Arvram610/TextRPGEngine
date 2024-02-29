package se.liu.arvra591.objects.creatures;
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
    private int energyRegenRate;

    public static CreatureStats basic = new CreatureStats(10,10,10,10,10);


    public CreatureStats(final int maxHealth, final int attack, final int defense, final int maxEnergy,
                         final int energyRegenRate)
    {
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.maxEnergy = maxEnergy;
        this.energyRegenRate = energyRegenRate;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getEnergyRegenRate() {
        return energyRegenRate;
    }

    public void printStats(){
        System.out.println("Attack: " + getAttack());
        System.out.println("Defense: " + getDefense());
        System.out.println("Max Health: " + getMaxHealth());
        System.out.println("Max Energy: " + getMaxEnergy());
        System.out.println("Energy Regen: " + getEnergyRegenRate());
    }
}
