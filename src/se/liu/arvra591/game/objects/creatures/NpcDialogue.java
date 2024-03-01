package se.liu.arvra591.game.objects.creatures;

import java.util.List;

/**
 * A class that holds the dialogue for an npc and can print it
 */
public class NpcDialogue
{
    private List<String> dialogue;
    /**
     * @param dialogue is the dialogue of the npc
     */
    public NpcDialogue(List<String> dialogue){
	this.dialogue = dialogue;
    }

    /**
     * @param name is the name of the npc
     * Prints the dialogue of the npc
     */
    public void printDialogue(String name) {
	for (String s : dialogue) {
	    System.out.println(name + ": " + s);
	}
    }
}
