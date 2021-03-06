package main.java.beans;

/**
 * @author lucaspinheiro
 */
public class GridCell extends Cell {

    private boolean empty = true;
    private boolean alreadyGuessed = false;
    private GridCellDisplayValues displayValue = GridCellDisplayValues.NO_GUESS;

    /**
     * Constructor of the class
     * @param x the x coordinate of the gridCell
     * @param y the y coordinate of the gridCell
     */
    public GridCell(int x, int y) {
        super(x, y);
    }

    /**
     * Check whether a GridCell is empty or not
     * @return empty {boolean}
     */
    public boolean isEmpty() {
        return this.empty;
    }

    /**
     * Set if the GridCell is empty
     * @param empty {boolean}
     */
    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    /**
     * Set if this GridCell has already been guessed
     * @param alreadyGuessed {boolean}
     */
    public void setAlreadyGuessed(boolean alreadyGuessed) {
        this.alreadyGuessed = alreadyGuessed;
    }

    /**
     * Check whether a this GridCell has been already guessed
     * @return alreadyGuessed {boolean}
     */
    public boolean isAlreadyGuessed() {
        return this.alreadyGuessed;
    }

    /**
     * Set the new display value of the GridCell
     * @param displayValue {String}
     */
    public void setDisplayValue(GridCellDisplayValues displayValue) {
       this.displayValue = displayValue;
    }

    /**
     * Get the display value of the cell
     * @return displayValue {String}
     */
    public GridCellDisplayValues getDisplayValue() {
        return this.displayValue;
    }
}

