package se.liu.arvra591.view.locations;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.creatures.CreatureStats;
import se.liu.arvra591.models.items.Item;
import se.liu.arvra591.models.locations.Locations;
import se.liu.arvra591.view.AbstractObjectView;

import java.util.ArrayList;
import java.util.List;

public class LocationView extends AbstractObjectView
{
    @Override public void printModel(AbstractObject model) {
	super.printModel(model);
	Locations locationModel = (Locations) model;
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

    private void printItems(Locations locationModel) {
	for (Item item : locationModel.getItemList()) {
	    System.out.print(item.getName() + " ");
	}
    }

    private void printCreatures(Locations locationModel) {
	for (Creature creature : locationModel.getCreatureList()) {
	    System.out.println(creature.getName() + " ");
	}
    }

    private void printExits(Locations locationModel) {
	for (Locations exit : locationModel.getExitList()) {
	    System.out.println(exit.getName() + " ");
	}
    }

    public static void main(String[] args) {
	List<Creature> creatureList = new ArrayList<>();
	List<Item> itemList = new ArrayList<>();
	List<Locations> exitList = new ArrayList<>();
	creatureList.add(new Creature("TestCreature", "TestDescription", 10,
			  new CreatureStats(10, 10, 10, 10, 10,10)));
	itemList.add(new Item("TestItem", "TestDescription", 10));
	itemList.add(new Item("TestItem2", "TestDescription2", 20));
	exitList.add(new Locations("TestLocation", "TestDescription",
				  new ArrayList<Creature>(), new ArrayList<Item>(), new ArrayList<Locations>()));



	Locations location = new Locations("TestLocation", "TestDescription",
					   creatureList, itemList, exitList);
	LocationView locationView = new LocationView();
	locationView.printModel(location);
    }
}
