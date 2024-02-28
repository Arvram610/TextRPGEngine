package se.liu.arvra591.objects;


/**
 * All objects in the game inherit from this class, objects includes locations as they are physical
 */
public abstract class AbstractObject
{
    protected String name;
    protected String description;

    protected AbstractObject(String name, String description){
        this.name = name;
        this.description = description;
    }

    /**
     * @return Returns the name of the object
     */
    public String getObjectName(){
        return name;
    }

    /**
     * @return Returns the description of the object
     */
    public String getDescription(){
        return description;
    }

    /**
     * Prints the objects info
     */
    public void printObject(){
        System.out.println("Name: " + getObjectName());
        System.out.println("Type: " + getClass().getSimpleName());
        System.out.println("Description: " + getDescription());
    }

    /**
     * Prints the name of the object
     */
    public void printName(){
        System.out.println(getObjectName());
    }

    /**
     * Prints the description of the object
     */
    public void printDescription(){
        System.out.println(getDescription());
    }
}
