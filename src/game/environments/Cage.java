package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.Dog;
import game.enemies.HeavySkeletalSwordsman;
import game.enemies.SkeletalBandit;
import game.utils.RandomNumberGenerator;

public class Cage extends Ground {

    private int spawnChance = 37;
    /**
     * Constructor.
     *
     */
    public Cage() {
        super('<');
    }
    @Override
    public void tick(Location location){
        //spawnChance = 0;
        if (!location.containsAnActor() && RandomNumberGenerator.getRandomInt(100) < spawnChance) {
            location.addActor(new Dog());
        }
    }
}
