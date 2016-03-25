package BattleShip;

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
            System.out.println("[Cell.constructor] params must be equal or greater than zero");
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
            System.out.println("[Cell.constructor] param must be equal or greater than 0");
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
            System.out.println("[Cell.constructor] param must be equal or greater than 0");
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
}