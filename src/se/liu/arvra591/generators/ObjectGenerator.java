package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.jsonParser.JsonParser;
import se.liu.arvra591.objects.AbstractObject;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.items.Item;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An abstract class that generates items of type {@link T}
 *
 * @param <T> The type of item the generator generates
 */
public abstract class ObjectGenerator<T> extends Generator
{

    protected Map<String, T> objects;

    protected ObjectGenerator() {
	super();
	objects = new HashMap<>();
    }

    /**
     * Generates the objects from given file
     *
     * @param fileName The file that the object come from
     *
     * @throws FileNotFoundException
     */
    public abstract void genObjects(String fileName) throws FileNotFoundException;

    protected abstract void genObject(JsonObject object);

    protected void genObjects(JsonArray jsonArray){
	for (JsonElement jsonElement : jsonArray) {
	    genObject(jsonElement.getAsJsonObject());
	}
    }

    protected CreatureStats genCreatureStats(JsonObject object){
	int attack = object.get("attack").getAsInt();
	int def = object.get("defense").getAsInt();
	int maxHealth = object.get("maxHealth").getAsInt();
	int maxEnergy = object.get("maxEnergy").getAsInt();
	int energyRegen = object.get("energyRegen").getAsInt();

	return new CreatureStats(maxHealth, attack, def, maxEnergy, energyRegen);
    }

    /**
     * @return Returns the generated objects
     */
    public Map<String, T> getObjects(){
	return objects;
    }
}
