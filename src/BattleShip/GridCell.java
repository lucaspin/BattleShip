package BattleShip;

/**
 * @author lucaspinheiro
 */
public class GridCell extends Cell {

    private boolean empty = false;

    /**
     * @constructor
     * @param x the x coordinate of the gridCell
     * @param y the y coordinate of the gridCell
     */
    public GridCell(int x, int y) {
        super(x, y);
    }

    /**
     * Check whether a GridCell is empty or not
     * @return empty the state of the cell
     */
    public boolean isEmpty() {
        return this.empty;
    }

    /**
     * Set the state of the GridCell
     * @param empty the new state of the GridCell
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

}

