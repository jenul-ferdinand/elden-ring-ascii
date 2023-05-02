package game.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviour.Behaviour;
import game.behaviour.WanderBehaviour;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

public class GiantCrab extends Enemy {

    public GiantCrab() {
        super("Giant Crab", 'c', 407,208,90);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "slams", getAttackAccuracy());
    }
}
