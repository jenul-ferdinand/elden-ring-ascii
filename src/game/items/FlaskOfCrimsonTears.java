package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * Flask of Crimson Tears
 * This item can be consumed by an actor, and it will heal them once consumed. The item has a max capacity of 2 uses.
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
     * How much health we will gain once consumed
     */
    private int healingPower;

    /**
     * This will hold our ConsumeAction
     */
    private Action consumeAction;



    /**
     * Constructor
     */
    public FlaskOfCrimsonTears() {

        // Super class attributes
        super("Flask of Crimson Tears", '+', false);

        // Max capacity of 2 uses
        this.capacity = 2;

        // The player will gain 250 HP once consumed
        this.healingPower = 250;

        // Create and add ConsumeAction
        this.consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);

    }



    /**
     * When consumed the player will gain health
     * @param actor Actor to buff health
     */
    @Override
    public void consumedBy(Actor actor) {

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



}
