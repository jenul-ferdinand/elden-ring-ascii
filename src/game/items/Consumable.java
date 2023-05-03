package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import game.Player;

public interface Consumable {
    void consumedBy(Player player);
}
