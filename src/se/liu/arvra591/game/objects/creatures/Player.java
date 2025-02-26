package se.liu.arvra591.game.objects.creatures;

import se.liu.arvra591.game.objects.ListHelper;
import se.liu.arvra591.game.objects.containers.PlayerInventory;
import se.liu.arvra591.game.objects.items.Consumable;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;

import java.util.List;
import java.util.Objects;

/**
 * A class representing a player A player has a location where pointing to where the player currently is A player has an inventory which
 * uses the {@link Inventory} class and has a carry weight for max weight it can carry A player has a current weight for the current weight
 * of items it is carrying A player has experience to level up Player extends {@link Creature}
 */
public class Player extends Creature
{
    protected Location currentLocation;


    /**
     * @param name            is the name of the player
     * @param description     is the description of the player
     * @param currentHealth   is the health of the player
     * @param stats           is the stats of the player such as attack, defense, level, energy
     * @param currentLocation is the location of the player
     * @param experience      is the experience points of the player, this determines the level of the player
     * @param inventory       is the inventory of the player which contains items
     */

    public Player(final String name, final String description, int currentHealth, int currentEnergy, PlayerStats stats,
		  Location currentLocation, PlayerInventory inventory)
    {
	super(name, description, currentHealth, currentEnergy, stats, inventory);
	this.currentLocation = currentLocation;
    }

    /**
     * Prints the players stats and location
     */
    @Override public void printObject() {
	super.printObject();
	printStats();
	System.out.println("Current Location: " + currentLocation.getName());
    }

    /**
     * Prints the players stats
     */
    public void printStats() {
	PlayerStats stats = getPlayerStats();
	PlayerInventory inventory = (PlayerInventory) this.inventory;
	stats.printStats();
	System.out.println("Energy: " + currentEnergy);
	System.out.println(("Health: " + currentHealth));
	System.out.println("Current weight of items: " + inventory.getCurrentWeight() + "/" +
			   stats.getCarryWeight()); // slash is used as a styring not file separator
    }

    public PlayerStats getPlayerStats() {
	return new PlayerStats(getStats(), ((PlayerStats) stats).getCarryWeight());
    }

    /**
     * @param name is the name of the item to inspect
     */
    public boolean inspect(String name) {
	Item item = inventory.getObject(name);
	if (item != null) {
	    item.printObject();
	    return true;
	}
	return currentLocation.inspect(name);
    }

    /**
     * @param name is the name of the exit to move to
     *
     * @return true if the player can move to the exit, false if the exit does not exist
     */
    public boolean move(String name) {
	Location location = currentLocation.getExit(name);
	if (location != null) {
	    currentLocation = location;
	    return true;
	}
	return false;
    }

    /**
     * @param location is the location to move to
     */
    public void forceMove(Location location) {
	currentLocation = location;
    }

    /**
     * @param name is the name of the item to pick up
     *
     * @return true if the player can pick up the item, false if the item does not exist
     */
    public boolean pickUpItem(String name) {
	Item item = currentLocation.removeItem(name);
	if (item != null) {
	    return pickUpItem(item);
	}
	return false;
    }

    public void useItem(String name) {
	Item item = inventory.getObject(name);
	if (item instanceof Consumable consumable) { //polymorfism är inte bästa lösningen i detta fall
	    consumable.use();
	    inventory.removeObject(item);
	    return;
	}
	System.out.println("You can't use that item");
    }

    /**
     * Prints the players inventory
     */
    public void printInventory() {
	inventory.printContainer();
    }

    /**
     * @param name is the name of the item to dropq
     *
     * @return true if the player can drop the item, false if the item does not exist
     */
    public boolean dropItem(String name) {
	Item item = inventory.removeObject(name);
	if (item != null) {
	    currentLocation.addItem(item);
	    if (Objects.equals(equippedItem, item)) {
		equippedItem = null;
	    }
	    return true;
	}
	return false;
    }

    /**
     * if the players HP is 0 or less this method is called and the game is over
     */
    public void onDeath() {
	sendCommand("say Sadly you died and the game is over");
	sendCommand("lose");
    }

    /**
     * @param name is the name of the npc to talk to
     *
     * @return true if the player can talk to the npc, false if the npc does not exist
     */
    public boolean talkToNpc(String name) {
	List<Npc> npcs = currentLocation.getNpcs();
	Npc npc = ListHelper.findObjectInList(npcs, name);
	if (npc != null) {
	    npc.talk();
	    return true;
	}
	return false;
    }

    /**
     * @param energy is the energy to reduce from the player
     */
    public boolean reduceEnergy(int energy) {
	int lastEnergy = currentEnergy;
	currentEnergy -= energy;
	if (currentEnergy < 0) {
	    currentEnergy = lastEnergy;
	    return false;
	}
	return true;
    }

    /**
     * @param item is the item to add to the player
     */
    public void forceAddItem(Item item) {
	inventory.forceAddObject(item);
    }

    /**
     * @param energy is the energy to add to the player
     */
    public void addEnergy(int energy) {
	stats = getPlayerStats();
	currentEnergy += energy;
	if (currentEnergy > stats.getMaxEnergy()) {
	    currentEnergy = stats.getMaxEnergy();
	}
    }

    /**
     * @return Returns the current location of the player
     */
    public Location getCurrentLocation() {
	return currentLocation;
    }
}
