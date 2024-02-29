package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.locations.Location;

/**
 * A class representing a player
 * A player has a location where pointing to where the player currently is
 * A player has an inventory which uses the {@link Inventory} class and has a carry weight for max weight it can carry
 * A player has a current weight for the current weight of items it is carrying
 * A player has experience to level up
 * Player extends {@link Creature}
 */
public class Player extends Creature
{

    /**
     * Capacity for how many items the player can carry.
     */
    protected int carryWeight;

    /**
     * Current weight of items the player is carrying.
     */
    protected int currentWeight;
    protected int experience;
    protected Location currentLocation;

    public Player(final String name, final String description, int health,
		  CreatureStats stats, int carryWeight, int currentWeight, Location currentLocation,
		  int experience) {
	super(name, description, health, stats);
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

    public static void main(String[] args) {
	CreatureStats stats = new CreatureStats(10, 10, 10, 10, 10);
	Location location = new Location("Test", "Test", null, null, null);
	Player player = new Player("Test", "Test", 10, stats, 10, 10, location, 0);
	player.printObject();

    }
}
