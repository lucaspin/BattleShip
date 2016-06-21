package main.java.ui;

import javax.swing.JButton;

/**
 * @author Lucas Pinheiro @lucaspin
 */
public class GridCellButton extends JButton {
    private int x;
    private int y;

    /**
     * Constructor of the class
     */
    GridCellButton(String name) {
        super(name);
        this.setFocusable(false);
    }

    /**
     * Set the x coordinate of the cell
     * @param _x {int} the x coordinate of the cell
     */
    void setXCoordinate(int _x) {
        this.x = _x;
    }

    /**
     * Set the y coordinate of the cell
     * @param _y {int} the y coordinate of the cell
     */
    void setYCoordinate(int _y) {
        this.y = _y;
    }

    int getYCoordinate() {
        return this.y;
    }

    int getXCoordinate() {
        return this.x;
    }
}
