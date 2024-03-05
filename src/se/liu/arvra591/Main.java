package se.liu.arvra591;

import se.liu.arvra591.game.Game;
import se.liu.arvra591.game.factories.Factory;
import se.liu.arvra591.game.generators.GameGenerator;
import se.liu.arvra591.game.listeners.CommandHandler;
import se.liu.arvra591.game.objects.creatures.Npc;
import se.liu.arvra591.game.objects.creatures.Player;
import se.liu.arvra591.game.objects.items.Item;
import se.liu.arvra591.game.objects.locations.Location;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.FileHandler;

/**
 * The main class for the game This is the file you run to start the game It handles logic on what mode the player is in (ex movement or
 * combat)
 */
public class Main
{
    //private Player player;
    private Game game;

    /**
     * Constructor for the main class, initializes what is needed for the game to run
     */
    public Main() {
	FileHandler fileHandler = null;
	try {
	    fileHandler = new FileHandler("log");
	} catch (IOException ignored) {
	    System.out.println("Could not find log file");
	    System.exit(1);
	}
	CommandHandler commandHandler = new CommandHandler();
	GameGenerator gameGenerator = new GameGenerator(commandHandler, fileHandler);
	gameGenerator.generateGame();
	Player player = gameGenerator.getPlayer();
	Map<String, Location> locations = gameGenerator.getLocations();
	Map<String, Factory<? extends Item>> items = gameGenerator.getItems();
	Map<String, Factory<? extends Npc>> npcs = gameGenerator.getNpcs();

	game = new Game(player, locations, items, npcs);

	commandHandler.setListener(game.getParser());
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

    /**
     * This method starts the game
     */
    public void startGame() {
	game.start();


	Scanner scanner = new Scanner(System.in);
	while (game.gameOn()) {
	    System.out.print(": ");
	    game.processInput(scanner.nextLine());
	}
    }
}
