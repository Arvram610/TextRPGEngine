package se.liu.arvra591.objects.creatures;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that holds the dialogue for an npc and can print it
 */
public class NpcDialogue
{
    private List<String> dialogue;

    public NpcDialogue(List<String> dialogue){
	this.dialogue = dialogue;
    }
    public void printDialogue(String name) {
	for (String s : dialogue) {
	    System.out.println(name + ": " + s);
	}
    }
}
