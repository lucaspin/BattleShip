package main.java;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * @author lucaspinheiro
 */
public enum ShipOrientations {
    HORIZONTAL,
    VERTICAL;

    private static final List<ShipOrientations> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    /**
     * Get a random dimension
     * @return a random ship dimension
     */
    static ShipOrientations getRandomOrientation() {
        return VALUES.get(RANDOM.nextInt(SIZE));
    }
}
