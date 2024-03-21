package se.liu.arvra591.game.objects.locations;

import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.game.objects.ListHelper;
import se.liu.arvra591.game.objects.containers.ObjectContainer;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class for locations in the game. Locations are places where the player can be and can contain npcs, items and exits Location can print
 * information about itself and inspect items and npcs in the location
 */
public class Location extends AbstractObject
{
    private final List<String> firstEnteredCommands;
    private final List<String> normalEnterCommands;
    private ObjectContainer<Npc> npcContainer;
    private ObjectContainer<Item> itemContainer;
    private ObjectContainer<Location> exitContainer;
    private List<String> exitStrings;
    private boolean firstEnter = true;

    /**
     * @param name                Name of the location
     * @param description         Description of the location
     * @param npcs                List of npcs in the location
     * @param items               List of items in the location
     * @param exits               List of exits in the location
     * @param firstEnterCommands  Commands to run when the location is first entered
     * @param normalEnterCommands Commands to run when the location is entered
     */

    public Location(String name, String description, List<Npc> npcs, List<Item> items, List<String> exitStrings,
		    List<String> firstEnterCommands, List<String> normalEnterCommands)
    {

	super(name, description);
	this.npcContainer = new ObjectContainer<>(npcs);
	this.itemContainer = new ObjectContainer<>(items);
	this.exitContainer = new ObjectContainer<>(new ArrayList<>());
	this.exitStrings = exitStrings;
	this.firstEnteredCommands = firstEnterCommands;
	this.normalEnterCommands = normalEnterCommands;
    }


    /**
     * This method should be called to tell the location that a player has entered it. If it is the first time the room is entered it will
     * run through the {@link firstEnteredCommands} commands. If it is not the first time entering the room it will run the
     * {@link normalEnterCommands} commands.
     */
    public void roomEntered() {
	if (firstEnter) {
	    sendCommands(firstEnteredCommands);
	    firstEnter = false;
	    return;
	}
	sendCommands(normalEnterCommands);
    }

    /**
     * @param location Location to add to the exit list
     */
    public void addExit(Location location) {
	exitContainer.forceAddObject(location);
    }

    /**
     * @param location Location to remove from the exit list
     */
    public void removeExit(String name) {
	exitContainer.removeObject(name);
    }

    /**
     * @param name Name of the item to add
     *
     * @return The item that was added
     */
    public Item removeItem(String name) {
	return itemContainer.removeObject(name);
    }

    /**
     * @param item Item to add to the location
     *
     * @return True if the item was added, false if not
     */
    public boolean addItem(Item item) {
	return itemContainer.addObject(item);
    }

    /**
     * @param npc Npc to add to the location
     */
    public void addNpc(Npc npc) {
	npcContainer.forceAddObject(npc);
    }

    /**
     * @param npc Npc to remove from the location
     */
    public void removeNpc(String name) {
	npcContainer.removeObject(name);
    }

    /**
     * @param name Name of the item to inspect Prints information about the item
     */
    public boolean inspect(String name) {
	List<List<? extends AbstractObject>> lists =
		Arrays.asList(npcContainer.getObjects(), itemContainer.getObjects(), exitContainer.getObjects());
	AbstractObject object = ListHelper.findObjectInLists(lists, name);

	if (object != null) {
	    object.printObject();
	    return true;
	}
	return false;
    }

    /**
     * @param name Name of the exit to get
     *
     * @return The name of the exit
     */
    public Location getExit(final String name) {
	return ListHelper.findObjectInList(exitContainer.getObjects(), name);
    }


    /**
     * @return returns the npcs
     */
    public List<Npc> getNpcs() {
	return npcContainer.getObjects();
    }

    /**
     * @return reurns the exits
     */
    public List<String> getExitStrings() {
	return exitStrings;
    }

    /**
     * Prints the the location and its contents such as npcs, items and exits
     */
    @Override public void printObject() {

	System.out.print("Location: ");
	printName();
	System.out.print("Description: ");
	printDescription();

	System.out.println("Items in location: ");
	ListHelper.printList(itemContainer.getObjects(), true);
	System.out.println();

	System.out.println("Creatures in location: ");
	ListHelper.printList(npcContainer.getObjects(), true);
	System.out.println();

	System.out.println("Exits in location: ");
	ListHelper.printList(exitContainer.getObjects(), true);
	System.out.println();
    }
}
