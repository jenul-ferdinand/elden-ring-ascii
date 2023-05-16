package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enemies.type.Sea;
import game.items.Rune;
import game.utils.RandomNumberGenerator;

public class GiantCrayfish extends Sea {
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
    public GiantCrayfish() {
        super("Giant Crayfish", 'R', 4803,527,100, "AOE");

        // Add Rune to inventory
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
