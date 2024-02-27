package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.itemContainers.PlayerInventory;
import se.liu.arvra591.objects.locations.Location;

public class Player extends Creature
{
    protected Location currentLocation;


    public Player(final String name, final String description, int health, int level,
		  PlayerStats stats, int carryWeight, int currentWeight, Location currentLocation,
		  int experience, PlayerInventory inventory) {
	super(name, description, health, level, stats, inventory);
	this.currentLocation = currentLocation;
    }

    @Override public void printObject() {
	super.printObject();
	PlayerStats stats = (PlayerStats) getStats();
	stats.printStats();
	System.out.println("Current Location: " + getCurrentLocation().getName());
    }

    public void interact(String name){
	currentLocation.interact(name);
    }

    public Location getCurrentLocation() {
	return currentLocation;
    }
}
