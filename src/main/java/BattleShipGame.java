package main.java;

import java.util.*;

/**
 * @author lucaspinheiro
 */
public class BattleShipGame {

    // This variable will indicate if the game has ended
    private boolean ended = false;

    // This variable will hold the reference to the grid
    private BattleShipGrid grid;

    // This list will hold the valid positions for ships of a dimension of 1 unit
    private List<Cell> validPositionsForUnitShip = new ArrayList<>();

    // This map will hold the valid positions for vertical ships of 1+ dimensions
    private HashMap<Integer, ArrayList<Cell>> validVerticalPositionsMap = new HashMap<>();

    // This map will hold the valid positions for horizontal ships of 1+ dimensions
    private HashMap<Integer, ArrayList<Cell>> validHorizontalPositionsMap = new HashMap<Integer, ArrayList<Cell>>();

    /**
     * @constructor
     * @param gridVerticalDimension the vertical dimension of the grid to build
     * @param gridHorizontalDimension he horizontal dimension of the grid to build
     */
    public BattleShipGame(int gridVerticalDimension, int gridHorizontalDimension) {
        if (this.isVerticalGridDimensionValid(gridVerticalDimension) && this.isHorizontalGridDimensionValid(gridHorizontalDimension)) {
            this.grid = new BattleShipGrid(gridVerticalDimension, gridVerticalDimension);

            // Create map to store valid start positions for ships
            this.createValidPositionsMaps();

            // Populate the grid
            this.populateGrid();
        }
    }

    /**
     * Create the maps that will hold every possible start position for a ship,
     */
    private void createValidPositionsMaps() {
        this.createValidPositionsForUnitShip();
        this.createVerticalValidPositionsMap();
        this.createHorizontalValidPositionsMap();
    }

    /**
     * Create the map that will hold every possible start position for a unitary ship
     */
    private void createValidPositionsForUnitShip() {
        for (int x = 0; x < this.grid.getHorizontalDimension(); x++) {
            for (int y = 0; y < this.grid.getVerticalDimension(); y++) {
                Cell validCell = new Cell(x, y);
                this.validPositionsForUnitShip.add(validCell);
            }
        }
    }

    /**
     * Update the valid vertical positions map for 1+ dimension after a ship insertion
     * @param cell the start position of the ship that was just inserted
     * @param dimension the dimension of the ship that was just inserted
     */
    private void updateValidPositionsForUnitShip(Cell cell, int dimension) {
        // TODO
    }

    /**
     * Create the valid vertical positions map for ships +1 dimension
     */
    private void createVerticalValidPositionsMap() {
        this.validVerticalPositionsMap.put(2, this.calculateInitialValidVerticalPositions(2));
        this.validVerticalPositionsMap.put(3, this.calculateInitialValidVerticalPositions(3));
        this.validVerticalPositionsMap.put(4, this.calculateInitialValidVerticalPositions(4));
        this.validVerticalPositionsMap.put(5, this.calculateInitialValidVerticalPositions(5));
    }

    /**
     * Create the valid horizontal positions map for ships +1 dimension
     */
    private void createHorizontalValidPositionsMap() {
        this.validHorizontalPositionsMap.put(2, this.calculateInitialValidHorizontalPositions(2));
        this.validHorizontalPositionsMap.put(3, this.calculateInitialValidHorizontalPositions(3));
        this.validHorizontalPositionsMap.put(4, this.calculateInitialValidHorizontalPositions(4));
        this.validHorizontalPositionsMap.put(5, this.calculateInitialValidHorizontalPositions(5));
    }

    /**
     * Build the lists of valid start positions for a vertical ship based on its dimension
     * @param dimension the dimension of the ship
     * @return validPositionsList {ArrayList}
     */
    private ArrayList<Cell> calculateInitialValidVerticalPositions(int dimension) {
        ArrayList<Cell> validPositionsList = new ArrayList<Cell>();

        for (int x = 0; x < this.grid.getHorizontalDimension(); x++) {
            for (int y = 0; y < (this.grid.getVerticalDimension() - dimension); y++) {
                Cell validCell = new Cell(x, y);
                validPositionsList.add(validCell);
            }
        }

        return validPositionsList;
    }

    /**
     * Update the valid vertical positions map for 1+ dimension after a ship insertion
     * @param cell the start position of the ship that was just inserted
     * @param dimension the dimension of the ship that was just inserted
     * @param orientation the orientation of the ship that was just inserted
     */
    private void updateValidVerticalPositionsMap(Cell cell, int dimension, ShipOrientations orientation) {

        switch(orientation) {
            case HORIZONTAL:
                // TODO: remove the ship parts and (n - 1) to the left of the start position
                break;
            case VERTICAL:
                // TODO: remove the ship parts and (n - 1) to the left of all its parts
                break;
        }

        // TODO: remember to check for grid bounds when doing this
    }

    /**
     * Build the lists of valid start positions for a horizontal ship based on its dimension
     * @param dimension the dimension of the ship
     * @return validPositionsList {ArrayList}
     */
    private ArrayList<Cell> calculateInitialValidHorizontalPositions(int dimension) {
        ArrayList<Cell> validPositionsList = new ArrayList<Cell>();

        for (int x = 0; x < (this.grid.getHorizontalDimension() - dimension); x++) {
            for (int y = 0; y < this.grid.getVerticalDimension(); y++) {
                Cell validCell = new Cell(x, y);
                validPositionsList.add(validCell);
            }
        }

        return validPositionsList;
    }

    /**
     * Update the valid horizontal positions map for 1+ dimension after a ship insertion
     * @param cell the start position of the ship that was just inserted
     * @param dimension the dimension of the ship that was just inserted
     * @param orientation the orientation of the ship that was just inserted
     */
    private void updateValidHorizontalPositionsMap(Cell cell, int dimension, ShipOrientations orientation) {

        switch(orientation) {
            case HORIZONTAL:
                // TODO: remove the ship parts and (n - 1) to the left of the start position
                break;
            case VERTICAL:
                // TODO: remove the ship parts and (n - 1) to the left of all its parts
                break;

            // TODO: remember to check for grid bounds when doing this
        }
    }

    public void initGame() {
        // initialize game
        while(!this.isEnded()) {
            // TODO: get guess
            // TODO: check if guess has hit some ship
            // TODO: update grid accordingly
        }

    }

    /**
     * Function that initializes the game, populating the grid with ships
     * of random dimension and orientation
     */
    private void populateGrid() {
        while(!this.grid.isFull()) {
            Ship newShip = this.fetchShipFromValidPositionsMap();
            this.grid.addShip(newShip);

            // Get new ship info
            int dimension = newShip.getDimension().getValue();
            ShipOrientations orientation = newShip.getOrientation();
            Cell startPosition = newShip.getStartPosition();

            // Update the valid positions
            this.updateValidPositionsForUnitShip(startPosition, dimension);
            this.updateValidHorizontalPositionsMap(startPosition, dimension, orientation);
            this.updateValidVerticalPositionsMap(startPosition, dimension, orientation);
        }
    }

    public List<Cell> getValidPositionsForUnitShip() {
        return this.validPositionsForUnitShip;
    }

    /**
     * Generate a new ship, fetching it from
     * @return newShip {Ship}
     */
    private Ship fetchShipFromValidPositionsMap() {
        ShipDimensions randomDimension = ShipDimensions.getRandomDimension();
        ShipOrientations randomOrientation = ShipOrientations.getRandomOrientation();
        Cell startPosition = null;
        Random randomNumber = new Random();
        int randomIndex;

        // Get startPosition from the right map
        if (randomDimension == ShipDimensions.ONE) {
            randomIndex = randomNumber.nextInt(this.validPositionsForUnitShip.size());
            startPosition = this.validPositionsForUnitShip.get(randomIndex);
        } else if (randomOrientation == ShipOrientations.HORIZONTAL) {
            ArrayList<Cell> validPositions = this.validHorizontalPositionsMap.get(randomDimension.getValue());
            randomIndex = randomNumber.nextInt(validPositions.size());
            startPosition = validPositions.get(randomIndex);
        } else if (randomOrientation == ShipOrientations.VERTICAL) {
            ArrayList<Cell> validPositions = this.validVerticalPositionsMap.get(randomDimension.getValue());
            randomIndex = randomNumber.nextInt(validPositions.size());
            startPosition = validPositions.get(randomIndex);
        }

        Ship newShip = new Ship(randomDimension, randomOrientation, startPosition);

        return newShip;
    }

    /**
     * Ask the user for a guess
     * @return guess {String}
     */
    public String askForGuess() {
        Scanner input = new Scanner(System.in);
        String guess;

        guess = input.nextLine();

        // TODO: check and format guess, returning a Cell

        input.close();

        return guess;
    }

    /**
     * Check whether a dimension is a valid vertical dimension for the grid
     * @param dimension the dimension to check
     * @return {boolean}
     */
    private boolean isVerticalGridDimensionValid(int dimension) {
        boolean isValidDimension = false;

        if (dimension >= BattleShipGrid.MIN_VERTICAL_DIMENSION && dimension <= BattleShipGrid.MAX_VERTICAL_DIMENSION) {
            isValidDimension = true;
        }

        return isValidDimension;
    }

    /**
     * Check a whether a dimension is a valid horizontal dimension for the grid
     * @param dimension the dimension to check
     * @return {boolean}
     */
    private boolean isHorizontalGridDimensionValid(int dimension) {
        boolean isValidDimension = false;

        if (dimension >= BattleShipGrid.MIN_HORIZONTAL_DIMENSION && dimension <= BattleShipGrid.MAX_HORIZONTAL_DIMENSION) {
            isValidDimension = true;
        }

        return isValidDimension;
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
