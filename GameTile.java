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
 * GameTile class that is in charge of created tiles for 2048.
 */

// You might need to import additional classes here.
// You might not use all of these classes.
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.util.*;

/**
  * A GameTile is a StackPane that groups together the 
  * visual items needed to display a 2048 tile.
  * 
  * We haven't talked about inheritance much yet, so 
  * the "extends" keyword might be a bit mysterious at this
  * point.  We'll help you through it in this assignment
  * and it will start to make much more sense over the next
  * few weeks.
  */
public class GameTile extends StackPane {

  private static final int WIDTH = 100;
  private static final int HEIGHT = 100;
  private static final int GRAY_NUMBER = 8;
  private static final int MAX_TILE = 8192;
  private static final int SIZE = 30;
  private static final int OFF_WHITE = 2;



  //Hashmap that uses tilevalue as the key to access appropriate color
  //Check out the populateColors method that populates the HashMap
  private static HashMap<Integer, Color> colors = 
      new HashMap<Integer, Color>();

  public GameTile() {
    super();

    if (colors == null) {
      populateColors();
    }
  }


  /* TODO: Fill in the constructor */
  public GameTile(int tileValue) {

    //calls the empty constructor
    super();

    if (colors == null) {
      populateColors();
    }


    // First we want to call populateColors to initialize our HashMap
    // for later use.
    populateColors();

    // Create a rectangle shape for the tiles, just like the original
    // 2048 game.
    Rectangle tile = new Rectangle();
    tile.setWidth(WIDTH);
    tile.setHeight(HEIGHT);

    // Set the value to what we want for a blank tile.
    if (tileValue <= 0)
      tile.setFill(colors.get(0));

    // Color tiles larger than 8192 black.
    else if (tileValue > MAX_TILE)
      tile.setFill(Color.BLACK);

    // Color tiles with appropriate value from the HashMap.
    else
      tile.setFill(colors.get(tileValue));


    // Text object to display the value associated with the GameTile.
    Text text = new Text();
    text.setFont(Font.font("Times New Roman", FontWeight.BOLD, SIZE));

    // If the tile is zero or less than,
    // make it appear blank.
    if (tileValue <= 0) {
      text.setFill(colors.get(0));
    }

    // Color the text grey if the value for the tile
    // is less than 8.
    else if (tileValue < GRAY_NUMBER) {
      text.setFill(Color.GRAY);
      text.setText(Integer.toString(tileValue));
    }

    // For values larger than 8192, make the text white
    // so it is better visible against the black tile.
    else if (tileValue > MAX_TILE) {
      text.setFill(Color.WHITE);
      text.setText(Integer.toString(tileValue));
    }

    // Make the text an off whitish color.
    // This one best matches the screenshot in the write up.
    else {
      text.setFill(colors.get(OFF_WHITE));
      text.setText(Integer.toString(tileValue));
    }

    // Add the shapes the StackPane object from the
    // class we are extending.
    this.getChildren().add(tile);
    this.getChildren().add(text);
  }

  /* Name: populateColors() 
   *
   * Purpose: The purpose of this method is to populate the HashMap
   * with RGB values pertaining to certain tileValues. For example,
   * the tileValue 2 has an RGB value of (238, 228, 218). Therefore,
   * if we want to access the color of tileValue 2 from the hashmap,
   * we would say colors.get(2) and it would return the color object
   * Color.rgb(238, 228, 218).
   *
   * You are free to change the RGB values of each tileValue as you wish.
   *
   * Parameters: None
   *
   * Return: None
   */
  public static void populateColors() {
    colors.put(0,Color.rgb(238, 228, 218, 0.35)); //empty tile
    colors.put(2, Color.rgb(238, 228, 218));
    colors.put(4, Color.rgb(237, 224, 200));
    colors.put(8,Color.rgb(242, 177, 121));
    colors.put(16, Color.rgb(245, 149, 99));
    colors.put(32,Color.rgb(246, 124, 95));
    colors.put(64,Color.rgb(246, 94, 59));
    colors.put(128,Color.rgb(237, 207, 114));
    colors.put(256,Color.rgb(237, 204, 97));
    colors.put(512,Color.rgb(237, 200, 80));
    colors.put(1024,Color.rgb(237, 197, 63));
    colors.put(2048,Color.rgb(237, 194, 46));
    colors.put(4096,Color.rgb(237, 194, 46));
    colors.put(8192,Color.rgb(237, 194, 46));
  }
}
