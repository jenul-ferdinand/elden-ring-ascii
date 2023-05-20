package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enemies.type.Sea;
import game.items.Rune;
import game.utils.Abilities;
import game.utils.RandomNumberGenerator;

public class GiantCrayfish extends Sea {

    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803,527,100);
        this.addCapability(Abilities.AOE);

        // Add rune to inventory with random value
        this.addItemToInventory(new Rune(RandomNumberGenerator.getRandomInt(500,2374)));
    }

    /**
     * The default weapon of the enemy
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "giant pincer", getAttackAccuracy());

    }
}
