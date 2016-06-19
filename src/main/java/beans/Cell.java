package main.java.beans;

/**
 * @author lucaspinheiro
 */
public class Cell {
    private int x;
    private int y;

    /**
     * @constructor
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Cell(int x, int y) {
        if (x >= 0 && y >= 0) {
            this.x = x;
            this.y = y;
        } else {
            System.out.println("[main.java.beans.Cell.constructor] params must be equal or greater than zero");
        }
    }

    /**
     * Set the new x coordinate of the cell
     * @param newX the new x coordinate of the cell
     */
    public void setX(int newX) {
        if (newX >= 0) {
            this.x = newX;
        } else {
            System.out.println("[main.java.beans.Cell.constructor] param must be equal or greater than 0");
        }
    }

    /**
     * Set the new y coordinate of the cell
     * @param newY the new x coordinate of the cell
     */
    public void setY(int newY) {
        if (newY >= 0) {
            this.y = newY;
        } else {
            System.out.println("[main.java.beans.Cell.constructor] param must be equal or greater than 0");
        }
    }

    /**
     * Get the x coordinate of the cell
     * @return x the x coordinate of the cell
     */
    public int getX() {
        return this.x;
    }

    /**
     * Get the y coordinate of the cell
     * @return y the y coordinate of the cell
     */
    public int getY() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {

        // Return true if object is compared to itself
        if (o == this) {
            return true;
        }

        // Return false if object is not of type Cell
        if (!(o instanceof Cell)) {
            return false;
        }

        // Cast o and compare the attributes
        Cell cellToCompare = (Cell) o;
        return this.x == cellToCompare.getX() && this.y == cellToCompare.getY();
    }
}