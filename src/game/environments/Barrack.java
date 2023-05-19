package game.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GodrickSoldier;
import game.utils.RandomNumberGenerator;

public class Barrack extends Ground {
    private int spawnChance = 45;
    /**
     * Constructor.
     *
     */
    public Barrack() {
        super('B');
    }

    @Override
    public void tick(Location location){
        //spawnChance = 0;
        if (!location.containsAnActor() && RandomNumberGenerator.getRandomInt(100) < spawnChance) {
            location.addActor(new GodrickSoldier());
        }
    }
}
