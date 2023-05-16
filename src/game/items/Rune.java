package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.RuneCollectAction;

public class Rune extends Item implements Collectable {
    /**
     * The value of the rune
     */
    private int value;

    /**
     * This will store out ConsumeAction
     */
    private Action collectAction;

    /**
     * The singleton RuneManager
     */
    private RuneManager runeManager;


    /**
     * Constructor.
     *
     */
    public Rune(int value) {

        // Set the default attributes
        super("Rune", '$', true);

        // Set the value
        this.value = value;

        // Add the ConsumeAction
        collectAction = new RuneCollectAction(this);
        this.addAction(collectAction);

        // Get the singleton instance of the RuneManager
        runeManager = RuneManager.getRuneManager();
    }



    /**
     * When called the Rune value will be added to the player's runes balance
     * @param collector The Actor class that will collect the item
     */
    @Override
    public void collectedBy(Actor collector) {
        // The runes will be added to the collectors balance
        runeManager.addRunes(collector, value);

        // Remove the action
        this.removeAction(collectAction);


    }
}
