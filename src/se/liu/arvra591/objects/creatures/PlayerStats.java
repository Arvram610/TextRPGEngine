package se.liu.arvra591.objects.creatures;

public class PlayerStats extends CreatureStats
{
    protected int carryWeight; //capacity for how many items the player can carry
    protected int currentWeight; //current weight of items the player is carrying
    protected int experience; //experience points the player has

    public PlayerStats(int health, int level, int attack, int defense,
		       int carryWeight, int currentWeight, int experience, int energy) {
	super(health, level, attack, energy, defense);
	this.carryWeight = carryWeight;
	this.currentWeight = currentWeight;
	this.experience = 0;
    }

    public int getCarryWeight() {
	return carryWeight;
    }

    public int getExperience() {
	return experience;
    }

    public int getCurrentWeight() {
	return currentWeight;
    }
}
