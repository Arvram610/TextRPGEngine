package se.liu.arvra591.view.creatures;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.creatures.CreatureStats;
import se.liu.arvra591.view.AbstractObjectView;

public class CreatureStatsView extends AbstractObjectView
{
    @Override public void printModel(AbstractObject model) {
	CreatureStats creatureStatsModel = (CreatureStats) model;
	System.out.println("Attack: " + creatureStatsModel.getAttack());
	System.out.println("Defense: " + creatureStatsModel.getDefense());
	System.out.println("Max Health: " + creatureStatsModel.getMaxHealth());
	System.out.println("Max Energy: " + creatureStatsModel.getMaxEnergy());
	System.out.println("Energy Regen: " + creatureStatsModel.getEnergyRegenRate());
    }
}
