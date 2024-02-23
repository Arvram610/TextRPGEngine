package se.liu.arvra591.view;


import se.liu.arvra591.models.AbstractObject;

/**
 * Superclass for all viewer-classes
 */
public abstract class AbstractObjectView extends AbstractView
{
    protected AbstractObjectView(AbstractObject model){
        super(model);
    }

    public void printModel() {
        AbstractObject model = (AbstractObject) this.model;
        System.out.println("Name: " + model.getName());
        System.out.println("Description: " + model.getDescription());
        System.out.println("Type: " + model.getClass().getSimpleName());
    }
}
