package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import game.Player;
import game.actions.ConsumeAction;

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
     * @param player The player
     */
    @Override
    public void consumedBy(Player player) {

        // Heal the player
        player.heal(healingPower);

        // Decrement the capacity
        capacity--;

        // If we have used up the capacity of the item
        if (capacity <= 0) {
            // Remove the ability to consume this
            removeAction(consumeAction);

            // Make portable so we can drop
            togglePortability();

            // Drop this item
            getDropAction(player);

            // Make it non-portable again, so we can't pick it up
            togglePortability();
        }

    }



}
