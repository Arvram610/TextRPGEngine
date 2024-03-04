package se.liu.arvra591.game.objects.creatures;

import se.liu.arvra591.game.objects.containers.CreatureInventory;

import java.util.Arrays;
import java.util.List;

/**
 * Class for non-player characters such as enemies, pets or vendors. All npcs have a dialogue but this can be empty for creatures
 *  that cant talk exs pets.
 *  Npcs are a subclass of {@link Creature}
 *
 */
public class Npc extends Creature
{
    private final List<List<String>> npcDialogues;
    private boolean canDisengage;

    private int timesTalked;

    /**
     * @param name what the npc is called
     * @param description describes the npc
     * @param currentHealth how much damage the npc can take
     * @param stats the stats of the npc
     * @param dialogue what the npc says
     * @param inventory the inventory of the npc
     */
    public Npc(final String name, final String description,
	       final int currentHealth, int currentEnergy, final CreatureStats stats, final List<List<String>> npcDialogues,
	       CreatureInventory inventory, boolean canDisengage) {
	super(name, description, currentHealth, currentEnergy, stats, inventory);
	this.npcDialogues = npcDialogues;
	this.canDisengage = canDisengage;
	this.timesTalked = 0;
    }

    /**
     * Prints the npc dialogue
     */
    public void talk(){
	sendCommands(npcDialogues.get(Math.min(timesTalked, npcDialogues.size())));
	timesTalked++;
    }

    /**
     * to be removed
     */

    public boolean takeDamage(int damage){
	currentHealth -= damage;
	if (currentHealth <= 0) {
	    //System.out.println(getName() + " has died");
	    return true;
	}
	return false;
    }

    /**
     * @return Returns true if the player can disengage from the npc
     */
    public boolean getCanDisengage(){
	return canDisengage;
    }
}
