package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.Player;
import game.actions.ConsumeAction;

public class Rune extends Item implements Consumable {
    private int value;
    private Action consumeAction;

    /**
     * Constructor.
     *
     */
    public Rune(int value) {
        super("Rune", '$', true);

        this.value = value;

        // Add the ConsumeAction
        consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
    }

    @Override
    public void consumedBy(Player player) {
        // Set the runes value for the player
        player.setRunes(value);
        // DEBUG
        System.out.println("PLAYERS RUNES: " + player.getRunes());

        // Remove the ConsumeAction
        this.removeAction(consumeAction);

        // Drop the item and make it not portable
        this.getPickUpAction(player);
        this.togglePortability();
    }
}
