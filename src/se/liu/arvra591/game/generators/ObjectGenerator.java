package se.liu.arvra591.game.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.creatures.CreatureStats;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;

/**
 * An abstract class that generates items of type {@link T}
 *
 * @param <T> The type of item the generator generates
 */
public abstract class ObjectGenerator<T> extends Generator
{

    protected Map<String, T> objects;

    protected CommandHandler commandHandler;

    protected ObjectGenerator(CommandHandler commandHandler, FileHandler fileHandler) {
	super(fileHandler);
	objects = new HashMap<>();
	this.commandHandler = commandHandler;
    }

    /**
     * Generates the objects from given file
     *
     * @param fileName The file that the object come from
     *
     * @throws FileNotFoundException
     */
    public abstract void generateObjects(String fileName) throws FileNotFoundException;

    protected abstract void generateObject(JsonObject object);

    protected void generateObjects(JsonArray jsonObjects) {
	for (JsonElement jsonElement : jsonObjects) {
	    generateObject(jsonElement.getAsJsonObject());
	}
    }

    protected CreatureStats generateCreatureStats(JsonObject object) {
	int attack = object.get("attack").getAsInt();
	int defense = object.get("defense").getAsInt();
	int maxHealth = object.get("maxHealth").getAsInt();
	int maxEnergy = object.get("maxEnergy").getAsInt();
	int energyRegen = object.get("energyRegen").getAsInt();

	return new CreatureStats(maxHealth, attack, defense, maxEnergy, energyRegen);
    }

    /**
     * @return Returns the generated objects
     */
    public Map<String, T> getObjects() {
	return objects;
    }
}
