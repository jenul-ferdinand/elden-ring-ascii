package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Collectable;



/**
 * Consumption of consumable items
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class RuneCollectAction extends Action {



    /**
     *
     */
    Collectable collectable;



    /**
     * Constructor
     * @param collectable The collectable item
     */
    public RuneCollectAction(Collectable collectable) {
        this.collectable = collectable;
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
        collectable.collectedBy(actor);

        // Remove the item from the ground
        map.locationOf(actor).removeItem((Item) collectable);

        // Return the confirmation message
        return actor + " consumes " + collectable;
    }



    /**
     * The menu option text String
     * @param actor The actor performing the action.
     * @return String The menu option
     */
    @Override
    public String menuDescription(Actor actor) {
        return "✨ Consume " + collectable;
    }
}
