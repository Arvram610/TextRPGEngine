package se.liu.arvra591.objects.creatures;

import java.util.Arrays;

/**
 * Class for non-player characters such as enemies, pets or vendors. All npcs have a dialogue but this can be empty for creatures
 *  that cant talk exs pets.
 *  Npcs are a subclass of {@link Creature}
 *
 */
public class Npc extends Creature
{
    protected NpcDialogue dialogue;
    public Npc(final String name, final String description, final int level,
	       final int health, final CreatureStats stats, final NpcDialogue dialogue) {
	super(name, description, health, level,  stats);
	this.dialogue = dialogue;
    }

    public void talk(){
	dialogue.printDialogue(getObjectName());
    }

    public static void main(String[] args) {
	NpcDialogue npcDialogue = new NpcDialogue(Arrays.asList("Hej!", "Hoppas du mår bra", "Ha en trevlig dag"));
	Npc npc = new Npc("Carl", "A friendly human", 10, 10, CreatureStats.basic, npcDialogue);
	npc.talk();
    }
}
