package se.liu.arvra591.view.creatures;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.Model;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.creatures.CreatureStats;
import se.liu.arvra591.view.AbstractObjectView;
import se.liu.arvra591.view.AbstractView;
import se.liu.arvra591.view.View;

public class CreatureStatsView extends AbstractView
{

    protected CreatureStatsView(final CreatureStats model) {
	super(model);
    }

    @Override public void printModel() {
	CreatureStats model = (CreatureStats) this.model;
	System.out.println("Attack: " + model.getAttack());
	System.out.println("Defense: " + model.getDefense());
	System.out.println("Max Health: " + model.getMaxHealth());
	System.out.println("Max Energy: " + model.getMaxEnergy());
	System.out.println("Energy Regen: " + model.getEnergyRegenRate());
    }
}
