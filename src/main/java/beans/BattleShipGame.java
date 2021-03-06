package main.java.beans;

import main.java.controller.BattleShipGameController;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

/**
 * @author lucaspinheiro
 */
public class BattleShipGame {
    private BattleShipGameController controller;

    // This variable will indicate if the game has ended
    private AtomicBoolean ended;

    private AtomicBoolean waitingUserGuess;

    // This variable will indicate the number of guesses the user has made
    private int numberOfGuesses = 0;

    // This variable will hold the reference to the grid
    private BattleShipGrid grid;

    // This list will hold the valid positions for ships of a dimension of 1 unit
    private List<Cell> validPositionsForUnitShip = new ArrayList<>();

    // This map will hold the valid positions for vertical ships of 1+ dimensions
    private Map<ShipDimensions, ArrayList<Cell>> validVerticalPositionsMap = new HashMap<>();

    // This map will hold the valid positions for horizontal ships of 1+ dimensions
    private Map<ShipDimensions, ArrayList<Cell>> validHorizontalPositionsMap = new HashMap<>();

    public Thread threadObject;

    /**
     * @constructor
     * @param gridVerticalDimension the vertical dimension of the grid to build
     * @param gridHorizontalDimension he horizontal dimension of the grid to build
     */
    public BattleShipGame(int gridVerticalDimension, int gridHorizontalDimension) {
        if (this.isVerticalGridDimensionValid(gridVerticalDimension) && this.isHorizontalGridDimensionValid(gridHorizontalDimension)) {
            this.grid = new BattleShipGrid(gridVerticalDimension, gridVerticalDimension);
            this.setEnded(new AtomicBoolean(false));
            this.setWaitingForUserGuess(new AtomicBoolean(false));

            // Create map to store valid start positions for ships
            this.createValidPositionsMaps();

            // Populate the grid
            this.populateGrid();
        }
    }

    public boolean getWaitingForUserGuess() {
        return this.waitingUserGuess.get();
    }

    public void setWaitingForUserGuess(AtomicBoolean bool) {
        this.waitingUserGuess = bool;
    }

    public BattleShipGrid getBattleShipGrid() {
        return this.grid;
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
     * @param newShip the ship that was just added
     */
    private void updateValidPositionsForUnitShip(Ship newShip) {
        for (Cell part : newShip.getParts()) {
            this.validPositionsForUnitShip.remove(part);
        }
    }

    /**
     * Create the valid vertical positions map for ships +1 dimension
     */
    private void createVerticalValidPositionsMap() {
        this.validVerticalPositionsMap.put(ShipDimensions.TWO, this.calculateInitialValidVerticalPositions(2));
        this.validVerticalPositionsMap.put(ShipDimensions.THREE, this.calculateInitialValidVerticalPositions(3));
        this.validVerticalPositionsMap.put(ShipDimensions.FOUR, this.calculateInitialValidVerticalPositions(4));
        this.validVerticalPositionsMap.put(ShipDimensions.FIVE, this.calculateInitialValidVerticalPositions(5));
    }

    /**
     * Create the valid horizontal positions map for ships +1 dimension
     */
    private void createHorizontalValidPositionsMap() {
        this.validHorizontalPositionsMap.put(ShipDimensions.TWO, this.calculateInitialValidHorizontalPositions(2));
        this.validHorizontalPositionsMap.put(ShipDimensions.THREE, this.calculateInitialValidHorizontalPositions(3));
        this.validHorizontalPositionsMap.put(ShipDimensions.FOUR, this.calculateInitialValidHorizontalPositions(4));
        this.validHorizontalPositionsMap.put(ShipDimensions.FIVE, this.calculateInitialValidHorizontalPositions(5));
    }

    /**
     * Build the lists of valid start positions for a horizontal ship based on its dimension
     * @param dimension the dimension of the ship
     * @return validPositionsList {ArrayList}
     */
    private ArrayList<Cell> calculateInitialValidHorizontalPositions(int dimension) {
        ArrayList<Cell> validPositionsList = new ArrayList<>();

        for (int x = 0; x < (this.grid.getHorizontalDimension() - dimension + 1); x++) {
            for (int y = 0; y < this.grid.getVerticalDimension(); y++) {
                Cell validCell = new Cell(x, y);
                validPositionsList.add(validCell);
            }
        }

        return validPositionsList;
    }

    /**
     * Build the lists of valid start positions for a vertical ship based on its dimension
     * @param dimension the dimension of the ship
     * @return validPositionsList {ArrayList}
     */
    private ArrayList<Cell> calculateInitialValidVerticalPositions(int dimension) {
        ArrayList<Cell> validPositionsList = new ArrayList<>();

        for (int x = 0; x < this.grid.getHorizontalDimension(); x++) {
            for (int y = 0; y < (this.grid.getVerticalDimension() - dimension + 1); y++) {
                Cell validCell = new Cell(x, y);
                validPositionsList.add(validCell);
            }
        }

        return validPositionsList;
    }

    /**
     * Update the valid vertical positions map for 1+ dimension after a ship insertion
     * @param newShip the ship that was just added
     */
    private void updateValidVerticalPositionsMap(Ship newShip) {

        switch(newShip.getOrientation()) {
            case HORIZONTAL:
                for (ShipDimensions key : this.validVerticalPositionsMap.keySet()) {
                    this.updateDiffDirectionValidPositionsList(key, newShip, ShipOrientations.VERTICAL);
                }
                break;
            case VERTICAL:
                for (ShipDimensions key : this.validVerticalPositionsMap.keySet()) {
                    this.updateSameDirectionValidPositionsLists(key, newShip, ShipOrientations.VERTICAL);
                }
                break;
        }
    }

    /**
     * Update valid positions lists when the direction of the ship that was added is the same
     * direction of the map we are updating
     * @param key the key of some dimension valid map
     * @param newShip the ship that was just added
     * @param orientation the orientation of the lists we want to update
     */
    private void updateSameDirectionValidPositionsLists(ShipDimensions key, Ship newShip, ShipOrientations orientation) {
        int shipDimension = newShip.getDimension().getValue();
        int yStartPoint = newShip.getStartPosition().getY();
        int xStartPoint = newShip.getStartPosition().getX();
        int keyDimension = key.getValue();
        int count;

        switch(orientation) {
            case VERTICAL:
                count = (yStartPoint + shipDimension - 1);

                for ( ; count > (yStartPoint - keyDimension) && count >= 0; count--) {
                    Cell cellToRemove = new Cell(xStartPoint, count);
                    this.validVerticalPositionsMap.get(key).remove(cellToRemove);
                }
                break;
            case HORIZONTAL:
                count = (xStartPoint + shipDimension - 1);

                for ( ; count > (xStartPoint - keyDimension) && count >= 0; count--) {
                    Cell cellToRemove = new Cell(count, yStartPoint);
                    this.validHorizontalPositionsMap.get(key).remove(cellToRemove);
                }
                break;
        }
    }

    /**
     * Update valid positions lists when the direction of the ship that was added is different
     * from the direction of map we are updating
     * @param key the key of some dimension map
     * @param newShip the ship that was just added
     * @param orientation the orientation of the map we want to update
     */
    private void updateDiffDirectionValidPositionsList(ShipDimensions key, Ship newShip, ShipOrientations orientation) {
        int shipDimension = newShip.getDimension().getValue();
        int yStartPoint = newShip.getStartPosition().getY();
        int xStartPoint = newShip.getStartPosition().getX();
        int keyDimension = key.getValue();

        switch (orientation) {
            case HORIZONTAL:
                for (int yCount = 0; yCount < shipDimension; yCount++, yStartPoint++) {
                    for (int xCount = xStartPoint; (xCount > xStartPoint - keyDimension) && xCount >= 0; xCount--) {
                        Cell cellToRemove = new Cell(xCount, yStartPoint);
                        this.validHorizontalPositionsMap.get(key).remove(cellToRemove);
                    }
                }
                break;
            case VERTICAL:
                for (int xCount = 0; xCount < shipDimension; xCount++, xStartPoint++) {
                    for (int yCount = yStartPoint; (yCount > yStartPoint - keyDimension) && yCount >= 0; yCount--) {
                        Cell cellToRemove = new Cell(xStartPoint, yCount);
                        this.validVerticalPositionsMap.get(key).remove(cellToRemove);
                    }
                }

                break;
        }
    }


    /**
     * Update the valid horizontal positions map for 1+ dimension after a ship insertion
     * @param newShip the ship that was just added
     */
    private void updateValidHorizontalPositionsMap(Ship newShip) {
        switch(newShip.getOrientation()) {
            case HORIZONTAL:
                for (ShipDimensions key : this.validHorizontalPositionsMap.keySet()) {
                    this.updateSameDirectionValidPositionsLists(key, newShip, ShipOrientations.HORIZONTAL);
                }
                break;
            case VERTICAL:
                for (ShipDimensions key : this.validHorizontalPositionsMap.keySet()) {
                    this.updateDiffDirectionValidPositionsList(key, newShip, ShipOrientations.HORIZONTAL);
                }
                break;
        }
    }

    public int getNumberOfGuesses() {
        return this.numberOfGuesses;
    }

    public void initGame() {
        Runnable runnable = () -> {
            while (!this.isEnded()) {
                if(this.getWaitingForUserGuess()) {
                    synchronized(threadObject) {
                        try {
                            threadObject.wait();
                        } catch (InterruptedException e) {}
                    }
                }
            }

            this.controller.closeWindow();
        };

        this.threadObject = new Thread(runnable);
        threadObject.start();
    }

    public void setController(BattleShipGameController controller) {
        this.controller = controller;
    }

    /**
     * Function that initializes the game, populating the grid with ships
     * of random dimension and orientation
     */
    private void populateGrid() {
        while(!this.grid.isFull()) {
            Ship newShip = this.fetchShipFromValidPositionsMap();
            if (newShip != null) {
                this.grid.addShip(newShip);

                // Update the valid positions
                this.updateValidPositionsForUnitShip(newShip);
                this.updateValidHorizontalPositionsMap(newShip);
                this.updateValidVerticalPositionsMap(newShip);
            }
        }
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
            ArrayList<Cell> validPositions = this.validHorizontalPositionsMap.get(randomDimension);

            // Check if there are still available positions at this list
            // TODO: find a better way to do this
            if (validPositions.size() > 0) {
                randomIndex = randomNumber.nextInt(validPositions.size());
                startPosition = validPositions.get(randomIndex);
            } else {
                startPosition = null;
            }
        } else if (randomOrientation == ShipOrientations.VERTICAL) {
            ArrayList<Cell> validPositions = this.validVerticalPositionsMap.get(randomDimension);

            // Check if there are still available positions at this list
            // TODO: find a better way to do this
            if (validPositions.size() > 0) {
                randomIndex = randomNumber.nextInt(validPositions.size());
                startPosition = validPositions.get(randomIndex);
            } else {
                startPosition = null;
            }
        }

        Ship newShip;

        if (startPosition != null) {
            newShip = new Ship(randomDimension, randomOrientation, startPosition);
        } else {
            newShip = null;
        }

        return newShip;
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
     * Increase the number of guesses when the user clicks on a cell button
     */
    public void increaseNumberOfGuesses() {
        this.numberOfGuesses++;
    }

    /**
     * Check if the game has ended
     * @return ended if the game has ended
     */
    public boolean isEnded() {
        return this.ended.get();
    }

    /**
     * Set the game status
     * @param ended whether end the game or not
     */
    public void setEnded(AtomicBoolean ended) {
        this.ended = ended;
    }
}
