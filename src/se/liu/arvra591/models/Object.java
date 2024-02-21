package se.liu.arvra591.models;

public abstract class Object
{
    private String name;
    private String description;

    protected Object(String name, String description){
        this.name = name;
        this.description = description;
    }

}
