package no.uib.inf101.sem2.grid;

/**
 * A CellPosition consists of a coordinate with a row and a column index.
 *
 * @param row  the row index of the cell
 * @param col  the column index of the cell
 */
public record CellPosition(int row, int col) implements Comparable<CellPosition>{

    @Override
    public String toString(){
        return this.row+", "+this.col;
    }

    @Override
    public int compareTo(CellPosition other) {
        if (this.row < other.row) {
            return -1;
        } else if (this.row > other.row) {
            return 1;
        } else if (this.col < other.col) {
            return -1;
        } else if (this.col > other.col) {
            return 1;
        } else {
            return 0;
        }
    }
}
