package main.java.ui;

import main.java.beans.GridCell;

import javax.swing.JButton;

/**
 * @author Lucas Pinheiro @lucaspin
 */
class GridCellButton extends JButton {
    private int x;
    private int y;

    /**
     * Constructor of the class
     */
    GridCellButton(String name) {
        super(name);
    }

    /**
     * Set the x coordinate of the cell
     * @param _x {int} the x coordinate of the cell
     */
    void setX(int _x) {
        this.x = _x;
    }

    /**
     * Set the y coordinate of the cell
     * @param _y {int} the y coordinate of the cell
     */
    void setY(int _y) {
        this.y = _y;
    }
}
