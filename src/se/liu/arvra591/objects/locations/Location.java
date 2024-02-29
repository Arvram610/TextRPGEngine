package se.liu.arvra591.objects.locations;

import se.liu.arvra591.ListHelper;
import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.NpcDialogue;
import se.liu.arvra591.objects.itemContainers.ObjectContainer;
import se.liu.arvra591.objects.items.Item;

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

    //private List<interactables> interactablesList; //might be added later


    public Location(String name, String description, List<Npc> npcList, List<Item> itemList, List<Location> exitList) {
	super(name, description);
	this.npcContainer = new ObjectContainer<>(npcList);
	this.itemContainer = new ObjectContainer<>(itemList);
	this.exitContainer = new ObjectContainer<>(exitList);
    }

    public void addExit(Location location){
	exitContainer.forceAddObject(location);
    }

    public void removeExit(Location location){
	exitContainer.removeObject(location);
    }

    public Item removeItem(String name){
	return itemContainer.removeObject(name);
    }


    public void inspect(String name){
	List<List<? extends AbstractObject>> lists = Arrays.asList(npcContainer.getObjectList(),
								   itemContainer.getObjectList(),
								   exitContainer.getObjectList());
	AbstractObject object = ListHelper.findObjectInLists(lists, name);

	if (object != null){
	    object.printObject();
	    return;
	}
	System.out.println("Couldn't find " + name + " in location.");
    }

    public Location getExit(final String name) {
	return (Location) ListHelper.findObjectInList(exitContainer.getObjectList(), name);
    }

    @Override public void printObject() {

	System.out.print("Location: ");
	printName();
	System.out.print("Description: ");
	printDescription();

	System.out.println("Items in location: ");
	ListHelper.printList(itemContainer.getObjectList(), true);
	System.out.println();

	System.out.println("Creatures in location: ");
	ListHelper.printList(npcContainer.getObjectList(), true);
	System.out.println();

	System.out.println("Exits in location: ");
	ListHelper.printList(exitContainer.getObjectList(), true);
	System.out.println();
    }


    public static void main(String[] args) {
	List<Npc> npcList = new ArrayList<>();
	List<Item> itemList = new ArrayList<>();

	List<Location> exitList = new ArrayList<>();
	npcList.add(new Npc("TestCreature", "TestDescription", 10,
				 new CreatureStats(10, 10, 10, 10, 10),
				 NpcDialogue.emptyDialogue, null));
	itemList.add(new Item("TestItem", "TestDescription", 10));
	itemList.add(new Item("TestItem2", "TestDescription2", 20));
	exitList.add(new Location("TestLocation", "TestDescription", new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));


	Location location = new Location("TestLocation", "TestDescription", npcList, itemList, exitList);
	location.printObject();

	location.inspect("TestItem");

    }
}
