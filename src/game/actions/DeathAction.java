package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.enemies.PileOfBones;
import game.utils.FancyMessage;
import game.utils.ResetManager;
import game.utils.Status;

/**
 * An action executed if an actor is killed.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Ruilin
 *
 */
public class DeathAction extends Action {
    /**
     * The attacker
     */
    private Actor attacker;



    /**
     * Constructor
     * @param actor attacker
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
        if (target.hasCapability(Status.RESPAWNABLE)){

            Location pileLocation = map.locationOf(target);

            map.removeActor(target);

            map.addActor(new PileOfBones(target), pileLocation);

        } else if (attacker.hasCapability(Status.HOSTILE_TO_ENEMY) && target != attacker){
            // Drop all Items
            for (Item item : target.getItemInventory()){
                dropActions.add(item.getDropAction(target));
            }

            // Drop all WeaponItems
            for (WeaponItem weapon : target.getWeaponInventory()) {
                dropActions.add(weapon.getDropAction(target));
            }

            // Execute the dropping actions for all the Items and WeaponItems
            for (Action drop : dropActions) {
                drop.execute(target, map);
            }
        }


        result += System.lineSeparator() + menuDescription(target);
        if(target.hasCapability(Status.HOSTILE_TO_ENEMY)){
            ResetManager reset = ResetManager.getInstance();
            reset.run(map);
        } else{
            map.removeActor(target);
            return result;
        }
        return null;
    }



    /**
     * The String to be printed for the menu option
     * @param actor The actor performing the action.
     * @return String Menu option string
     */
    @Override
    public String menuDescription(Actor actor) {
        if(actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            // Display the YOU_DIED title card
            for (String line : FancyMessage.YOU_DIED.split("\n")) {
                new Display().println(line);
                try {
                    Thread.sleep(200);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
        return "💀 " + actor + " is killed.";
    }
}
