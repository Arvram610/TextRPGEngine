package se.liu.arvra591.objects;


/**
 * Superclass for all objects
 */
public abstract class AbstractObject implements Object
{
    protected String name;
    protected String description;

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

    public void printObject(){
        System.out.println("Name: " + getName());
        System.out.println("Type: " + getClass().getSimpleName());
        System.out.println("Description: " + getDescription());
    }

    public void printName(){
        System.out.println(getName());
    }

    public void printDescription(){
        System.out.println(getDescription());
    }
}
