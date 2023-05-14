package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enemies.type.Land;
import game.enemies.type.Sea;
import game.enemies.type.Skeletal;
import game.items.Grossmesser;
import game.items.Rune;
import game.utils.Abilities;
import game.utils.RandomNumberGenerator;

public class GiantDog extends Land {

    public GiantDog() {
        super("Giant Dog", 'G', 693 ,314 ,90);
        this.addCapability(Abilities.AOE);
        this.addItemToInventory(new Rune(RandomNumberGenerator.getRandomInt(313,1808)));
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
