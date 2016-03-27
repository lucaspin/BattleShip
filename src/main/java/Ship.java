package main.java;

import java.util.List;
import java.util.ArrayList;

/**
 * @author lucaspinheiro
 */
public class Ship {
    private ShipDimensions dimension;
    private ShipOrientations orientation;
    private Cell startPosition;
    private List<Cell> parts = new ArrayList<>();

    /**
     * @constructor
     * @param dimension the dimension of the ship
     * @param orientation the orientation of the ship
     * @param startPosition the start position of the ship
     */
    public Ship(ShipDimensions dimension, ShipOrientations orientation, Cell startPosition) {
        if (startPosition != null) {
            this.dimension = dimension;
            this.orientation = orientation;
            this.startPosition = startPosition;
        } else {
            System.out.println("[main.java.Ship.constructor] Invalid parameters");
        }
    }

    /**
     * Get the ship dimension
     * @return dimension the dimension of the ship
     */
    public ShipDimensions getDimension() {
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
    public void addPart(Cell newPart) {
        if (newPart != null && this.parts.size() < this.dimension.getValue()) {
            this.parts.add(newPart);
        }
    }

    /**
     * Remove a part of a ship. Used when the user hits that part
     * @param partToRemove the part of the ship to remove
     */
    public void removePart(GridCell partToRemove) {
        this.parts.remove(partToRemove);
    }

    /**
     * Get the ship's parts
     * @return parts the parts of the ship
     */
    public List<Cell> getParts() {
       return this.parts;
    }

    @Override
    public boolean equals (Object o) {

        // if it is the same object
        if (o == this) {
            return true;
        }

        // if the object is not of class Ship
        if (!(o instanceof Ship)) {
            return false;
        }

        // Cast object to type Ship
        Ship ship = (Ship) o;

        // Check the properties
        return this.startPosition.getX() == ship.getStartPosition().getX()
               && this.startPosition.getY() == ship.getStartPosition().getY();
    }
}
