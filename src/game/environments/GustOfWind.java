package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.GiantDog;
import game.enemies.HeavySkeletalSwordsman;
import game.enemies.LoneWolf;
import game.utils.Status;
import game.utils.RandomNumberGenerator;

public class GustOfWind extends Ground {
    int spawnChanceWolf = 33;
    int spawnChanceDog = 4;
    public GustOfWind() {
        super('&');
    }

    @Override
    public void tick(Location location){
        //spawnChance = 0;
        if(location.containsAnActor()){
            return;
        } else if (RandomNumberGenerator.getRandomInt(100) < spawnChanceWolf &&
                (location.x() < (location.map().getXRange().max() / 2))) {
                location.addActor(new LoneWolf());
        } else if (RandomNumberGenerator.getRandomInt(100) < spawnChanceDog &&
                !(location.x() < (location.map().getXRange().max() / 2))) {
            location.addActor(new GiantDog());
        }
    }

}
