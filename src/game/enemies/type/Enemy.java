package game.enemies.type;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.actions.DespawnAction;
import game.behaviour.*;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;
/**
 * A class extend from actor
 * The Enemy can follow the player if it is within range, and  attack other actors
 * that have the HOSTILE_TO_ENEMY.
 * Created by:Ruilin
 * Modified by: Ruilin
 */
public abstract class Enemy extends Actor implements Resettable {
    private int attackDamage;
    private int attackAccuracy;

    protected Map<Integer, Behaviour> behaviours;

    /**
     * Constructor.
     *
     * @param name           the name of the Actor
     * @param displayChar    the character that will represent the Actor in the display
     * @param hitPoints      the Actor's starting hit points
     * @param attackDamage
     * @param attackAccuracy
     */
    public Enemy(String name, char displayChar, int hitPoints, int attackDamage, int attackAccuracy) {
        super(name, displayChar, hitPoints);
        ResetManager R = ResetManager.getInstance();
        R.registerResettable(this);
        this.attackDamage = attackDamage;
        this.attackAccuracy = attackAccuracy;
        this.behaviours = new HashMap<>();
        this.behaviours.put(995, new DespawnBehaviour());
        this.behaviours.put(996, new AttackAOEBehaviour());
        this.behaviours.put(997, new AttackBehaviour());
        this.behaviours.put(999, new WanderBehaviour());
    }

    /**
     @return the behaviours of Enemy
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     Sets behaviours of Enemy.
     @param behaviours the behaviours of Enemy
     */
    public void setBehaviours(Map<Integer, Behaviour> behaviours) {
        this.behaviours = behaviours;
    }

    /**
     @return the amount of damage this Enemy's attack deals
     */
    public int getAttackDamage() {
        return attackDamage;
    }

    /**
     Sets attack damage of Enemy.
     @param attackDamage attack damage of Enemy
     */
    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    /**
     @return the accuracy of Enemy's attack
     */
    public int getAttackAccuracy() {
        return attackAccuracy;
    }

    /**
     Sets accuracy of Enemy's attack.
     @param attackAccuracy the accuracy of Enemy's attack
     */
    public void setAttackAccuracy(int attackAccuracy) {
        this.attackAccuracy = attackAccuracy;
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        checkPlayerInRangeToFollow(map);

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * The lone wolf can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for(Weapon weapon : otherActor.getWeaponInventory()){
                if(weapon.getSkill(otherActor) != null){
                    actions.add(weapon.getSkill(otherActor));
                }
                if(weapon.getSkill(otherActor, direction) != null){
                    actions.add(weapon.getSkill(otherActor,direction));
                }
                actions.add(new AttackAction(this,direction,weapon));
            }

            actions.add(new AttackAction(this, direction));
            // HINT 1: The AttackAction above allows you to attak the enemy with your intrinsic weapon.
            // HINT 1: How would you attack the enemy with a weapon?
        }
        return actions;
    }


    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "IntrinsicAttack", getAttackAccuracy());
    }

    public void checkPlayerInRangeToFollow(GameMap map){
        for (Exit exit : map.locationOf(this).getExits()) {
            for (Exit exit1 : exit.getDestination().getExits()){
                if(exit1.getDestination().getActor() != null){
                    if(exit1.getDestination().getActor().hasCapability(Status.HOSTILE_TO_ENEMY)){
                        System.out.println("following player");
                        this.behaviours.remove(996);
                        this.behaviours.put(998, new FollowBehaviour(exit1.getDestination().getActor()));
                    }

                }
            }
        }
    }



    /**
     * Despawn the enemy when the player dies
     * @param map - The GameMap
     */
    @Override
    public void reset(GameMap map) {
        Action a = new DespawnAction(this);
        a.execute(this, map);
    }
}
