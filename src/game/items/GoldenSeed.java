package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.utils.Status;

/**
 * Golden Seed.
 * This increases the number of uses the FlaskOfCrimsonTears has
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class GoldenSeed extends Item implements Consumable {
    /**
     * This will store the Item instance of the flask
     */
    private Enhanceable targetFlask;

    /**
     * This will hold our ConsumeAction
     */
    private final Action consumeAction;

    /**
     * This will store the string with the name of the flask
     */
    private String string;



    /**
     * Constructor
     */
    public GoldenSeed() {
        super("Golden Seed", 'g', true);

        this.string = "";

        this.consumeAction = new ConsumeAction(this);
    }



    /**
     * Inform a carried Item of the passage of time.
     * This method is called once per turn, if the Item is being carried.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // INSIDE INVENTORY
        // If not in inventory
        if (!hasCapability(Status.IN_INVENTORY)) {

            // Loop through the items in the actors item inventory
            for (Item item : actor.getItemInventory()) {

                // Check if that item has the capability FLASK
                if (item.hasCapability(Status.FLASK)) {

                    // Every flask should be an Enhanceable item, therefore downcast.
                    Enhanceable enhanceable = (Enhanceable) item;

                    // Add the ConsumeAction
                    this.addAction(consumeAction);

                    // Set the target flask instance as the current item
                    targetFlask = enhanceable;

                    // String
                    string = " which can enhance " + targetFlask;
                    string = string.substring(0, string.length() - 5);
                }
            }

            // Add capability in inventory
            this.addCapability(Status.IN_INVENTORY);
        }
    }



    /**
     * Inform an Item on the ground of the passage of time.
     * This method is called once per turn, if the item rests upon the ground.
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        // DROPPED ITEM
        // Has capability IN_INVENTORY
        if (hasCapability(Status.IN_INVENTORY)) {
            // Remove the capability
            this.removeCapability(Status.IN_INVENTORY);

            // Remove the ConsumeAction
            this.removeAction(consumeAction);
        }
    }



    /**
     * Enhance the target flask
     * @param actor - Actor
     * @param map - GameMap
     */
    @Override
    public void consumedBy(Actor actor, GameMap map) {
        // Enhance the target flask
        targetFlask.enhance();

        // Remove the ConsumeAction
        this.removeAction(consumeAction);

        // Drop the item
        this.getDropAction(actor);

        // Make non portable
        this.togglePortability();

        // Remove the item from the ground
        map.locationOf(actor).removeItem(this);
    }



    /**
     * toString
     * @return String - Returns the string
     */
    public String toString() {
        return super.toString() + string;
    }
}
