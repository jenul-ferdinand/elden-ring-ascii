package game.items;

import edu.monash.fit2099.engine.actors.Actor;

public interface Collectable {
    void collectedBy(Actor collector);
}
