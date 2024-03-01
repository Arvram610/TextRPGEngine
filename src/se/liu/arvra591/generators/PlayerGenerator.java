package se.liu.arvra591.generators;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import se.liu.arvra591.objects.creatures.CreatureStats;
import se.liu.arvra591.objects.creatures.Player;
import se.liu.arvra591.objects.creatures.PlayerStats;
import se.liu.arvra591.objects.locations.Location;

import java.io.IOException;

public class PlayerGenerator extends ObjectGenerator<Player>
{

    @Override public void genObjects(final String fileName) throws IOException {
	JsonArray jsonArray = loadJsonFile("player/" + fileName);
	genObjects(jsonArray);
    }

    @Override protected void genObject(final JsonObject object) {
	String name = object.get("name").getAsString();
	String desc = object.get("desc").getAsString();
	int health = object.get("health").getAsInt();
	PlayerStats stats = genPlayerStats(object.get("stats").getAsJsonObject());
	Location location = ;
	objects.put("player", new Player(name, desc, health, stats, location, inventory));
    }

    private PlayerStats genPlayerStats(JsonObject object){
	int attack = object.get("attack").getAsInt();
	int def = object.get("defense").getAsInt();
	int maxHealth = object.get("maxHealth").getAsInt();
	int maxEnergy = object.get("maxEnergy").getAsInt();
	int energyRegen = object.get("energyRegen").getAsInt();
	int carryWeight = object.get("carryWeight").getAsInt();
	return new PlayerStats(maxHealth, attack, def, maxEnergy, energyRegen, carryWeight);
    }
}
