package main.java;

/**
 * @author lucaspinheiro
 */
public class ShipCell extends Cell {

    private boolean hit = false;

    /**
     * @constructor
     * @param x the x coordinate of the ShipCell
     * @param y the y coordinate of the ShipCell
     */
    public ShipCell(int x, int y) {
        super(x, y);
    }

    /**
     * Set the state of the ShipCell
     * @param hit the state of this ShipCell
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    /**
     * Check whether this ShipCell has been hitted
     * @return isHit if this ShipCell is hitted
     */
    public boolean isHit() {
        return this.hit;
    }
}
