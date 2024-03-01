package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.NpcDialogue;

import java.io.IOException;
import java.util.List;

public class NpcFactoryGenerator extends ObjectGenerator<Factory<? extends Npc>>
{
    @Override public void genObjects(final String fileName) throws IOException {
	JsonArray jsonArray = loadJsonFile("npcs/" + fileName);
	genObjects(jsonArray);
    }

    @Override protected void genObject(final JsonObject object) {
	String type = object.get("type").getAsString();
	String name = object.get("name").getAsString();
	String desc = object.get("description").getAsString();
	int weight = object.get("weight").getAsInt();
	List<String> new NpcDialogue();

	objects.put(name, new Factory<>(new Npc(name, desc)));
    }
}
