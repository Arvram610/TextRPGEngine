package se.liu.arvra591.objects.creatures;

import java.util.ArrayList;
import java.util.List;

public class NpcDialogue
{
    List<String> dialogue;

    public static NpcDialogue emptyDialogue = new NpcDialogue(new ArrayList<>());

    public NpcDialogue(List<String> dialogue){
	this.dialogue = dialogue;
    }
    public void printDialogue(String name) {
	for (String s : dialogue) {
	    System.out.println(name + ": " + s);
	}
    }
}
