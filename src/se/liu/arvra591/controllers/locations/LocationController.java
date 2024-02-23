package se.liu.arvra591.controllers.locations;

import se.liu.arvra591.controllers.AbstractObjectController;
import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.creatures.CreatureStats;
import se.liu.arvra591.models.items.Item;
import se.liu.arvra591.models.locations.Locations;
import se.liu.arvra591.view.AbstractObjectView;
import se.liu.arvra591.view.locations.LocationView;

import java.util.ArrayList;
import java.util.List;

public class LocationController extends AbstractObjectController
{
    private LocationController(final Locations model, final LocationView view) {
	super(model, view);
    }

    public static void main(String[] args) {
	List<Creature> creatureList = new ArrayList<>();
	List<Item> itemList = new ArrayList<>();
	List<Locations> exitList = new ArrayList<>();
	creatureList.add(new Creature("TestCreature", "TestDescription", 10,
				      new CreatureStats(10, 10, 10, 10, 10, 10)));
	itemList.add(new Item("TestItem", "TestDescription", 10));
	itemList.add(new Item("TestItem2", "TestDescription2", 20));


	LocationController locationController = new LocationController(new Locations("TestLocation", "TestDescription",
								 creatureList, itemList, exitList), new LocationView());
	locationController.updateView();
    }
}
