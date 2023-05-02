package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

/**
 * BEHOLD, DOG!
 *
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class LoneWolf extends Enemy {


    public LoneWolf() {
        super("Lone Wolf", 'h', 102,97,95, null);

    }



    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "bites", getAttackAccuracy());
    }
}
