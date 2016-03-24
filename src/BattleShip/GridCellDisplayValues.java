package BattleShip;

/**
 * @author lucaspinheiro
 */
public enum GridCellDisplayValues {
    NO_GUESS("-"),
    GUESS_EMPTY("*"),
    GUESS_NON_EMPTY("X");

    private String displayValue;

    GridCellDisplayValues(String displayValue) {
        this.displayValue = displayValue;
    }

    String getValue() {
        return this.displayValue;
    }
}
