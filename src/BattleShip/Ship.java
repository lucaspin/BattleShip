package BattleShip;

import java.util.Objects;
import java.util.ArrayList;

/**
 * @author lucaspinheiro
 */
public class Ship {
    // Constants that hold the dimension limits
    // TODO: calculate these based on grid's dimension
    private final int MIN_DIMENSION = 1;
    private final int MAX_DIMENSION = 4;

    private int dimension;
    private ShipOrientations orientation;
    private Cell startPosition;
    private ArrayList<ShipCell> parts;

    /**
     * @constructor
     * @param dimension the dimension of the ship
     * @param orientation the orientation of the ship
     * @param startPosition the start position of the ship
     */
    public Ship(int dimension, ShipOrientations orientation, Cell startPosition) {
        // TODO: check if startPosition is valid as well (maybe check this on Grid, instead of here)
        if (this.isValidDimension(dimension) && startPosition != null) {
            this.dimension = dimension;
            this.orientation = orientation;
            this.startPosition = startPosition;
        } else {
            System.out.println("[BattleShip.Ship.constructor] Invalid parameters");
        }
    }

    /**
     * Check if the given dimension is valid
     * @param dimension the dimension to check
     * @return isValidDimension true if dimension is valid
     */
    private boolean isValidDimension(int dimension) {
        boolean isValidDimension = false;

        if (dimension >= MIN_DIMENSION && dimension <= MAX_DIMENSION) {
            isValidDimension = true;
        }

        return isValidDimension;
    }

    /**
     * Get the ship dimension
     * @return dimension the dimension of the ship
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Get the ship's orientation
     * @return orientation the orientation of this ship
     */
    public ShipOrientations getOrientation() {
        return this.orientation;
    }

    /**
     * Get the start position of the ship
     * @return startPosition the startPosition of the ship
     */
    public Cell getStartPosition() {
        return this.startPosition;
    }

    /**
     * Add a new part to the ship
     * @param newPart the new part of the ship to be added
     */
    public void addPart(ShipCell newPart) {
        if (newPart != null && this.parts.size() < this.dimension) {
            this.parts.add(newPart);
        }
    }

    /**
     * Hit a part of the ship
     * @param part the part to hit
     */
    public void hitPart(ShipCell part) {
        int index = this.parts.indexOf(part);

        // TODO: check if part really belongs to the ship
        ShipCell partToUpdate = this.parts.get(index);
        partToUpdate.setHit(true);
    }

    /**
     * Get the ship's parts
     * @return parts the parts of the ship
     */
    public ArrayList<ShipCell> getParts() {
       return this.parts;
    }

}
