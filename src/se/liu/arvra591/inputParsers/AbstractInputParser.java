package se.liu.arvra591.inputParsers;

import se.liu.arvra591.Adventure;
import se.liu.arvra591.ParseAction;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractInputParser
{

    protected Map<String, ParseAction> parseInputs;

    public AbstractInputParser(){
	parseInputs = new HashMap<>();
	parseInputs.put("help", this::printActions);
    }

    private String inputParemeter(String input, String action){
	return input.replace(action+" ", "");
    }

    public void printActions(String input){
	System.out.println("Available commands:");
	parseInputs.forEach((key, value) -> {
	    System.out.println("  " + key);
	});
    }

    public void parseInput(String input){
	String[] inputs = input.split(" ");
	parseInputs.forEach((key, value) -> {
	    if (inputs[0].toLowerCase().equals(key))
		value.action(inputParemeter(input, key));
	});
	/*
//	 ska också skriva en funktion printActions som skriver ut alla actions som finns
	 */
    }

}
