package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enemies.type.Land;
import game.enemies.type.Sea;
import game.enemies.type.Skeletal;
import game.items.Grossmesser;
import game.items.Rune;
import game.utils.RandomNumberGenerator;

public class GiantDog extends Land {
    /**
     * Constructor.
     *
     * @param name           the name of the Actor
     * @param displayChar    the character that will represent the Actor in the display
     * @param hitPoints      the Actor's starting hit points
     * @param attackDamage
     * @param attackAccuracy
     * @param specialSkill
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693 ,314 ,90, "AOE");
        this.addItemToInventory(new Rune(RandomNumberGenerator.getRandomInt(313,1808)));
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "bites", getAttackAccuracy());

    }
}
