package game.environments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorLocationsIterator;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enemies.LoneWolf;
import game.utils.Status;
import game.utils.RandomNumberGenerator;

public class GustOfWind extends Ground {
    int spawnChance = 33;
    public GustOfWind() {
        super('&');
    }

    @Override
    public void tick(Location location){
        if(location.containsAnActor()){
            return;
        } else if (RandomNumberGenerator.getRandomInt(100) < spawnChance) {
            location.addActor(new LoneWolf());
        }
    }

}
