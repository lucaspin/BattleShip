package main.java;

import java.util.List;
import java.util.ArrayList;

/**
 * @author lucaspinheiro
 */
public class BattleShipGrid {
    private int verticalDimension;
    private int horizontalDimension;
    private int occupiedCellsMaxNumber;
    private int occupiedCellsCurrentNumber;
    private boolean full = false;
    private GridCell[][] grid;
    private List<Ship> ships = new ArrayList<>();

    // Expose constants to limit the dimensions of the grid
    public static final int MIN_VERTICAL_DIMENSION = 5;
    public static final int MIN_HORIZONTAL_DIMENSION = 5;
    public static final int MAX_VERTICAL_DIMENSION = 20;
    public static final int MAX_HORIZONTAL_DIMENSION = 20;

    /**
     * @constructor
     * @param horizontalDimension the horizontal dimension of the grid
     * @param verticalDimension the vertical dimension of the grid
     */
    public BattleShipGrid(int horizontalDimension, int verticalDimension) {
        if (horizontalDimension >= MIN_HORIZONTAL_DIMENSION && horizontalDimension <= MAX_HORIZONTAL_DIMENSION &&
            verticalDimension >= MIN_VERTICAL_DIMENSION && verticalDimension <= MAX_VERTICAL_DIMENSION) {
            this.verticalDimension = verticalDimension;
            this.horizontalDimension = horizontalDimension;
            this.initGrid();
            this.setOccupiedCellsMaxNumber();
        } else {
            System.out.println("[main.java.BattleShipGrid.constructor] dimensions must be positive");
        }
    }

    /**
     * Function that draws the grid in the terminal
     */
    public void displayGrid() {
        System.out.printf("\n%2s |", "");

        for (int count = 0; count < this.verticalDimension; count++) {
            System.out.printf("%2d |", count);
        }

        System.out.println();

        for (int verticalCount = 0; verticalCount < this.verticalDimension; verticalCount++) {
            for (int horizontalCount = 0; horizontalCount < this.horizontalDimension; horizontalCount++) {

                if (horizontalCount == 0) {

                    // Display the separators
                    for (int count = 0; count <= this.horizontalDimension; count++) {
                        System.out.printf("%1s%1s", "–––", "");
                    }

                    // Go to next line
                    System.out.println();
                }

                if (horizontalCount == 0) {
                    System.out.printf("%2s |", verticalCount);
                }

                // Print the symbols
                System.out.printf("%2s |", this.grid[horizontalCount][verticalCount].getDisplayValue().getValue());
            }
            System.out.print("\n");
        }

        System.out.println();
    }

    /**
     * Set the max number of occupied cells, according to the grid's dimension
     */
    private void setOccupiedCellsMaxNumber() {
        this.occupiedCellsMaxNumber = Math.round((float) 0.4 * this.verticalDimension * this.horizontalDimension);
    }

    /**
     * Set a grid's state to full
     * @param full {boolean}
     */
    private void setFull(boolean full) {
        this.full = full;
    }

    /**
     * Check whether the grid is full
     * @return full {boolean}
     */
    public boolean isFull() {
        return this.full;
    }

    /**
     * Get the vertical dimension of the grid
     * @return verticalDimension the vertical dimension of the grid
     */
    public int getVerticalDimension() {
        return this.verticalDimension;
    }

    /**
     * Get the horizontal dimension of the grid
     * @return horizontalDimension the horizontal dimension of the grid
     */
    public int getHorizontalDimension() {
        return this.horizontalDimension;
    }

    /**
     * Get the grid's current ships
     * @return ships the non-destroyed ships in the grid
     */
    public List<Ship> getShips() {
        return this.ships;
    }

    /**
     * Initialize the BattleShipGrid
     */
    public void initGrid() {
        int horizontalDimension = this.getHorizontalDimension();
        int verticalDimension = this.getVerticalDimension();

        // Create grid
        this.grid = new GridCell[horizontalDimension][verticalDimension];

        // Populate it with GridCells
        for (int verticalIndex = 0; verticalIndex < verticalDimension; verticalIndex++) {
            for(int horizontalIndex = 0; horizontalIndex < horizontalDimension; horizontalIndex++) {
                GridCell gridCell = new GridCell(horizontalIndex, verticalIndex);
                this.grid[horizontalIndex][verticalIndex] = gridCell;
            }
        }
    }

    /**
     * Check whether a ship's start position is valid or not
     * @param ship the ship to check
     * @return isValidPosition if startPosition is valid
     */
    private boolean isValidShipStartPosition(Ship ship) {
        boolean isValidPosition = true;
        Cell startPosition = ship.getStartPosition();

        if (startPosition.getX() > this.getHorizontalDimension() || startPosition.getY() > this.getVerticalDimension()) {
            isValidPosition = false;
        }

        return isValidPosition;
    }

    /**
     * Add a ship to grid
     * @param ship the ship to add
     */
    public void addShip(Ship ship) {
        if (!this.isFull() && this.isValidShipStartPosition(ship)) {
            this.setShipParts(ship);
            this.ships.add(ship);

            // Update the number of occupied cells
            this.occupiedCellsCurrentNumber += ship.getDimension().getValue();

            // Check if the number of max occupied cells has not been reached
            if (this.occupiedCellsCurrentNumber >= this.occupiedCellsMaxNumber) {
                this.setFull(true);
            }
        } else {
            System.out.println("[main.java.BattleShipGrid.BattleShipGrid.addShip] the startPosition of the ship isn't part of the grid");
        }
    }

    /**
     * Remove a ship from the grid when it is destroyed
     * @param ship the ship to remove from the grid
     */
    public void removeShip(Ship ship) {
        this.ships.remove(ship);
    }

    /**
     * Set where in the grid a ship is going to be
     * @param ship the ship to set parts
     */
    private void setShipParts(Ship ship) {
        int xPoint = ship.getStartPosition().getX();
        int yPoint = ship.getStartPosition().getY();

        switch(ship.getOrientation()) {
            case HORIZONTAL:
                for (int count = 0; count < ship.getDimension().getValue(); count++, xPoint++) {
                    Cell newShipPart = new Cell(xPoint, yPoint);
                    ship.addPart(newShipPart);
                    this.grid[xPoint][yPoint].setEmpty(false);
                }
                break;
            case VERTICAL:
                for (int count = 0; count < ship.getDimension().getValue(); count++, yPoint++) {
                    GridCell newShipPart = new GridCell(xPoint, yPoint);
                    ship.addPart(newShipPart);
                    this.grid[xPoint][yPoint].setEmpty(false);
                }
                break;
        }
    }

    /**
     * Get the grid itself
     * @return grid the grid itself
     */
    public GridCell[][] getGrid() {
        return this.grid;
    }
}
