package se.liu.arvra591.game.objects.creatures;

/**
 * Class for the logic of the npcs The npcs will have a set of actions they can do, and will do one of them at the start of their turn
 */
public class NpcLogic
{
    private static final int ENERGY_COST = 5;
    private static final int REST_ENERGY_REGENERATION = 15;
    private Npc npc;

    public NpcLogic(Npc npc)
    {
	this.npc = npc;
    }

    public void setNpc(Npc npc) {
	this.npc = npc;
    }

    public void startOfTurn() {
	npc.addEnergy(Integer.toString(npc.getStats().getEnergyRegenerationRate()));
	if (npc.getCurrentEnergy() >= ENERGY_COST) {
	    attack();
	} else {
	    rest();
	}
    }

    /**
     * The npc attacks the player
     */
    private void attack() {
	npc.attack();
    }

    /**
     * @param index Random number to choose which item to use
     */

    private void rest() {
	npc.rest(REST_ENERGY_REGENERATION);
    }
}
