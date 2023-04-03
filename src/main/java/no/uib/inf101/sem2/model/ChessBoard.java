package no.uib.inf101.sem2.model;

import java.util.ArrayList;
import java.util.Iterator;

public class ChessBoard {
    private final int rows = 8;
    private final int cols = 8;
    private final Tile[][] tiles = new Tile[rows][cols];

    public ChessBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                tiles[i][j] = new EmptyTile(new TilePosition(i,j));
            }
        }
    }

    public  int getRows() {
        return rows;
    }

    public  int getCols() {
        return cols;
    }

    public void getTiles() {
        for (Tile[] tileList : tiles) {
            for(Tile tile : tileList){
                System.out.println(tile);
            }

        }
    }
    public Iterator<Tile> iterator() {
        ArrayList<Tile> cells = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                cells.add(new Tile(new TilePosition(i,j)));
            }
        }
        return cells.iterator();
    }
}
