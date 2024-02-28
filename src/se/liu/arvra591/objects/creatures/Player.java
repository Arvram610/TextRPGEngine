package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.itemContainers.PlayerInventory;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.util.ArrayList;

public class Player extends Creature
{
    protected Location currentLocation;


    public Player(final String name, final String description, int health,
		  PlayerStats stats, Location currentLocation,
		  int experience, PlayerInventory inventory) {
	super(name, description, health, stats, inventory);
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

    public boolean move(String name){
	Location location = currentLocation.getExit(name);
	if (location != null) {
	    currentLocation = location;
	    return true;
	}
	return false;
    }

    public boolean pickUpItem(String name){
	Item item = currentLocation.removeItem(name);
	if (item != null) {
	    inventory.addObject(item);
	    return true;
	}
	return false;
    }

    public Location getCurrentLocation() {
	return currentLocation;
    }

    public static void main(String[] args) {
	Location l1 = new Location("Room 1", "Första rummet du vaknar i",
				   new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	PlayerStats c1 = new PlayerStats(10, 10, 10, 10, 10, 10, 10, 10);
	Player p1 = new Player("Kalle", "Redigt kool", 10,
			       c1, l1, 0, null);

	Location l2 = new Location("Room 2", "Andra rummet",
				   new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	l1.addExit(l2);

	p1.getCurrentLocation().printObject();
	p1.move("Room 2");
	p1.getCurrentLocation().printObject();
    }
}
