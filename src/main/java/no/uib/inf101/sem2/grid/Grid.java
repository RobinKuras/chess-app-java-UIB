package no.uib.inf101.sem2.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Grid<E> implements IGrid<E> {
    private final int rows;
    private final int cols;
    private final E defaultValue;
    private final List<List<E>> grid = new ArrayList<>();;

    //Constructor that take in amount of rows, columns and a default value of a generic type.
    //Then generates a nested list that will represent a grid.
    public Grid(int rows, int cols, E defaultValue){
        this.rows = rows;
        this.cols = cols;
        this.defaultValue = defaultValue;

        for (int i = 0; i < rows; i++) {
            grid.add(new ArrayList<>());

            for (int j = 0; j < cols; j++) {
                grid.get(i).add(this.defaultValue);
            }
        }
    }

    //Second constructor in case no default value is given as argument, it is defaulted to 'null'.
    public Grid(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.defaultValue = null;

        for (int i = 0; i < rows; i++) {
            grid.add(new ArrayList<>());

            for (int j = 0; j < cols; j++) {
                grid.get(i).add(this.defaultValue);
            }
        }

    }

    @Override
    public int getRows() {
        return this.rows;
    }

    @Override
    public int getCols() {
        return this.cols;
    }

    @Override
    public void set(CellPosition cellPos, E value) throws IndexOutOfBoundsException {
        this.grid.get(cellPos.row()).set(cellPos.col(),value);
    }

    @Override
    public E get(CellPosition cellPosition) throws IndexOutOfBoundsException {
        return this.grid.get(cellPosition.row()).get(cellPosition.col());
    }

    @Override
    public boolean positionIsOnGrid(CellPosition cellPos) {
        return (cellPos.row() >= 0 && cellPos.row() < this.getRows()
                && cellPos.col() >= 0 && cellPos.col() < this.getCols());
    }

    @Override
    public Iterator<GridCell<E>> iterator() {
        ArrayList<GridCell<E>> cells = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells.add(new GridCell<E>(new CellPosition(i,j),this.get(new CellPosition(i,j))));
            }
        }

        return cells.iterator();
    }
}
