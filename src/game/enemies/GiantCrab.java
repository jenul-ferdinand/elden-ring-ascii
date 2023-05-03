package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enemies.type.Sea;

public class GiantCrab extends Sea {

    /**
     * Constructor
     */
    public GiantCrab() {
        super("Giant Crab", 'c', 407,208,90, "AOE");
    }

    /**
     * The default weapon of the enemy
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "slams", getAttackAccuracy());

    }
}
