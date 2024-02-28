package se.liu.arvra591.objects.creatures;

public class PlayerStats extends CreatureStats
{
    protected int carryWeight; //capacity for how many items the player can carry
    protected int experience; //experience points the player has

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
