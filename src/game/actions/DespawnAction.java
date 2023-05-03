package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public class DespawnAction extends Action {
    private Actor DespawnActor;

    public DespawnAction(Actor actor) {
        this.DespawnActor = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";

        // remove actor
        map.removeActor(target);
        result += menuDescription(target);
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "💤 " + actor + " is despawned.";
    }
}
