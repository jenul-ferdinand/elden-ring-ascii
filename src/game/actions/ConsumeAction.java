package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;
import game.items.Consumable;

public class ConsumeAction extends Action {
    private Consumable consumable;

    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Consume the item and pass the actor
        consumable.consumedBy((Player) actor);

        // Remove the item from the location of the ground
        map.locationOf(actor).removeItem((Item) consumable);

        // Return the confirmation message
        return actor + " consumes " + consumable;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Consume " + consumable;
    }
}
