package main.java.ui;

import main.java.beans.GameMessages;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Color;

/**
 * @author Lucas Pinheiro @lucaspin
 */
class GameInfoPanel extends JPanel {
    private JLabel gameInfo;

    /**
     * Constructor of the class
     */
    GameInfoPanel() {
        this.setInitialOpts();
        this.gameInfo = new JLabel(GameMessages.WELCOME);
        this.gameInfo.setHorizontalAlignment(SwingConstants.CENTER);
        this.gameInfo.setVerticalAlignment(SwingConstants.CENTER);
        this.add(this.gameInfo);
    }

    /**
     * Set the initial layout options for this pane
     */
    private void setInitialOpts() {
        int width = BattleShipMainFrame.DEFAULT_WIDTH - 50;
        int height = (BattleShipMainFrame.DEFAULT_HEIGHT / 4) - 50;
        this.setPreferredSize(new Dimension(width, height));
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));
    }

    /**
     * Set the game info text
     * @param gameInfo {String}
     */
    public void setGameInfoText(String gameInfo) {
        this.gameInfo.setText(gameInfo);
    }
}
