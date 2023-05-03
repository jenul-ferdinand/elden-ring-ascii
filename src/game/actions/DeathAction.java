package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Player;
import game.enemies.HeavySkeletalSwordsman;
import game.enemies.PileOfBones;
import game.enemies.type.Skeletal;
import game.items.Rune;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class DeathAction extends Action {
    /**
     * The attacker
     */
    private Actor attacker;



    /**
     * Constructor
     * @param actor
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
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

        ActionList dropActions = new ActionList();
        if(target instanceof Skeletal && !(target instanceof PileOfBones)){
            Location pileLocation = map.locationOf(target);
            map.removeActor(target);
            map.addActor(new PileOfBones((Skeletal) target), pileLocation);
        }else{
            // drop all items
            for (Item item : target.getItemInventory())
                if(!(item instanceof Rune)){
                    dropActions.add(item.getDropAction(target));
                } else if(attacker instanceof Player){
                    dropActions.add(item.getDropAction(target));
                }

            for (WeaponItem weapon : target.getWeaponInventory())
                dropActions.add(weapon.getDropAction(target));
            for (Action drop : dropActions)
                drop.execute(target, map);
            // remove actor
            map.removeActor(target);
        }

        result += System.lineSeparator() + menuDescription(target);
        return result;
    }



    /**
     * The String to be printed for the menu option
     * @param actor The actor performing the action.
     * @return String Menu option string
     */
    @Override
    public String menuDescription(Actor actor) {
        return "💀 " + actor + " is killed.";
    }
}
