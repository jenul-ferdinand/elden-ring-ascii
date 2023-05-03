package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.PurchaseAction;
import game.enemies.LoneWolf;
import game.environments.Dirt;
import game.environments.Floor;
import game.environments.Wall;
import game.items.Club;
import game.items.Rune;
import game.traders.MerchantKale;
import game.utils.FancyMessage;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {

	public static void main(String[] args) {
		// Create a new World (It contains the main game loop)
		World world = new World(new Display());
		// Create the ground factory
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor());
		// Store the map design in variable map
		List<String> map = Arrays.asList(
				"...........................................................................",
				"......................#####....######......................................",
				"......................#..___....____#......................................",
				"..................................__#......................................",
				"......................._____........#......................................",
				"......................#............_#......................................",
				"......................#...........###......................................",
				"...........................................................................",
				"...........................................................................",
				"..................................###___###................................",
				"..................................________#................................",
				"..................................#________................................",
				"..................................#_______#................................",
				"..................................###___###................................",
				"....................................#___#..................................",
				"...........................................................................",
				"...........................................................................",
				"...........................................................................",
				"..####__##....................................................######..##...",
				"..#.....__....................................................#....____....",
				"..#___..........................................................__.....#...",
				"..####__###..................................................._.....__.#...",
				"..............................................................###..__###...",
				"...........................................................................");

		// Create an instance of the game map
		GameMap gameMap = new GameMap(groundFactory, map);
		// Add the new map to the world
		world.addGameMap(gameMap);

		// Display the ELDEN RING title card
//		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
//			new Display().println(line);
//			try {
//				Thread.sleep(200);
//			} catch (Exception exception) {
//				exception.printStackTrace();
//			}
//		}




		// Create a Lone Wolf enemy
		gameMap.at(23, 17).addActor(new LoneWolf());
		// Create Merchant Kale
		gameMap.at(40, 12).addActor(new MerchantKale());

		// Create a Rune for testing
		gameMap.at(38, 15).addItem(new Rune(100));
		gameMap.at(38, 16).addItem(new Rune(100));

		// Create a Player
		Player player = new Player("Tarnished", '@', 300);
		// Add the Player to the World
		world.addPlayer(player, gameMap.at(36, 10));


		// Start the World game loop
		world.run();
	}
}
