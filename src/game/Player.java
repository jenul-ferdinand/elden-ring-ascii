package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.Club;
import game.items.FlaskOfCrimsonTears;
import game.items.Rune;
import game.items.RuneManager;
import game.utils.ResetManager;
import game.utils.Resettable;
import game.utils.Status;

/**
 * Class representing the Player. It implements the Resettable interface.
 * It carries around a club to attack a hostile creature in the Lands Between.
 * Created by: Adrian Kristanto
 * @author Adrian Kristanto
 * Modified by: Jenul Ferdinand
 *
 */
public class Player extends Actor implements Resettable {
	/**
	 * Create and store an instance of the menu
	 */
	private final Menu menu = new Menu();

	/**
	 * To store the singleton instance of the RuneManager
	 */
	private final RuneManager runeManager;

	/**
	 * This will store the player's last location
	 */
	private Location lastLocation;



	/**
	 * Constructor
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		// Super class attributes
		super(name, displayChar, hitPoints);

		// Hostile to enemies
		ResetManager R = ResetManager.getInstance();
		R.registerResettable(this);

		// Capability that the player is hostile to enemies
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		// Can trade with traders
		this.addCapability(Status.TRADE_CAPABLE);

		// Add the Club to the weapon inventory
		this.addWeaponToInventory(new Club());

		// Add the Flask of Crimson Tears to the inventory
		this.addItemToInventory(new FlaskOfCrimsonTears());

		// Initialise the last location as null
		this.lastLocation = null;

		// RuneManager initialisation
		this.runeManager = RuneManager.getRuneManager();
		// Set the initial runes to zero
		runeManager.setRunes(this, 0 );
	}



	/**
	 * Handles the console each turn
	 * @param actions    collection of possible Actions for this Actor
	 * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
	 * @param map        the map containing the Actor
	 * @param display    the I/O object to which messages may be written
	 * @return Action - Returns the action the player has chosen from the menu
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// If last location is not the same as the current location
		if (lastLocation != map.locationOf(this)) {
			// Set the last location to the current location
			this.lastLocation = map.locationOf(this);
		}

		// Colours as variables
		final String ANSI_RESET = "\u001B[0m";
		final String ANSI_RED = "\u001B[31m";
		final String ANSI_YELLOW = "\u001B[33m";

		// Print the HP in red
		display.println(ANSI_RED + "HP: " + printHp() + ANSI_RESET);

		// Print the Runes balance in green
		display.println(ANSI_YELLOW + "Runes: " + RuneManager.getRuneManager().getRunes(this) + ANSI_RESET);

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// return/print the console menu
		return menu.showMenu(this, actions, display);
	}



	/**
	 * Game reset handling
	 */
	@Override
	public void reset(GameMap map) {
		// Heal the player to max health
		this.heal(99999);

		// If the player's runes is greater than 0
		if (runeManager.getRunes(this) > 0) {
			// Drop a Rune with value of the player's rune balance at the last location
			map.at(lastLocation.x(), lastLocation.y()).addItem(new Rune(runeManager.getRunes(this)));
		}

		// Remove the runes from the player
		runeManager.setRunes(this, 0);

		// Move the player back to the starting location
		map.moveActor(this, map.at(38, 9));
	}



	/**
	 * The default weapon
	 * @return IntrinsicWeapon The player's fists
	 */
	@Override
	public IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(11, "punches");
	}



}
