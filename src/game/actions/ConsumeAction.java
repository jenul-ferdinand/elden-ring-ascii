package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;
import game.items.Consumable;

/**
 * Consumption of consumable items.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class ConsumeAction extends Action {
    /**
     * Variable to store our consumable item
     */
    private Consumable consumable;



    /**
     * Constructor
     * @param consumable The consumable item
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }



    /**
     * Call the consumedBy method of the consumable
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String The confirmation message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Consume the item and pass the actor
        consumable.consumedBy(actor);

        // Remove the item from the location of the ground
        map.locationOf(actor).removeItem((Item) consumable);

        // Return the confirmation message
        return actor + " consumes " + consumable;
    }



    /**
     * The menu option text String
     * @param actor The actor performing the action.
     * @return String The menu option
     */
    @Override
    public String menuDescription(Actor actor) {
        return "✨ Consume " + consumable;
    }
}
