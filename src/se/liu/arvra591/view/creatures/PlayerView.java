package se.liu.arvra591.view.creatures;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.creatures.Creature;
import se.liu.arvra591.models.creatures.CreatureStats;
import se.liu.arvra591.models.creatures.Player;
import se.liu.arvra591.view.AbstractObjectView;

public class PlayerView extends AbstractObjectView
{
    protected CreatureStatsView creatureStatsView;

    public PlayerView(final AbstractObject model){
	super(model);
	creatureStatsView = new CreatureStatsView(((Creature) model).getStats());

    }

    @Override public void printModel() {
	super.printModel();
	Player model = (Player) this.model;
	System.out.println("Health: " + model.getHealth());
	creatureStatsView.printModel();
	System.out.println("Carry Weight: " + model.getCarryWeight());
	System.out.println("Current Weight: " + model.getCurrentWeight());
    }

    public static void main(String[] args) {
	Player p = new Player("H", "j", 10,
				  new CreatureStats(10, 10, 10, 10, 10), 10, 5);
	PlayerView v = new PlayerView(p);
	v.printModel();
    }
}
