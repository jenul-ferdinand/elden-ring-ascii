package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enemies.type.Sea;

public class GiantCrab extends Sea {

    public GiantCrab() {
        super("Giant Crab", 'c', 407,208,90, "AOE");
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "slams", getAttackAccuracy());

    }
}
