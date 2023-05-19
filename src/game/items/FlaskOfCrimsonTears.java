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
public class FlaskOfCrimsonTears extends Item implements Consumable, Enhanceable {
    /**
     * The capacity of the item
     */
    private int capacity;

    /**
     * Stores the initial capacity
     */
    private int maxCapacity;

    /**
     * How much health we will gain once consumed
     */
    private final int healingPower;

    /**
     * This will hold our ConsumeAction
     */
    private final Action consumeAction;

    /**
     * Refilled boolean to check stuff
     */
    private boolean refilled;


    /**
     * Constructor
     */
    public FlaskOfCrimsonTears() {
        // Super class attributes
        super("Flask of Crimson Tears", '+', false);

        // Max capacity of 2 uses
        this.capacity = 2;

        // Set the initial capacity
        this.maxCapacity = capacity;

        // The player will gain 250 HP once consumed
        this.healingPower = 250;

        // Initialise the ConsumeAction
        this.consumeAction = new ConsumeAction(this);

        // Add FLASK capability
        this.addCapability(Status.FLASK);

        // Set refilled to false initially
        this.refilled = false;
    }



    /**
     * Inform a carried Item of the passage of time.
     * Since this method is called when Item is carried, ConsumeAction will be added.
     * @param currentLocation - The location of the actor carrying this Item.
     * @param actor - The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // If the capacity is greater than or equal to zero and not refilled
        if (capacity >= 0 && !refilled) {
            // Add the consume action
            addAction(consumeAction);

            // Refilled
            refilled = true;
        }
    }



    /**
     * Called when the player uses the ConsumeAction
     * @param actor Actor to buff health
     */
    @Override
    public void consumedBy(Actor actor, GameMap map) {
        if (capacity > 0) {
            // Remove item from ground, in case Actor consumes this Item while it's on the ground.
            map.locationOf(actor).removeItem(this);

            // Heal the player
            actor.heal(healingPower);

            // Decrement the capacity
            capacity--;
        } else {
            // Remove the ability to consume this
            removeAction(consumeAction);

            // Not refilled
            refilled = false;
        }
    }

    @Override
    public String toString() {
        return super.toString() + " (" + capacity + "/" + maxCapacity + ")";
    }

    @Override
    public void enhance() {
        // Set the max capacity to the initial max plus 2
        int initialCapacity = maxCapacity;
        maxCapacity = initialCapacity + 2;

        // Set the capacity to the max
        capacity = maxCapacity;
    }
}
