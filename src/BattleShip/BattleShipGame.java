package BattleShip;

/**
 * @author lucaspinheiro
 */
public class BattleShipGame {
    private boolean ended = false;
    private BattleShipGrid grid;

    /**
     * @constructor
     * @param gridVerticalDimension the vertical dimension of the grid to build
     * @param gridHorizontalDimension he horizontal dimension of the grid to build
     */
    public BattleShipGame(int gridVerticalDimension, int gridHorizontalDimension) {
        // TODO: improve this check
        if (gridHorizontalDimension > 0 && gridVerticalDimension > 0) {
            this.grid = new BattleShipGrid(gridVerticalDimension, gridVerticalDimension);
        }
    }

    public void initGame() {
        // TODO
    }

    /**
     * Check if the game has ended
     * @return ended if the game has ended
     */
    public boolean isEnded() {
        return this.ended;
    }

    /**
     * Set the game status
     * @param ended whether end the game or not
     */
    public void setEnded(boolean ended) {
        this.ended = ended;
    }
}
