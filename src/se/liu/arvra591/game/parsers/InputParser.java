package se.liu.arvra591.game.parsers;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for input parsers. All input parsers inherit from this class. The class has a map of all available commands and a method
 * to parse the input and call the corresponding action
 */
public abstract class InputParser
{
    protected Map<String, ParseAction> parseInputs;

    protected InputParser() {
	parseInputs = new HashMap<>();
	parseInputs.put("help", this::printActions);
    }

    private String removeActionFromInput(String input, String action) {
	return input.replace(action + " ", "");
    }

    /**
     * @param ignored will be empty string Prints all available commands
     */
    private void printActions(String ignored) {
	System.out.println("Available commands:");
	parseInputs.forEach((key, value) -> System.out.println("  " + key));
    }

    /**
     * @param input The input from the user Parses the input and calls the corresponding action
     */
    //Kodgranskning says that input is not used but is wrong
    public void parseInput(String input) {
	String[] inputs = input.split(" ");

	ParseAction action = parseInputs.get(inputs[0].toLowerCase());
	if (action != null) {
	    action.performAction(removeActionFromInput(input, inputs[0]));
	} else {
	    System.out.println("Invalid command");
	}

    }

}
