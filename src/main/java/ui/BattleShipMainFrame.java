package main.java.ui;

import main.java.beans.GridCell;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Lucas Pinheiro @lucaspin
 */
public class BattleShipMainFrame extends JFrame {
    static final int DEFAULT_WIDTH = 500;
    static final int DEFAULT_HEIGHT = 500;

    private BattleShipMainPanel battleShipMainPanel;

    /**
     * Constructor of the class
     * @param grid {GridCell[][]} the battle ship grid
     */
    public BattleShipMainFrame(GridCell[][] grid, int hDimension, int vDimension) {
        super("BattleShip");
        this.setInitialFrameOpts();
        this.setWindowClosingHandler();
        this.battleShipMainPanel = new BattleShipMainPanel(grid, hDimension, vDimension);
        this.getContentPane().add(this.battleShipMainPanel);
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
        this.setSize(BattleShipMainFrame.DEFAULT_WIDTH, BattleShipMainFrame.DEFAULT_HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    /**
     * Display the battle ship grid
     */
    public void displayGrid() {
        this.battleShipMainPanel.displayGrid();
    }

}
