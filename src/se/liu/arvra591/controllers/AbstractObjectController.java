package se.liu.arvra591.controllers;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.view.AbstractObjectView;

/**
 * Superclass for all controller classes
 */
public abstract class AbstractObjectController implements Controller
{
    protected AbstractObject model;
    protected AbstractObjectView view;

    protected AbstractObjectController(AbstractObject model, AbstractObjectView view){
        this.model = model;
        this.view = view;
    }

    public void updateView(){
        view.printModel(model);
    }
}
