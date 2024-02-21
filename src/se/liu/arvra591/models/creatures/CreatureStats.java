package se.liu.arvra591.models.creatures;


import se.liu.arvra591.models.AbstractObject;

public class CreatureStats extends AbstractObject
{
    private int maxHealth;
    private int attack;
    private int defense;
    private int carryWeight;
    private int maxEnergy;
    private int energyRegenRate;


    public CreatureStats(final int maxHealth, final int attack, final int defense, final int carryWeight, final int maxEnergy,
                         final int energyRegenRate)
    {
        super("CreatureStats", "CreatureStats");
        this.maxHealth = maxHealth;
        this.attack = attack;
        this.defense = defense;
        this.carryWeight = carryWeight;
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

    public int getCarryWeight() {
        return carryWeight;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public int getEnergyRegenRate() {
        return energyRegenRate;
    }
}
