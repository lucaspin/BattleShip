package main.java;

import main.java.beans.BattleShipGame;

/**
 * @author lucaspinheiro
 */
public class Application {
    public static void main(String[] args) {
        BattleShipGame game = new BattleShipGame(5, 5);
        game.initGame();
    }
}
