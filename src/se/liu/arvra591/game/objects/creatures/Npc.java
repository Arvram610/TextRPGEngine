package se.liu.arvra591.game.objects.creatures;

import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.containers.CreatureInventory;
import se.liu.arvra591.game.objects.items.Item;

import java.util.Arrays;

/**
 * Class for non-player characters such as enemies, pets or vendors. All npcs have a dialogue but this can be empty for creatures
 *  that cant talk exs pets.
 *  Npcs are a subclass of {@link Creature}
 */
public class Npc extends Creature
{
    protected NpcDialogue dialogue;
    private boolean canDisengage;

    /**
     * @param name what the npc is called
     * @param description describes the npc
     * @param currentHealth how much damage the npc can take
     * @param stats the stats of the npc
     * @param dialogue what the npc says
     * @param inventory the inventory of the npc
     */
    public Npc(final String name, final String description,
	       final int currentHealth, int currentEnergy, final CreatureStats stats, final NpcDialogue dialogue,
	       CreatureInventory inventory, boolean canDisengage) {
	super(name, description, currentHealth, currentEnergy, stats, inventory);
	this.dialogue = dialogue;
	this.canDisengage = canDisengage;
    }

    /**
     * Prints the npc dialogue
     */
    public void talk(){
	dialogue.printDialogue(getName());
    }

    /**
     * @return Returns true if the player can disengage from the npc
     */
    public boolean getCanDisengage(){
	return canDisengage;
    }

    /**
     * @return Returns the inventory of the npc
     */
    public CreatureInventory getInventory(){
	return inventory;
    }

    /**
     * Puts player back into adventure mode and drops all the items in the NPCs inventory
     */
    public void onDeath(){
	sendCommand("say " + name + " dropped: ");
	for (Item item : inventory.getObjects()) {
	    sendCommand("spawnitem " + item.getName());
	    sendCommand("say   " + item.getName());
	}
	sendCommand("removenpc " + name);
	sendCommand("disengage");
    }

    /**
     * Attacks the player with the current damage of the npc
     */
    public void attack(){
	CreatureStats stats = getStats();
	String attack = Integer.toString(stats.getAttack());
	sendCommand("attackplayer " + attack);
    }

    /**
     * @param energyRegen is the amount of energy the npc will regain
     */
    public void rest(int energyRegen){
	String restEnergyRegen = Integer.toString(energyRegen);
	addEnergy(restEnergyRegen);
    }
    public static void main(String[] args) {
	NpcDialogue npcDialogue = new NpcDialogue(Arrays.asList("Hej!", "Hoppas du mår bra", "Ha en trevlig dag"));
	Npc npc = new Npc("Carl", "A friendly human", 10, 10, CreatureStats.basic, npcDialogue, null, true);
	npc.talk();
    }
}
