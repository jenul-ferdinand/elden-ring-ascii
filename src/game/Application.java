package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.defaultGrounds.Dirt;
import game.defaultGrounds.Floor;
import game.defaultGrounds.Wall;
import game.environments.Graveyard;
import game.environments.GustOfWind;
import game.environments.PuddleOfWater;
import game.items.*;
import game.traders.FingerReaderEnia;
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
		FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new Graveyard(), new GustOfWind(), new PuddleOfWater());

		// Store the map design in variable map
		List<String> map = Arrays.asList(
				"..nnnn................................................~~~~~~~~~~~~~~~~~~~~~",
				"......................#####....######..................~~~~~~~~~~~~~~~~~~~~",
				"..nnnn................#..___....____#...................~~~~~~~~~~~~~~~~~~~",
				"..................................__#....................~~~~~~~~~~~~~~~~~~",
				"......................._____........#.....................~~~~~~~~~~~~~~~~~",
				"......................#............_#......................~~~~~~~~~~~~~~~~",
				"......................#...........###......................................",
				"...........................................................................",
				"...........................................................................",
				"~~~~~~~~~~~......&&&&&............###___###................................",
				"~~~~~~~~~~~~......................________#....nnnn........................",
				"~~~~~~~~~~~~~.....................#________................................",
				"~~~~~~~~~~~~......................#_______#....nnnn........................",
				"~~~~~~~~~~~.......................###___###................................",
				"~~~~~~~~~~..........................#___#..................................",
				"...........................................................................",
				"...........................................................................",
				"...........................................................................",
				"..####__##...........................................&&&......######..##...",
				"..#.....__...........................................&&&......#....____....",
				"..#___..............&&&..............................&&&........__.....#...",
				"..####__###.........&&&......................................._.....__.#...",
				"....................&&&.......................................###..__###...",
				"...........................................................................");

		// Create an instance of the game map
		GameMap gameMap = new GameMap(groundFactory, map);
		// Add the new map to the world
		world.addGameMap(gameMap);

		// Display the ELDEN RING title card
		for (String line : FancyMessage.ELDEN_RING.split("\n")) {
			new Display().println(line);
			try {
				Thread.sleep(200);
			} catch (Exception exception) {
				exception.printStackTrace();
			}
		}

		// Merchant Kale
		gameMap.at(40, 12).addActor(new MerchantKale());

		// Finger Reader Enia
		gameMap.at(40, 10).addActor(new FingerReaderEnia());

		// Remembrance of the Grafted
		gameMap.at(39, 10).addItem(new RemembranceOfTheGrafted());

		// Rune for testing
		gameMap.at(38, 15).addItem(new Rune(100));
		gameMap.at(38, 16).addItem(new Rune(100));
		gameMap.at(38,17).addItem(new GoldenRune());
		gameMap.at(38, 18).addItem(new GoldenSeed());

		// Weapons for testing
		gameMap.at(36, 11).addItem(new Grossmesser());
		gameMap.at(36, 12).addItem(new Scimitar());

		// Player
		Player player = new Player("Tarnished", '@', 300);
		// Add the Player to the World
		world.addPlayer(player, gameMap.at(38, 9));


		// Start the World game loop
		world.run();
	}
}
