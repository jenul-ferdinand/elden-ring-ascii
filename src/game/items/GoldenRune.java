package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.utils.RandomNumberGenerator;
import game.utils.Status;


/**
 * This is a golden rune, it is found scattered across the map.
 * The value of the golden rune is randomly generated.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class GoldenRune extends Item implements Consumable {
    /**
     * The value of the rune
     */
    private final int value;

    /**
     * This will store out ConsumeAction
     */
    private final Action consumeAction;

    /**
     * The singleton RuneManager
     */
    private final RuneManager runeManager;



    /**
     * Constructor.
     *
     */
    public GoldenRune() {

        // Set the default attributes
        super("Golden Rune", '*', true);

        // Set the random value
        this.value = RandomNumberGenerator.getRandomInt(200, 10000);

        // Initialise the ConsumeAction
        consumeAction = new ConsumeAction(this);

        // Get the singleton instance of the RuneManager
        runeManager = RuneManager.getRuneManager();

    }



    /**
     * Inform a carried Item of the passage of time.
     * Since this method is called when Item is carried, ConsumeAction will be added.
     * @param currentLocation - The location of the actor carrying this Item.
     * @param actor - The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // INSIDE INVENTORY
        // If not in inventory
        if (!hasCapability(Status.IN_INVENTORY)) {

            // Add the ConsumeAction
            this.addAction(consumeAction);

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
     * Called when the player uses the ConsumeAction.
     * This will increase the Actor's runes by the value
     * @param actor - The Actor class that will collect the item
     */
    @Override
    public void consumedBy(Actor actor, GameMap map) {
        // The runes will be added to the collectors balance
        runeManager.addRunes(actor, value);

        // Remove the action
        this.removeAction(consumeAction);

        // Drop the item
        this.getDropAction(actor);

        // Make non portable
        this.togglePortability();

        // Remove the item from the ground
        map.locationOf(actor).removeItem(this);
    }



}
