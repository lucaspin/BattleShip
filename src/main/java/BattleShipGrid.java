package main.java;

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
    private ArrayList<Ship> ships;

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
        // TODO: Improve this check
        if (horizontalDimension > 0 && verticalDimension > 0) {
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
        for (int verticalCount = 0; verticalCount < this.verticalDimension; verticalCount++) {
            for (int horizontalCount = 0; horizontalCount < this.horizontalDimension; horizontalCount++) {
                System.out.print(this.grid[horizontalCount][verticalCount].getDisplayValue().getValue() + " ");
            }
            System.out.print("\n");
        }

    }

    /**
     * Set the max number of occupied cells, according to the grid's dimension
     */
    private void setOccupiedCellsMaxNumber() {
        // TODO: accept a difficulty parameter, instead of setting 0.6 directly
        this.occupiedCellsMaxNumber = Math.round((float) 0.6 * this.verticalDimension * this.horizontalDimension);
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
     * Get the number of occupied cells in the grid
     * @return occupiedCellsCurrentNumber {int}
     */
    public int getOccupiedCellCurrentNumber() {
        return this.occupiedCellsCurrentNumber;
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
    public ArrayList<Ship> getShips() {
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
            this.occupiedCellsCurrentNumber += ship.getDimension();

            // Check if the number of max occupied cells has not been reached
            if (this.occupiedCellsCurrentNumber >= this.occupiedCellsMaxNumber) {
                this.setFull(true);
            }
        } else {
            System.out.println("[main.java.BattleShipGrid.BattleShipGrid.addShip] the startPosition of the ship isn't part of the grid");
        }
    }

    /**
     * Set where in the grid a ship is going to be
     * @param ship the ship to set parts
     */
    private void setShipParts(Ship ship) {
        int xPoint;
        int yPoint;

        switch(ship.getOrientation()) {
            case HORIZONTAL:
                yPoint = ship.getStartPosition().getY();
                xPoint = ship.getStartPosition().getX();

                for (int count = 0; count <= ship.getDimension(); count++, yPoint++) {
                    ShipCell newShipPart = new ShipCell(xPoint, yPoint);
                    ship.addPart(newShipPart);
                }

                break;
            case VERTICAL:
                yPoint = ship.getStartPosition().getY();
                xPoint = ship.getStartPosition().getX();

                for (int count = 0; count <= ship.getDimension(); count++, xPoint++) {
                    ShipCell newShipPart = new ShipCell(xPoint, yPoint);
                    ship.addPart(newShipPart);
                }
                break;
        }
    }
}
