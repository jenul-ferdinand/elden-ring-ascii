package game.enemies;

import game.enemies.type.Skeletal;
import game.items.Rune;
import game.items.Scimitar;
import game.utils.RandomNumberGenerator;

public class SkeletalBandit extends Skeletal {
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 153,0,0);

        // Add the Scimitar to inventory
        this.addWeaponToInventory(new Scimitar());

        // Add Rune to inventory
        this.addItemToInventory(new Rune(RandomNumberGenerator.getRandomInt(35,892)));
    }
}
