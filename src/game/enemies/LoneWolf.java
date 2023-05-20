package game.enemies;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.enemies.type.Land;
import game.items.Rune;
import game.utils.Status;

/**
 * BEHOLD, DOG!
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class LoneWolf extends Land {
    /**
     * Constructor
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102,97,95);
    }

    /**
     * The default weapon of the enemy
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "bites", getAttackAccuracy());
    }
}
