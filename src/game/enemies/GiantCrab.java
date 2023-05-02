package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;

public class GiantCrab extends Enemy {

    public GiantCrab() {
        super("Giant Crab", 'c', 407,208,90, "AOE");
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "slams", getAttackAccuracy());

    }
}
