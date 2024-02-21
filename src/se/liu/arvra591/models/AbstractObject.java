package se.liu.arvra591.models;


/**
 * Superclass for all objects
 */
public abstract class AbstractObject
{
    private String name;
    private String description;

    protected AbstractObject(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }
}