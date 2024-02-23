package se.liu.arvra591.models.locations;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Locations extends AbstractObject
{
    private List<Creature> creatureList; //might be divided into enemies and players
    private List<Item> itemList;
    private List<Locations> exitList;

    //private List<interactables> interactablesList; //might be added later


    public Locations(String name, String description, List<Creature> creatureList, List<Item> itemList, List<Locations> exitList) {
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

    public List<Locations> getExitList() {
	return exitList;
    }
}
