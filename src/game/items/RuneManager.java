package game.items;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.HashMap;

/**
 * Manages the Runes System
 * Created by: Jenul Ferdinand
 * @author Jenul Ferdinand
 * Modified by: Jenul Ferdinand
 */
public class RuneManager {



    /**
     * Stores the data for Actor as the key and Integer as the value
     */
    private HashMap<Actor, Integer> runeCollectors;

    /**
     * Holds the singleton instance of this class
     */
    private static RuneManager instance;



    /**
     * Private constructor
     */
    private RuneManager() {
        this.runeCollectors = new HashMap<>();
    }

    static {
        instance = new RuneManager();
    }



    /**
     * Get the singleton instance
     * @return RuneManager Returns the singleton instance of this class
     */
    public static RuneManager getRuneManager() {
        return instance;
    }



    /**
     * Sets the runes to a specific value
     * @param actor The actor
     * @param amount The amount
     */
    public void setRunes(Actor actor, int amount) {
        runeCollectors.put(actor, amount);
    }



    /**
     * Gets the amount of runes for the specific actor
     * @param actor The actor
     * @return int The amount
     */
    public int getRunes(Actor actor) {
        return runeCollectors.get(actor);
    }



    /**
     * Check's whether the actor exists as a key
     * @param actor The actor
     * @return Boolean True or False whether the key exists
     */
    public boolean checkCollector(Actor actor) {
        return runeCollectors.containsKey(actor);
    }



    /**
     * Subtracts the runes
     * @param actor The actor
     * @param amount The amount
     */
    public void subtractRunes(Actor actor, int amount) {
        // Get the current value
        int currentValue = runeCollectors.get(actor);

        // Subtract the current value by amount
        int newValue = currentValue - amount;

        // Set the new value
        setRunes(actor, newValue);
    }



    /**
     * Adds the runes
     * @param actor The actor
     * @param amount The amount
     */
    public void addRunes(Actor actor, int amount) {
        // Get the current value
        int currentValue = runeCollectors.get(actor);

        // Add the current value by amount
        int newValue = currentValue + amount;

        // Set the new value
        setRunes(actor, newValue);
    }



}
