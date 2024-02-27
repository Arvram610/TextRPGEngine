package se.liu.arvra591.objects.creatures;


import se.liu.arvra591.objects.Object;

public class CreatureStats implements Object
{
    private int maxHealth;
    private int attack;
    private int defense;
    private int maxEnergy;
    private int energyRegenRate;


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
