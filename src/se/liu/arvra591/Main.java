package se.liu.arvra591;

import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;
import se.liu.arvra591.game.generators.GameGenerator;

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

    /**
     * Constructor for the main class, initializes what is needed for the game to run
     */
    public Main(){
	GameGenerator gameGenerator = new GameGenerator();
	gameGenerator.generateGame();
	player = gameGenerator.getPlayer();
	locations = gameGenerator.getLocations();
	items = gameGenerator.getItems();
	npcs = gameGenerator.getNpcs();
    }

    /**
     * This method starts the game
     */
    public void startGame(){
	player.printObject();
    }


    /**
     * The method used to start the game
     *
     * @param args Ignored
     */
    public static void main(String[] args) {
	Main main = new Main();
	main.startGame();
    }
}
