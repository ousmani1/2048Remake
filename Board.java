/*
 * This Class is used to construct a Board object to be used
 * for the simulation of the game 2048. The class also allows
 * for the board to be rotated 90 degrees to the right or left.
 * Used by the class Gui2048 to make the game function.
 */

import java.util.Random;

public class Board {
    /**
     * Number of tiles showing when the game starts
     */
    public final int NUM_START_TILES = 2;

    /**
     * The probability (times 100) that a randomly
     * generated tile will be a 2 (vs a 4)
     */
    public final int TWO_PROBABILITY = 90;

    /**
     * The size of the grid
     */
    public final int GRID_SIZE;

    private int[][] grid;  // The grid of tile values
    private int score;     // The current score

    // You do not have to use these variables
    private final Random random;    // A random number generator (for testing)

    /**
     * Name: Board(Random random, int boardSize).
     * 
     * Purpose: The purpose of this method is to create or construct a fresh
     * board for the user with two random tiles places within the board. This
     * board will have a particular boardSize that the user sets, as well as a
     * random
     *
     * @param boardSize size of the 2048 game board to be used.
     * @param random    Random random represents the random number which 
     *                  be used to specific where (after every move) a 
     *                  new tile should be added to the board when playing.
     */
    public Board(Random random, int boardSize) {
        if (boardSize < 2) boardSize = 4;

        // initialize member variables
        this.random = random;
        this.GRID_SIZE = boardSize;
        this.grid = new int[boardSize][boardSize];
        this.score = 0;

        // loop through and add two initial tiles to the board randomly
        for (int index = 0; index < NUM_START_TILES; index++) {
            addRandomTile();
        }
    }


    /**
     * Constructor used to load boards for grading/testing
     * 
     * THIS IS USED FOR GRADING - DO NOT CHANGE IT
     *
     * @param random
     * @param inputBoard
     */
    public Board(Random random, int[][] inputBoard) {
        this.random = random;
        this.GRID_SIZE = inputBoard.length;
        this.grid = new int[GRID_SIZE][GRID_SIZE];
        for (int r = 0; r < GRID_SIZE; r++) {
            for (int c = 0; c < GRID_SIZE; c++) {
                this.grid[r][c] = inputBoard[r][c];
            }
        }
    }

    /**
     * return the tile value in a particular cell in the grid.
     *
     * @param row The row
     * @param col The column
     * @return The value of the tile at (row, col)
     */
    public int getTileValue(int row, int col) {
        return grid[row][col];
    }

    /**
     * Get the current score
     *
     * @return the current score of the game
     */
    public int getScore() {
        return score;
    }

    /**
     * Name: addRandomTile()
     * 
     * Purpose: The purpose of this method is to add a random tile of either
     * value 2 or 4 to a random empty space on the 2048
     * board. The place where this tile is added is dependent on the random
     * value associated with each board object. If no tiles are empty, it
     * returns without changing the board.
     */
    public void addRandomTile() {
        int count = 0;
        // loop through grid keeping count of every empty space on board
        for (int rowI = 0; rowI < grid.length; rowI++) {
            for (int colI = 0; colI < grid[rowI].length; colI++) {
                if (grid[rowI][colI] == 0) {
                    count++;
                }
            }
        }

        // if count is still 0 after loop, no empty spaces, return
        if (count == 0) {
            System.out.println("There are no empty spaces!");
            return;
        }

        // keep track of where on board random tile should be placed
        int location = random.nextInt(count);
        int value = random.nextInt(100);

        // reset count
        count = 0;
        // loop through grid checking where grid is 0 & incrementing count
        for (int rowI = 0; rowI < grid.length; rowI++) {
            for (int colI = 0; colI < grid[rowI].length; colI++) {
                if (grid[rowI][colI] == 0) {
                    // if count equals random location generated, place tile
                    if (count == location) {
                        System.out.println("Adding a tile to location " + rowI + ", " + colI);
                        if (value < TWO_PROBABILITY) {
                            grid[rowI][colI] = 2;
                        } else {
                            grid[rowI][colI] = 4;
                        }
                    }
                    count++;
                }
            }
        }
    }

    /**
     * Name: isGameOver()
     * <p>
     * Purpose: The purpose of this method is to check whether or not the game
     * in play is over. The game is officially over once there are no longer any
     * valid moves that can be made in any direction. If the game is over, this
     * method will return true and print the words: "Game Over!" This method
     * will be checked before any movement is ever made.
     *
     * @return true if the game is over, and false if the game isn't over
     */
    public boolean isGameOver() {
        return (!canMoveLeft() && !canMoveRight() && !canMoveUp()
                && !canMoveDown());
    }


    /**
     * Name: canMove(Direction direction)
     * 
     * Purpose: The purpose of this method is to check to see if the movement of
     * the tiles in any direction can actually take place. It does not move the
     * tiles, but at every index of the grid, checks to see if there is a tile
     * above, below, to the left or right that has the same value. If this is
     * the case, then that tile can be moved. It also checks if there is an
     * empty (0) tile at a specified index, as this also indicates that movement
     * can be possible. This method is called within move() so that that method
     * can determine whether or not tiles should be moved.
     *
     * @param direction direction the tiles will move (if possible)
     * @return true if the movement can be done and false if it cannot
     */
    public boolean canMove(Direction direction) {
        // utilize helper methods to check if movement in a particular
        // direction is possible
     	if(direction == null) return false; 

        switch (direction) {
            case UP:
                return canMoveUp();
            case RIGHT:
                return canMoveRight();
            case DOWN:
                return canMoveDown();
            case LEFT:
                return canMoveLeft();
            default:
                // If we got here, something went wrong, so return false
                return false;
        }
    }

    /**
     * Name: move(Direction direction)
     * 
     * Purpose: The purpose of this method is to move the tiles in the game
     * board by a specified direction passed in as a parameter. If the movement
     * cannot be done, the method returns false. If the movement can be done, it
     * moves the tiles and returns true. This method relies on the help of four
     * other helper methods to perform the game play.
     *
     * @param direction direction the tiles will move (if possible)
     * @return true if the movement can be done and false if it cannot
     */
    public boolean move(Direction direction) {
        // if canMove is false, exit and don't move tiles
        if (!canMove(direction)) return false;

        // move in relationship to the direction passed in
        switch (direction) {
            case UP:
                moveUp();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN:
                moveDown();
                break;
            case LEFT:
                moveLeft();
                break;
            default:
                // This should never happen
                return false;
        }

        return true;
    }

    /**
     * This method moves all elements in the grid to
     * the right by calling the rotate() method. Here,
     * we only need to rotate counter clockwise to acheive
     * the correct movement.
     */
    private void moveRight() {
        // Start by moving counter clockwise.
        rotate(false);

        // Follow the same logic as moving up.
        // We will also combine tiles here if needed.
        rotate(false);
        rotate(false);
        moveDown();
        rotate(false);
        rotate(false);

        // Rotate counter clockwise three times to
        // bring elements to the right.
        rotate(false);
        rotate(false);
        rotate(false);
    }

    /**
     * This method moves all elements in the grid to the
     * left by calling the rotate() method and rotating
     * both clockwise and counter clockwise.
     *
     */
    private void moveLeft() {
        // Start by rotating board clockwise
        rotate(true);

        // Implement the same logic as moving up.
        // This is where tiles will be combined if
        // appropriate.
        rotate(false);
        rotate(false);
        moveDown();
        rotate(false);
        rotate(false);

        // Rotate clockwise three times to
        // bring elements to the left.
        rotate(true);
        rotate(true);
        rotate(true);
    }

    /**
     * This method was provided to us for this assignment.
     * Did not make any changes. Will basically move all elements
     * in the board downwards, while combining individual tiles
     * when appropriate.
     */
    private void moveDown() {
        //Implementation by our very own tutor: Junxi Li
        int tmp, i, j, k;
        for(i = 0; i < grid.length; i++){
            k = grid[i].length-1;
            for(j = grid[i].length-1; j >= 0; j--){
                if(grid[j][i] != 0){
                    tmp = grid[j][i];
                    grid[j][i] = 0;
                    grid[k][i] = tmp;
                    k--;
                }
            }
            k = grid[i].length-1;
            for(j = grid[i].length-1; j >= 1; j--, k--){
                if(grid[j][i] == grid[j-1][i]){
                    grid[k][i] = grid[j-1][i] * 2;
                    score += grid[j-1][i] * 2;
                    j--;
                }else{
                    grid[k][i] = grid[j][i];
                }
            }
            if(j == 0){
                grid[k][i] = grid[j][i];
                k--;
            }
            for(j = k; j >= 0; j--){
                grid[j][i] = 0;
            }
        }
    }

    /**
     * This method will move the board up, by combining
     * various counther clockwise rotations with the moveDown() method.
     */
    private void moveUp() {

        // Rotate the board counter clockwise twice.
        rotate(false);
        rotate(false);

        // Move all elements down to combine when needed.
        moveDown();

        // Rotate counter clockwise twice again to
        // bring the elements to the top.
        rotate(false);
        rotate(false);
    }

    /**
     * This method will rotate our board clockwise
     * or counterclockwise.
     *
     * @param clockwise boolean value that will determine
     *                  whether to rotate clockwise or
     *                  counter clockwise.
     */
    public void rotate(boolean clockwise) {
        if (clockwise)
            rotateClockwise(grid);
        else
            rotateCounterClockwise(grid);
    }


    /**
     * Loops through the game board from right to left and checks
     * if it is possible to move right.
     *
     * @return true if possible, false if not.
     */
    private boolean canMoveLeft() {
        // Loop through the array grid from right to left. Stop before the
        // first column because we are moving left, there will be no need to
        // check for a column behind it.
        for (int row = 0; row < grid.length; row++) {
            for (int col = grid.length-1; col > 0; col--) {

                if (grid[row][col] != 0 &&
                        (grid[row][col] == grid[row][col-1] ||
                        grid[row][col-1] == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Loops through the game board from left to right and checks
     * if it is possible to move right.
     *
     * @return true if possible, false if not.
     */
    private boolean canMoveRight() {
        // Loop through the array grid from left to right. Stop before the
        // last column because we are moving right, there will be no need to
        // check for a column ahead of it.
        for(int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length-1; col++) {

                // Check if the current element is not 0. This will
                // prevent us from moving into an empty space. Then,
                // check if the element one column ahead is the same
                // value or equal to zero.
                if(grid[row][col] != 0 &&
                        (grid[row][col] == grid[row][col+1] ||
                        grid[row][col+1] == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Loops through the game board backwards and checks if it
     * is possible to move up.
     *
     * @return true if possible, false if not.
     */
    private boolean canMoveUp() {
        // Loop through the array grid backwards. Stop before the first row
        // because we are moving up, there will be no need to check
        // for the row above it.
        for (int row = grid.length-1; row > 0; row--) {
            for (int col = grid[row].length-1; col >= 0; col--) {

                // Check if the current element is not 0. This will
                // prevent us from moving into an empty space. Then,
                // check if the element one row above is the same
                // value or equal to zero.
                if (grid[row][col] != 0 &&
                        (grid[row][col] == grid[row-1][col] ||
                                grid[row-1][col] == 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Loop through the game board and check if it
     * is possible to move down.
     *
     * @return true if it is possible, false if not.
     */
    private boolean canMoveDown() {
        // Loop through the array grid. But stop before the last row
        // because we are moving down, there will be no need to check
        // for the row below it. It might even result in an exception
        // since there is no row after the last row.
        for (int row = 0; row < grid.length - 1; row++) {
            for (int col = 0; col < grid[row].length; col++) {

                // First we have to check if the current element is not 0.
                // The reason for this is because we do not want to enable
                // the movement of an empty space into another empty space.
                // Then, we have to check if the element one row below is
                // the same value or zero to allow a move downward.
                if (grid[row][col] != 0 &&
                        (grid[row][col] == grid[row+1][col] ||
                        grid[row+1][col] == 0)) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder outputString = new StringBuilder();
        outputString.append(String.format("Score: %d\n", score));
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int column = 0; column < GRID_SIZE; column++)
                outputString.append(grid[row][column] == 0 ? "    -"
                        : String.format("%5d", grid[row][column]));

            outputString.append("\n");
        }
        return outputString.toString();
    }

    /**
     * Set the grid to a new value.  This method can be used for
     * testing and is used by our testing/grading script.
     * @param newGrid The values to set the grid to
     */
    public void setGrid(int[][] newGrid)
    {
        if (newGrid.length != GRID_SIZE ||
                newGrid[0].length != GRID_SIZE) {
            System.out.println("Attempt to set grid to incorrect size");
            return;
                }
        this.grid = new int[GRID_SIZE][GRID_SIZE];
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++) {
                grid[r][c] = newGrid[r][c];
            }
        }
    }

    /**
     * get a copy of the grid
     * @return A copy of the grid
     */
    public int[][] getGrid()
    {
        int[][] gridCopy = new int[GRID_SIZE][GRID_SIZE];
        for (int r = 0; r < grid.length; r++)
        {
            for (int c = 0; c < grid[r].length; c++) {
                gridCopy[r][c] = grid[r][c];
            }
        }
        return gridCopy;
    }

    /**
     * Helper method that will be used in the rotateClockwise
     * and rotateCounterClockwise methods. We want to switch
     * each element at a specific row and column around to
     * the element at the column and row instead.
     *
     * @param board Our game board that will be transposed.
     */
    private static void transpose(int[][] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = row; col < board[0].length; col++) {

                // Same idea as swapping elements in a
                // single dimensional array, but we are
                // swapping the elements by switching the rows
                // and columns here.
                int temp = board[row][col];
                board[row][col] = board[col][row];
                board[col][row] = temp;
            }
        }
    }

    /**
     * Helper method that will be used int the rotateClockwise
     * and rotateCounterClockwise methods. We want to swap
     * a single row in a two dimensional array with another row.
     *
     * @param board Our game board that will have its rows swaped.
     */
    private static void swapRows(int[][] board) {

        // We are looping through two variables here.
        // 'row' starts at the beggining row of the
        // two dimensional array, while 'rowBack' starts
        // at the last row. We will increment and decrement
        // them respectivly.
        for (int  row = 0, rowBack = board.length - 1;
             row < rowBack; row++, rowBack--) {

            // We want to switch the leftmost row
            // with the rightmost row.
            int[] temp = board[row];
            board[row] = board[rowBack];
            board[rowBack] = temp;
        }
    }

    /**
     * Helper method that will be used in the rotate() method.
     * Will rotate our two dimensional array counter
     * clockwise.
     *
     * @param board Our game board that will be rotated.
     */
    private static void rotateCounterClockwise(int[][] board) {
        // First swap array[row][column] with array[column][row].
        transpose(board);

        // Then swap rows on each side.
        swapRows(board);
    }

    /**
     * Helper method that will be used in the rotate() method.
     * Will rotate our two dimensional array clockwise.
     *
     * @param board Our game board that will be rotated
     */
    private static void rotateClockwise(int[][] board) {
        // First swap rows on each side.
        swapRows(board);

        // Then swap array[row][column] with array[column][row].
        transpose(board);
    }

}
