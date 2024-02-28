package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.itemContainers.PlayerInventory;
import se.liu.arvra591.objects.locations.Location;

import java.util.ArrayList;

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

    public void inspect(String name){
	currentLocation.inspect(name);
    }

    public void move(String name){
	Location location = currentLocation.getExit(name);
	if (location != null)
	    currentLocation = location;
    }

    public Location getCurrentLocation() {
	return currentLocation;
    }

    public static void main(String[] args) {
	Location l1 = new Location("Room 1", "Första rummet du vaknar i",
				   new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	CreatureStats c1 = new CreatureStats(10, 10, 10, 10, 10);
	Player p1 = new Player("Kalle", "Redigt kool", 10, 1,
			       c1, 10, 0, l1, 0);

	Location l2 = new Location("Room 2", "Andra rummet",
				   new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	l1.addExit(l2);

	p1.getCurrentLocation().printObject();
	p1.move("Room 2");
	p1.getCurrentLocation().printObject();
    }
}
