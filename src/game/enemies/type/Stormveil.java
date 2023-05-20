package game.enemies.type;

public abstract class Stormveil extends Enemy{
    /**
     * Constructor.
     *
     * @param name           the name of the Actor
     * @param displayChar    the character that will represent the Actor in the display
     * @param hitPoints      the Actor's starting hit points
     * @param attackDamage
     * @param attackAccuracy
     */
    public Stormveil(String name, char displayChar, int hitPoints, int attackDamage, int attackAccuracy) {
        super(name, displayChar, hitPoints, attackDamage, attackAccuracy);
    }
}
