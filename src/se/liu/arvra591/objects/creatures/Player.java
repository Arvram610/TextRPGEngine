package se.liu.arvra591.objects.creatures;

import se.liu.arvra591.ListHelper;
import se.liu.arvra591.objects.containers.PlayerInventory;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.util.ArrayList;
import java.util.List;

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
    protected Location currentLocation;
    protected int currentEnergy;


    /**
     * @param name is the name of the player
     * @param description is the description of the player
     * @param currentHealth is the health of the player
     * @param stats is the stats of the player such as attack, defense, level, energy
     * @param currentLocation is the location of the player
     * @param experience is the experience points of the player, this determines the level of the player
     * @param inventory is the inventory of the player which contains items
     */
    public Player(final String name, final String description, int currentHealth, int currentEnergy,
		  PlayerStats stats, Location currentLocation,
		   PlayerInventory inventory) {
	super(name, description, currentHealth, stats, inventory);
	this.currentLocation = currentLocation;
	this.currentEnergy = currentEnergy;
    }

    /**
     * Prints the players stats and location
     */
    @Override public void printObject() {
	super.printObject();
	printStats();
	System.out.println("Current Location: " + getCurrentLocation().getName());
    }

    /**
     * Prints the players stats
     */
    public void printStats(){
	PlayerStats stats = getPlayerStats();
	stats.printStats();
    }

    public PlayerStats getPlayerStats() {
	return (PlayerStats) stats;
    }

    /**
     * @param name is the name of the item to inspect
     */
    public boolean inspect(String name){
	return currentLocation.inspect(name);
    }

    /**
     * @param name is the name of the exit to move to
     *
     * @return true if the player can move to the exit, false if the exit does not exist
     */
    public boolean move(String name){
	Location location = currentLocation.getExit(name);
	if (location != null) {
	    currentLocation = location;
	    return true;
	}
	return false;
    }

    /**
     * @param name is the name of the item to pick up
     *
     * @return true if the player can pick up the item, false if the item does not exist
     */
    public boolean pickUpItem(String name){
	Item item = currentLocation.removeItem(name);
	if (item != null) {
	    pickUpItem(item); //use super method or remove super method?
	    return true;
	}
	return false;
    }

    /**
     * Prints the players inventory
     */
    public void printInventory(){
	inventory.printContainer();
    }

    /**
     * @param name is the name of the item to dropq
     *
     * @return true if the player can drop the item, false if the item does not exist
     */
    public boolean dropItem(String name){
	Item item = inventory.removeObject(name);
	if (item != null) {
	    currentLocation.addItem(item);
	    return true;
	}
	return false;
    }

    /**
     * @param name is the name of the npc to talk to
     *
     * @return true if the player can talk to the npc, false if the npc does not exist
     */
    public boolean talkToNpc(String name){
	List<Npc> npcs = currentLocation.getNpcs();
	Npc npc = (Npc) ListHelper.findObjectInList(npcs, name);
	if (npc != null) {
	    npc.talk();
	    return true;
	}
	return false;
    }

    /**
     * @param energy is the energy to reduce from the player
     */
    public boolean reduceEnergy(int energy){
	stats = getPlayerStats();
	int lastEnergy = currentEnergy;
	currentEnergy -= energy;
	if (currentEnergy < 0) {
	    currentEnergy = lastEnergy;
	    return false;
	}
	return true;
    }

    /**
     * @param energy is the energy to add to the player
     */
    public void addEnergy(int energy){
	stats = getPlayerStats();
	currentEnergy += energy;
	if (currentEnergy > stats.getMaxEnergy()) {
	    currentEnergy = stats.getMaxEnergy();
	}
    }

    /**
     * @return Returns the current energy of the player
     */
    public int getCurrentEnergy(){
	return currentEnergy;
    }

    /**
     * @return Returns the current location of the player
     */
    public Location getCurrentLocation() {
	return currentLocation;
    }

    public static void main(String[] args) {
	Location l1 = new Location("Room 1", "Första rummet du vaknar i",
				   new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	PlayerStats c1 = new PlayerStats(10, 10, 10, 10, 10, 10, 10, 10, 110);
	Player p1 = new Player("Kalle", "Redigt kool", 10, 100,
			       c1, l1, null);

	Location l2 = new Location("Room 2", "Andra rummet",
				   new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
	l1.addExit(l2);

	p1.getCurrentLocation().printObject();
	p1.move("Room 2");
	p1.getCurrentLocation().printObject();
    }
}
