package se.liu.arvra591.view.creatures;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.Model;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.creatures.CreatureStats;
import se.liu.arvra591.view.AbstractObjectView;

public class CreatureView extends AbstractObjectView
{
    CreatureStatsView creatureStatsView;
    public CreatureView(final AbstractObject model) {
	super(model);
	creatureStatsView = new CreatureStatsView(((Creature) model).getStats());
    }

    @Override public void printModel() {
	super.printModel();
	Creature model = (Creature) this.model;
	System.out.println("Health: " + model.getHealth());
	creatureStatsView.printModel();
    }

    public static void main(String[] args) {

	Creature c = new Creature("H", "j", 10,
				  new CreatureStats(10,10,10,10,10, 10));
	CreatureView v = new CreatureView(c);
    	v.printModel();
    }
}
