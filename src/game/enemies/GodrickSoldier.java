package game.enemies;

import game.enemies.type.Stormveil;
import game.items.Grossmesser;
import game.items.HeavyCrossbow;

public class GodrickSoldier extends Stormveil {
    /**
     * Constructor.
     *
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198,0,0);
        this.addWeaponToInventory(new HeavyCrossbow());
    }
}
