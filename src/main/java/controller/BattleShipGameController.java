package main.java.controller;

import main.java.beans.*;
import main.java.ui.*;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author Lucas Pinheiro @lucaspin
 */
public class BattleShipGameController {
    private BattleShipGame game;
    private BattleShipMainFrame view;

    /**
     * Constructor of the class
     */
    public BattleShipGameController(BattleShipGame game, BattleShipMainFrame mainFrame) {
        this.game = game;
        this.view = mainFrame;
    }

    public void closeWindow() {
        this.view.dispatchEvent(new WindowEvent(this.view, WindowEvent.WINDOW_CLOSING));
    }

    public void checkGuess(GridCell guessedCell, GridCellButton cellButton) {
        BattleShipGrid battleShipGrid = this.game.getBattleShipGrid();

        this.game.increaseNumberOfGuesses();

        if (guessedCell.isEmpty()) {
            view.setGameInfoText(GameMessages.WATER_HIT);
            cellButton.setText("");
        } else {
            view.setGameInfoText(GameMessages.SHIP_HIT);
            cellButton.setText("X");
            // Find the ship to which that cell belongs
            Ship hitShip = null;

            for (Ship ship : battleShipGrid.getShips()) {
                for (Cell shipPart : ship.getParts()) {
                    if (shipPart.getX() == guessedCell.getX() && shipPart.getY() == guessedCell.getY()) {
                        hitShip = ship;
                    }
                }
            }

            // Remove the ship
            if (hitShip != null) {
                hitShip.removePart(guessedCell);

                // Check if ship was destroyed
                if (hitShip.getParts().size() == 0) {
                    battleShipGrid.removeShip(hitShip);
                    String message = GameMessages.SHIP_DESTROYED + "\n" +
                            "==> There are " + battleShipGrid.getShips().size() + " remaining.";
                    view.setGameInfoText(message);
                }

                // Check if game has ended
                if (battleShipGrid.getShips().size() == 0) {
                    int dialogResult = JOptionPane.showConfirmDialog(null, GameMessages.END_GAME_DIALOG_MESSAGE,
                            GameMessages.END_GAME_DIALOG_TITLE, JOptionPane.YES_NO_OPTION);

                    if (dialogResult == JOptionPane.NO_OPTION) {
                        this.game.setEnded(new AtomicBoolean(true));
                    } else {
                        // TODO: Start another game
                    }

                }
            }
        }

        synchronized (this.game.threadObject) {
            this.game.threadObject.notify();
        }
    }
}
