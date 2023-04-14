package no.uib.inf101.sem2.grid;

/**
 * A GridCell in a grid consists of the position, and the value of the cell .
 *
 * @param pos  the coordinate in the grid
 * @param value  the value contained in the cell
 */
public record GridCell<E>(CellPosition pos, E value) {}

