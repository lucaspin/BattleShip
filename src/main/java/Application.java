package main.java;

import main.java.beans.BattleShipGame;
import main.java.beans.BattleShipGrid;
import main.java.controller.BattleShipGameController;
import main.java.ui.BattleShipMainFrame;

import javax.swing.SwingUtilities;

/**
 * @author lucaspinheiro
 */
public class Application {
    public static void main(String[] args) {
        BattleShipGame game = new BattleShipGame(5, 5);
        BattleShipGrid battleShipGrid = game.getBattleShipGrid();

        SwingUtilities.invokeLater(() -> {
            BattleShipMainFrame mainFrame = new BattleShipMainFrame(battleShipGrid.getGrid(),
                    battleShipGrid.getHorizontalDimension(),
                    battleShipGrid.getVerticalDimension());
            mainFrame.setVisible(true);
            BattleShipGameController controller = new BattleShipGameController(game, mainFrame);
            game.setController(controller);
            mainFrame.setController(controller);
            mainFrame.displayGrid();
        });

        game.initGame();
    }
}
