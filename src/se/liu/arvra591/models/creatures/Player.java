package se.liu.arvra591.models.creatures;

public class Player extends Creature
{

    private int carryWeight; //capacity for how many items the player can carry
    private int currentWeight; //current weight of items the player is carrying

    public Player(final String name, final String description, int health, CreatureStats stats, int carryWeight, int currentWeight) {
	super(name, description, health, stats);
	this.carryWeight = carryWeight;
	this.currentWeight = currentWeight;
    }

    public int getCarryWeight() {
	return carryWeight;
    }

    public int getCurrentWeight() {
	return currentWeight;
    }
}
