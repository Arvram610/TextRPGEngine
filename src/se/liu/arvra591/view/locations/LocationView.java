package se.liu.arvra591.view.locations;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.creatures.CreatureStats;
import se.liu.arvra591.models.items.Item;
import se.liu.arvra591.models.locations.Location;
import se.liu.arvra591.view.AbstractObjectView;

import java.util.ArrayList;
import java.util.List;

public class LocationView extends AbstractObjectView
{


    public LocationView(final AbstractObject model) {
	super(model);
    }

    @Override public void printModel() {
	super.printModel();
	Location locationModel = (Location) model;
	System.out.print("Items in location: ");
    	printItems(locationModel);
	System.out.println();

	System.out.print("Creatures in location: ");
	printCreatures(locationModel);
	System.out.println();

	System.out.print("Exits in location: ");
	printExits(locationModel);
	System.out.println();
    }

    private void printItems(Location locationModel) {
	for (Item item : locationModel.getItemList()) {
	    System.out.print(item.getName() + " ");
	}
    }

    private void printCreatures(Location locationModel) {
	for (Creature creature : locationModel.getCreatureList()) {
	    System.out.println(creature.getName() + " ");
	}
    }

    private void printExits(Location locationModel) {
	for (Location exit : locationModel.getExitList()) {
	    System.out.println(exit.getName() + " ");
	}
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
	LocationView locationView = new LocationView(location);
	locationView.printModel();
    }
}
