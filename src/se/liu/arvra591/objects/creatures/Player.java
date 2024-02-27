package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.locations.Location;

public class Player extends Creature
{

    protected int carryWeight; //capacity for how many items the player can carry
    protected int currentWeight; //current weight of items the player is carrying
    protected int experience;
    protected Location currentLocation;

    public Player(final String name, final String description, int health, int level,
		  CreatureStats stats, int carryWeight, int currentWeight, Location currentLocation,
		  int experience) {
	super(name, description, health, level,  stats);
	this.carryWeight = carryWeight;
	this.currentWeight = currentWeight;
	this.currentLocation = currentLocation;
	this.experience = 0;
    }

    @Override public void printObject() {
	super.printObject();
	System.out.println("Carry Weight: " + getCarryWeight());
	System.out.println("Current Weight: " + getCurrentWeight());
	System.out.println("Current Location: " + getCurrentLocation().getName());
    }

    public void inspect(String name){
	currentLocation.inspect(name);
    }

    public void move(String name){
	Location location = currentLocation.getLocation(name);
	if (location != null)
	    currentLocation = location;
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

    public Location getCurrentLocation() {
	return currentLocation;
    }
}
