package BattleShip;

import java.util.ArrayList;

/**
 * @author lucaspinheiro
 */
public class BattleShipGrid {
    private int verticalDimension;
    private int horizontalDimension;
    private GridCell[][] grid;
    private ArrayList<Ship> ships;

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
        } else {
            System.out.println("[BattleShip.BattleShipGrid.constructor] dimensions must be positive");
        }
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

        // Create the grid and populate with GridCell
        for (int horizontalIndex = 0; horizontalIndex <= horizontalDimension; horizontalIndex++) {
            for(int verticalIndex = 0; verticalIndex <= verticalDimension; verticalIndex++) {
                GridCell gridCell = new GridCell(horizontalIndex, verticalIndex);
                this.grid[horizontalIndex][verticalIndex] = gridCell;
            }
        }
    }

    /**
     * Check whether a ship can be placed in the grid vertically
     * @param ship the ship to check
     * @return {Boolean}
     */
    private boolean canShipBeVerticalllyDrawn(Ship ship) {
        boolean canShipBeDrawn = false;

        if ((this.getVerticalDimension() - ship.getStartPosition().getX()) >= ship.getDimension()) {
            canShipBeDrawn = true;
        }

        return canShipBeDrawn;
    }

    /**
     * Check whether a ship can be drawn horizontally
     * @param ship the ship to check
     * @return canBeDrawn if the ship can be drawn
     */
    private boolean canShipBeHorizontallyDrawn(Ship ship) {
        boolean canBeDrawn = false;

        if (this.getHorizontalDimension() - ship.getStartPosition().getY() >= ship.getDimension()) {
            canBeDrawn = true;
        }

        return canBeDrawn;
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
        if (this.isValidShipStartPosition(ship)) {
            this.setShipParts(ship);
            this.addShipToGrid(ship);
        } else {
            System.out.println("[BattleShip.BattleShipGrid.addShip] the startPosition of the ship isn't part of the grid");
        }
    }

    /**
     * Add a ship to the grid
     * @param ship the ship to add
     */
    private void addShipToGrid(Ship ship) {
        this.ships.add(ship);
    }

    /**
     * Remove a ship from the grid
     * @param ship the ship to remove
     */
    private void removeShipFromGrid(Ship ship) {
        this.ships.remove(ship);
    }

    /**
     * Set where in the grid a ship is going to be
     * @param ship the ship to set parts
     */
    private void setShipParts(Ship ship) {
        // TODO: check for NON_EMPTY cells

        switch(ship.getOrientation()) {
            case Ship.VERTICAL:

                if (this.canShipBeVerticalllyDrawn(ship)) {
                    int yPoint = ship.getStartPosition().getY();
                    int xPoint = ship.getStartPosition().getX();

                    for (int count = 0; count <= ship.getDimension(); count++, yPoint++) {
                        ShipCell newShipPart = new ShipCell(xPoint, yPoint);
                        ship.addPart(newShipPart);
                    }

                } else {
                    System.out.println("[BattleShip.BattleShipGrid.setShipParts] the ship can't be drawn");
                }

                break;
            case Ship.HORIZONTAL:

                if (this.canShipBeHorizontallyDrawn(ship)) {
                    int yPoint = ship.getStartPosition().getY();
                    int xPoint = ship.getStartPosition().getX();

                    for (int count = 0; count <= ship.getDimension(); count++, xPoint++) {
                        ShipCell newShipPart = new ShipCell(xPoint, yPoint);
                        ship.addPart(newShipPart);
                    }

                } else {
                    System.out.println("[BattleShip.BattleShipGrid.setShipParts] the ship can't be drawn");
                }

                break;
        }

    }
}
