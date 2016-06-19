package main.java.beans;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    private static final List<ShipDimensions> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * @constructor
     * @param dimension {int}
     */
    ShipDimensions(int dimension) {
        this.dimension = dimension;
    }

    /**
     * Get the dimension value
     * @return dimension {dimension}
     */
    public int getValue() {
        return this.dimension;
    }

    /**
     * Get a random dimension
     * @return a random ship dimension
     */
    static ShipDimensions getRandomDimension() {
       return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
