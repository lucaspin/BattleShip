package main.java.ui;

import main.java.beans.GameMessages;
import main.java.beans.GridCell;
import main.java.controller.BattleShipGameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Lucas Pinheiro @lucaspin
 */
public class BattleShipMainFrame extends JFrame {
    static final int DEFAULT_WIDTH = 800;
    static final int DEFAULT_HEIGHT = 600;

    private BattleShipGameController controller;
    private BattleShipMainPanel battleShipMainPanel;
    private GameInfoPanel gameInfoPanel;
    private int gridVDimension;
    private int gridHDimension;
    private GridCell[][] grid;

    /**
     * Constructor of the class
     * @param grid {GridCell[][]} the battle ship grid
     */
    public BattleShipMainFrame(GridCell[][] grid, int hDimension, int vDimension) {
        super("BattleShip");
        this.setInitialFrameOpts();
        this.setWindowClosingHandler();

        this.grid = grid;
        this.gridHDimension = hDimension;
        this.gridVDimension = vDimension;

        this.battleShipMainPanel = new BattleShipMainPanel(hDimension, vDimension);
        this.gameInfoPanel = new GameInfoPanel();

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.anchor = GridBagConstraints.NORTH;
        this.getContentPane().add(this.battleShipMainPanel, constraints);

        constraints.anchor = GridBagConstraints.SOUTH;
        constraints.gridy = 1;
        this.getContentPane().add(this.gameInfoPanel, constraints);
    }

    /**
     * Set the listener for when the user closes the window
     */
    private void setWindowClosingHandler() {
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                e.getWindow().dispose();
            }
        });
    }

    /**
     * Set the initial layout options for the main frame
     */
    private void setInitialFrameOpts() {
        this.setLayout(new GridBagLayout());
        this.setSize(BattleShipMainFrame.DEFAULT_WIDTH, BattleShipMainFrame.DEFAULT_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * Display the battle ship grid
     */
    public void displayGrid() {
        for (int row = 0; row < this.gridHDimension; row++) {
            for (int column = 0; column < this.gridVDimension; column++) {
                int cellRow = this.grid[row][column].getX();
                int cellColumn = this.grid[row][column].getY();
                String buttonName = String.format("[%s][%s]", cellRow, cellColumn);

                GridCellButton cellButton = new GridCellButton(buttonName);
                cellButton.setXCoordinate(cellRow);
                cellButton.setYCoordinate(cellColumn);

                cellButton.addActionListener(e -> {
                    GridCellButton buttonClicked = (GridCellButton) e.getSource();
                    buttonClicked.setEnabled(false);
                    int x = buttonClicked.getXCoordinate();
                    int y = buttonClicked.getYCoordinate();
                    this.controller.checkGuess(this.grid[x][y], buttonClicked);
                });

                this.battleShipMainPanel.add(cellButton);
            }
        }
    }

    /**
     * Display game info to the user.
     * @param text {String}
     */
    public void setGameInfoText(String text) {
        this.gameInfoPanel.setGameInfoText(text);
    }

    /**
     * Set the game controller
     * @param controller {BattleShipController}
     */
    public void setController(BattleShipGameController controller) {
        this.controller = controller;
    }

}
