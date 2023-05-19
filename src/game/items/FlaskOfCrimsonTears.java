package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.utils.Status;

/**
 * Flask of Crimson Tears.
 * This item the player starts of with, and has a specific capacity
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class FlaskOfCrimsonTears extends Item implements Consumable {
    /**
     * The capacity of the item
     */
    private int capacity;

    /**
     * Stores the initial capacity
     */
    private final int initialCapacity;

    /**
     * How much health we will gain once consumed
     */
    private final int healingPower;

    /**
     * This will hold our ConsumeAction
     */
    private final Action consumeAction;



    /**
     * Constructor
     */
    public FlaskOfCrimsonTears() {
        // Super class attributes
        super("Flask of Crimson Tears", '+', false);

        // Max capacity of 2 uses
        this.capacity = 2;

        // Set the initial capacity
        this.initialCapacity = capacity;

        // The player will gain 250 HP once consumed
        this.healingPower = 250;

        // Initialise the ConsumeAction
        this.consumeAction = new ConsumeAction(this);
    }



    /**
     * Inform a carried Item of the passage of time.
     * Since this method is called when Item is carried, ConsumeAction will be added.
     * @param currentLocation - The location of the actor carrying this Item.
     * @param actor - The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (!hasCapability(Status.IN_INVENTORY)) {
            this.addAction(consumeAction);
            this.addCapability(Status.IN_INVENTORY);
        }
    }



    /**
     * Called when the player uses the ConsumeAction
     * @param actor Actor to buff health
     */
    @Override
    public void consumedBy(Actor actor, GameMap map) {
        // Remove item from ground, in case Actor consumes this Item while it's on the ground.
        map.locationOf(actor).removeItem(this);

        // Heal the player
        actor.heal(healingPower);

        // Decrement the capacity
        capacity--;

        // If we have used up the capacity of the item
        if (capacity <= 0) {
            // Remove the ability to consume this
            removeAction(consumeAction);

            // Make portable so we can drop
            togglePortability();

            // Drop this item
            getDropAction(actor);

            // Make it non-portable again, so we can't pick it up
            togglePortability();
        }


    }

    @Override
    public String toString() {
        return super.toString() + " (" + capacity + "/" + initialCapacity + ")";
    }
}
