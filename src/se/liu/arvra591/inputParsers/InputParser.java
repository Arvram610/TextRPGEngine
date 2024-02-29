package se.liu.arvra591.inputParsers;

import se.liu.arvra591.ParseAction;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for input parsers. All input parsers inherit from this class.
 * The class has a map of all available commands and a method to parse the input and call the corresponding action
 */
public abstract class InputParser
{

    protected Map<String, ParseAction> parseInputs;

    protected InputParser(){
	parseInputs = new HashMap<>();
	parseInputs.put("help", this::printActions);
    }
    private String removeActionFromInput(String input, String action){
	return input.replace(action+" ", "");
    }

    /**
     * @param input The input from the user
     * Prints all available commands
     */
    public void printActions(String input){
	System.out.println("Available commands:");
	parseInputs.forEach((key, value) -> {
	    System.out.println("  " + key);
	});
    }

    /**
     * @param input The input from the user
     * Parses the input and calls the corresponding action
     */
    //Kodgranskning says that input is not used but is wrong
    public void parseInput(String input){
	String[] inputs = input.split(" ");
	parseInputs.forEach((key, value) -> {
	    if (inputs[0].toLowerCase().equals(key))
		value.performAction(removeActionFromInput(input, key));
	});
    }

}
