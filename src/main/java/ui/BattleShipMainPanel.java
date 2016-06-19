package main.java.ui;

import main.java.beans.GridCell;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;

/**
 * @author Lucas Pinheiro @lucaspin
 */
class BattleShipMainPanel extends JPanel {
    private GridCell[][] grid;
    private int hDimension;
    private int vDimension;

    /**
     * Constructor of the class
     * @param grid {BattleShipGrid} the battle ship grid
     */
    BattleShipMainPanel(GridCell[][] grid, int hDimension, int vDimension) {
        this.grid = grid;
        this.hDimension = hDimension;
        this.vDimension = vDimension;
        this.setInitialOpts();
    }

    /**
     * Set the initial layout options for the main panel
     */
    private void setInitialOpts() {
        this.setLayout(new GridLayout(this.hDimension, this.vDimension));

        int width = BattleShipMainFrame.DEFAULT_WIDTH - 50;
        int height = ((3 * BattleShipMainFrame.DEFAULT_HEIGHT) / 4) - 50;

        this.setPreferredSize(new Dimension(width , height));
        this.setBorder(new EmptyBorder(new Insets(0, 5, 5, 5)));
    }

    /**
     * Function that adds the grid to the panel
     */
    void displayGrid() {
        for (int row = 0; row < this.hDimension; row++) {
            for (int column = 0; column < this.vDimension; column++) {
                int cellRow = this.grid[row][column].getX();
                int cellColumn = this.grid[row][column].getY();
                String buttonName = String.format("[%s][%s]", cellRow, cellColumn);

                GridCellButton cellButton = new GridCellButton(buttonName);
                cellButton.setX(cellRow);
                cellButton.setY(cellColumn);

                // TODO: add event listener

                this.add(cellButton);
            }
        }
    }

}
