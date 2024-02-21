package se.liu.arvra591.controllers.items;

import se.liu.arvra591.controllers.AbstractObjectController;
import se.liu.arvra591.models.items.Item;
import se.liu.arvra591.view.items.ItemView;


/**
 * Class to control items
 */
public class ItemController extends AbstractObjectController
{
    public ItemController(final Item model, final ItemView view) {
	super(model, view);
    }
}
