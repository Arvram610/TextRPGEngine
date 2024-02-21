package se.liu.arvra591.view.creatures;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.Model;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.creatures.CreatureStats;
import se.liu.arvra591.view.AbstractObjectView;

public class CreatureView extends AbstractObjectView
{
    CreatureStatsView creatureStatsView = new CreatureStatsView();
    @Override public void printModel(Creature model) {
	super.printModel(model);
	Creature creatureModel = (Creature) model;
	System.out.println("Health: " + creatureModel.getHealth());
	creatureStatsView.printModel(creatureModel.getStats());
    }

    public static void main(String[] args) {
	CreatureView v = new CreatureView();
	Creature c = new Creature("H", "j", 10,
				  new CreatureStats(10,10,10,10,10, 10));
    	v.printModel(c);
    }
}
