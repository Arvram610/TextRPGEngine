package se.liu.arvra591;

import se.liu.arvra591.factories.Factory;
import se.liu.arvra591.generators.GameGenerator;
import se.liu.arvra591.objects.creatures.Npc;
import se.liu.arvra591.objects.creatures.Player;
import se.liu.arvra591.objects.items.Item;
import se.liu.arvra591.objects.locations.Location;

import java.util.Map;

/**
 * The main class for the game
 * This is the file you run to start the game
 * It handles logic on what mode the player is in (ex movement or combat)
 */
public class Main
{
    private Player player;
    private Map<String, Location> locations;
    private Map<String, Factory<? extends Item>> items;
    private Map<String, Factory<? extends Npc>> npcs;

    public Main(){
	GameGenerator gameGenerator = new GameGenerator();
	gameGenerator.generateGame();
	player = gameGenerator.getPlayer();
	locations = gameGenerator.getLocations();
	items = gameGenerator.getItems();
	npcs = gameGenerator.getNpcs();
    }

    public void startGame(){
	player.printObject();
    }


    public static void main(String[] args) {
	Main main = new Main();
	main.startGame();
    }
}
