package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.Item;
import game.Player;
import game.actions.ConsumeAction;

public class Rune extends Item implements Consumable {
    /**
     * The value of the rune
     */
    private int value;

    /**
     * This will store out ConsumeAction
     */
    private Action consumeAction;



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
        consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
    }



    /**
     * When called the Rune value will be added to the player's runes balance
     * @param player The player
     */
    @Override
    public void consumedBy(Player player) {

        // Set the runes value for the player
        player.addRunes(value);

        // Remove the ConsumeAction
        this.removeAction(consumeAction);

        // Drop the item
        this.getDropAction(player);

        // Make it not portable (delete)
        this.togglePortability();

    }
}
