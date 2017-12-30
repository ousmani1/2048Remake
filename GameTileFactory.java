/*
 * Name: Omer Usmani
 * Login: cs8bshg
 * Sources of Help: None
 *
 * Date: May 4th, 2017
 * File: Gui2048.java
 *
 * cse8b Assignment 7
 *
 * This Class will utilize tiles that were created by other students to
 * use in our own game.
 */

import java.util.*;

/** We will use the getTile method here in Gui2048.java */
public class GameTileFactory {
    private Random rand = new Random();
    
    // Modify this according to the number of custom tiles you wish to use
    private final int NUM_CUSTOM_TILES = 2;

    // use getTile method to get a random Tile
    public GameTile getTile(int value) {
        switch (rand.nextInt(NUM_CUSTOM_TILES)) {
            // Uses two students' custom tiles
            case 0: return new GameTileyokina(value);
            case 1: return new GameTiletnn050(value);
            case 2: return new GameTile(value);
            
            // You easily can add more tiles with an additional line of code
            // case 2: return new GameTileStudentThree(value);
            // case 3: return new GameTileStudentFour(value);
            default: return null;
        }
    }
}
