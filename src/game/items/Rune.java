package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.utils.Status;


/**
 * This is the item form of the game's currency.
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class Rune extends Item implements Consumable {
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
    public Rune(int value) {

        // Set the default attributes
        super("Rune", '$', false);

        // Set the value
        this.value = value;

        // Initialise the ConsumeAction
        consumeAction = new ConsumeAction(this);
        addAction(consumeAction);

        // Get the singleton instance of the RuneManager
        runeManager = RuneManager.getRuneManager();

    }



    /**
     * Called when the player uses the ConsumeAction.
     * This will increase the Actor's runes by the value
     * @param actor - The Actor class that will collect the item
     */
    @Override
    public void consumedBy(Actor actor, GameMap map) {
        // Remove the gah damn Item from the ground
        map.locationOf(actor).removeItem(this);

        // The runes will be added to the collectors balance
        runeManager.addRunes(actor, value);

        // Remove the action
        this.removeAction(consumeAction);

    }
}
