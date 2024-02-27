package se.liu.arvra591.objects.locations;

import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.creatures.Creature;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.NpcDialogue;
import se.liu.arvra591.objects.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Location extends AbstractObject
{
    private List<Npc> npcList; //might be divided into enemies and players
    private List<Item> itemList;
    private List<Location> exitList;

    //private List<interactables> interactablesList; //might be added later


    public Location(String name, String description, List<Npc> npcList, List<Item> itemList, List<Location> exitList) {
	super(name, description);
	this.npcList = npcList;
	this.itemList = itemList;
	this.exitList = exitList;
    }

    public List<Npc> getNpcList() {
	return npcList;
    }

    public List<Item> getItemList() {
	return itemList;
    }

    public List<Location> getExitList() {
	return exitList;
    }


    private void printList(List<? extends AbstractObject> list) {
	for (AbstractObject item : list) {
	    System.out.println("\t" + item.getName());
	}
    }

    @Override public void printObject() {

	System.out.print("Location: ");
	printName();
	System.out.print("Description: ");
	printDescription();

	System.out.println("Items in location: ");
	printList(itemList);
	System.out.println();

	System.out.println("Creatures in location: ");
	printList(npcList);
	System.out.println();

	System.out.println("Exits in location: ");
	printList(exitList);
	System.out.println();
    }


    public static void main(String[] args) {
	List<Npc> npcList = new ArrayList<>();
	List<Item> itemList = new ArrayList<>();

	List<Location> exitList = new ArrayList<>();
	npcList.add(new Npc("TestCreature", "TestDescription", 10,
				 new CreatureStats(10, 10, 10, 10, 10),
				 NpcDialogue.emptyDialogue));
	itemList.add(new Item("TestItem", "TestDescription", 10));
	itemList.add(new Item("TestItem2", "TestDescription2", 20));
	exitList.add(new Location("TestLocation", "TestDescription",
				  new ArrayList<>(), new ArrayList<>(), new ArrayList<>()));



	Location location = new Location("TestLocation", "TestDescription",
					 npcList, itemList, exitList);
	location.printObject();

    }
}