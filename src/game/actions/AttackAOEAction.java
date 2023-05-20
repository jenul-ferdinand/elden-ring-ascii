package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

public class AttackAOEAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * The direction of incoming attack.
     */
    private String direction = "surrounding";

    /**
     * Weapon used for the attack
     */
    private Weapon weapon;

    /**
     * Random number generator
     */
    private Random rand = new Random();



    /**
     * Constructor for AttackAction with target, direction and weapon.
     *
     * @param target    the Actor to attack
     * @param weapon the weapon used for the attack
     */
    public AttackAOEAction(Actor target,Weapon weapon) {
        this.target = target;
        this.weapon = weapon;
    }



    /**
     * Constructor with intrinsic weapon as default
     *
     * @param target the actor to attack
     */
    public AttackAOEAction(Actor target) {
        this.target = target;
    }



    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        if (weapon == null) {
            weapon = actor.getIntrinsicWeapon();
        }
        int damage = weapon.damage();
        for (Exit exit : map.locationOf(actor).getExits()){
            Location toAttack = exit.getDestination();
            if (toAttack.containsAnActor()){
                target = toAttack.getActor();
                if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
                    result += actor + " misses " + target + ".";
                } else{
                    target.hurt(damage);
                    result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
                    if (!target.isConscious()) {
                        result += new DeathAction(actor).execute(target, map) + "\r\n";
                    }
                }
            }
        }
        return result;
    }



    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return"⚔️ " + actor + " attacks " + target + " at " + direction + " with " + (weapon != null ? weapon : "Intrinsic Weapon");
    }



}
