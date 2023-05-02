package game.enemies;

import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.enemies.type.Sea;

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
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(getAttackDamage(), "giant pincer", getAttackAccuracy());

    }
}
