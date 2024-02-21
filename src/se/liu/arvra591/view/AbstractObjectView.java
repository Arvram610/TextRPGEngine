package se.liu.arvra591.view;

import se.liu.arvra591.models.AbstractObject;

/**
 * Superclass for all viewer-classes
 */
public abstract class AbstractObjectView
{

    public void printModel(final AbstractObject model) {
        System.out.println("Name: " + model.getName());
        System.out.println("Description: " + model.getDescription());
        System.out.println("Type: " + model.getClass().getSimpleName());
    }
}
