package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.objects.locations.Location;

import java.util.ArrayList;

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
	Location location = currentLocation.getExit(name);
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
