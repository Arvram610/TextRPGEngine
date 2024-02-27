package se.liu.arvra591.objects.creatures;

public class Player extends Creature
{

    protected int carryWeight; //capacity for how many items the player can carry
    protected int currentWeight; //current weight of items the player is carrying

    public Player(final String name, final String description, int health, CreatureStats stats, int carryWeight, int currentWeight) {
	super(name, description, health, stats);
	this.carryWeight = carryWeight;
	this.currentWeight = currentWeight;
    }

    @Override public void printObject() {
	super.printObject();
	System.out.println("Carry Weight: " + getCarryWeight());
	System.out.println("Current Weight: " + getCurrentWeight());
    }

    public int getCarryWeight() {
	return carryWeight;
    }

    public int getCurrentWeight() {
	return currentWeight;
    }
}
