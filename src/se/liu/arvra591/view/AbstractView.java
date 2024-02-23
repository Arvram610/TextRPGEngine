package se.liu.arvra591.view;

import se.liu.arvra591.models.AbstractObject;
import se.liu.arvra591.models.Model;

public abstract class AbstractView implements View
{
    protected Model model;
    protected AbstractView(Model model){
	this.model = model;
    }

}
