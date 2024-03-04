package se.liu.arvra591.game.objects.locations;

import se.liu.arvra591.game.ListHelper;
import se.liu.arvra591.game.objects.AbstractObject;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.containers.ObjectContainer;
import se.liu.arvra591.game.objects.items.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Class for locations in the game. Locations are places where the player can be and can contain npcs, items and exits
 * Location can print information about itself and inspect items and npcs in the location
 */
public class Location extends AbstractObject
{
    private ObjectContainer<Npc> npcContainer; //might be divided into enemies and players
    private ObjectContainer<Item> itemContainer;
    private ObjectContainer<Location> exitContainer;
    private List<String> exitStrings;

    //private List<interactables> interactablesList; //might be added later

    /**
     * @param name Name of the location
     * @param description Description of the location
     * @param npcs List of npcs in the location
     * @param items List of items in the location
     * @param exits List of exits in the location
     */
    public Location(String name, String description, List<Npc> npcs, List<Item> items, List<String> exitStrings) {
	super(name, description);
	this.npcContainer = new ObjectContainer<>(npcs);
	this.itemContainer = new ObjectContainer<>(items);
	this.exitContainer = new ObjectContainer<>(new ArrayList<>());
	this.exitStrings = exitStrings;
    }

    /**
     * @param location Location to add to the exit list
     */
    public void addExit(Location location){
	exitContainer.forceAddObject(location);
    }

    /**
     * @param location Location to remove from the exit list
     */
    public void removeExit(String name){
	exitContainer.removeObject(name);
    }

    /**
     * @param name Name of the item to add
     *
     * @return The item that was added
     */
    public Item removeItem(String name){
	return itemContainer.removeObject(name);
    }

    /**
     * @param item Item to add to the location
     *
     * @return True if the item was added, false if not
     */
    public boolean addItem(Item item){
	return itemContainer.addObject(item);
    }

    /**
     * @param npc Npc to add to the location
     */
    public void addNpc(Npc npc){
	npcContainer.forceAddObject(npc);
    }

    /**
     * @param npc Npc to remove from the location
     */
    public void removeNpc(String name){
	npcContainer.removeObject(name);
    }

    /**
     * @param name Name of the item to inspect
     * Prints information about the item
     */
    public boolean inspect(String name){
	List<List<? extends AbstractObject>> lists = Arrays.asList(npcContainer.getObjects(),
								   itemContainer.getObjects(),
								   exitContainer.getObjects());
	AbstractObject object = ListHelper.findObjectInLists(lists, name);

	if (object != null){
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
     * @return List of npcs in the location
     */
    public Npc getNpc(final String name) {
	return ListHelper.findObjectInList(npcContainer.getObjects(), name);
    }

    public List<Npc> getNpcs() {
	return npcContainer.getObjects();
    }

    /**
     * @return List of items in the location
     */
    public Item getItem(final String name) {
	return ListHelper.findObjectInList(itemContainer.getObjects(), name);
    }

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
