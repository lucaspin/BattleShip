package main.java;

/**
 * @author lucaspinheiro
 */
public enum ShipDimensions {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private int dimension;

    ShipDimensions(int dimension) {
        this.dimension = dimension;
    }

    int getValue() {
        return this.dimension;
    }
}
