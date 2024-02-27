package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.locations.Location;

public class Player extends Creature
{

    protected int carryWeight; //capacity for how many items the player can carry
    protected int currentWeight; //current weight of items the player is carrying
    protected int experience;
    protected Location currentLocation;

    public Player(final String name, final String description, int health, int level,
		  CreatureStats stats, int carryWeight, int currentWeight, Location currentLocation) {
	super(name, description, health, level,  stats);
	this.carryWeight = carryWeight;
	this.currentWeight = currentWeight;
	this.currentLocation = currentLocation;
    }

    @Override public void printObject() {
	super.printObject();
	System.out.println("Carry Weight: " + getCarryWeight());
	System.out.println("Current Weight: " + getCurrentWeight());
	System.out.println("Current Location: " + getCurrentLocation().getName());
    }

    private void interact(String name){
	currentLocation.interact(name);
    }

    public int getCarryWeight() {
	return carryWeight;
    }

    public int getCurrentWeight() {
	return currentWeight;
    }

    public Location getCurrentLocation() {
	return currentLocation;
    }
}
