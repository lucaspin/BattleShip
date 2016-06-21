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
    private int hDimension;
    private int vDimension;

    /**
     * Constructor of the class
     */
    BattleShipMainPanel(int hDimension, int vDimension) {
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
}
