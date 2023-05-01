package game.enemies;

import edu.monash.fit2099.engine.actors.Actor;

abstract class Enemy extends Actor {
    private int attackDamage;
    private int attackAccuracy;
    private String uniqueAbility;


    /**
     * Constructor.
     *
     * @param name            the name of the Actor
     * @param displayChar     the character that will represent the Actor in the display
     * @param hitPoints       the Actor's starting hit points
     * @param attackDamage
     * @param attackAccuracy
     * @param uniqueAbility
     */
    public Enemy(String name, char displayChar, int hitPoints, int attackDamage, int attackAccuracy, String uniqueAbility) {
        super(name, displayChar, hitPoints);
        this.attackDamage = attackDamage;
        this.attackAccuracy = attackAccuracy;
        this.uniqueAbility = uniqueAbility;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public int getAttackAccuracy() {
        return attackAccuracy;
    }

    public void setAttackAccuracy(int attackAccuracy) {
        this.attackAccuracy = attackAccuracy;
    }

    public String getUniqueAbility() {
        return uniqueAbility;
    }

    public void setUniqueAbility(String uniqueAbility) {
        this.uniqueAbility = uniqueAbility;
    }
}
