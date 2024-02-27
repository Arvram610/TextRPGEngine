package se.liu.arvra591.objects.locations;

import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.creatures.Creature;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Location extends AbstractObject
{
    private List<Creature> creatureList; //might be divided into enemies and players
    private List<Item> itemList;
    private List<Location> exitList;

    //private List<interactables> interactablesList; //might be added later


    public Location(String name, String description, List<Creature> creatureList, List<Item> itemList, List<Location> exitList) {
	super(name, description);
	this.creatureList = creatureList;
	this.itemList = itemList;
	this.exitList = exitList;
    }

    public List<Creature> getCreatureList() {
	return creatureList;
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
	printList(creatureList);
	System.out.println();

	System.out.println("Exits in location: ");
	printList(exitList);
	System.out.println();
    }


    public static void main(String[] args) {
	List<Creature> creatureList = new ArrayList<>();
	List<Item> itemList = new ArrayList<>();

	List<Location> exitList = new ArrayList<>();
	creatureList.add(new Creature("TestCreature", "TestDescription", 10,
				      new CreatureStats(10, 10, 10, 10, 10)));
	itemList.add(new Item("TestItem", "TestDescription", 10));
	itemList.add(new Item("TestItem2", "TestDescription2", 20));
	exitList.add(new Location("TestLocation", "TestDescription",
				  new ArrayList<Creature>(), new ArrayList<Item>(), new ArrayList<Location>()));



	Location location = new Location("TestLocation", "TestDescription",
					 creatureList, itemList, exitList);
	location.printObject();

    }
}
